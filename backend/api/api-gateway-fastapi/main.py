from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
import httpx

app = FastAPI()

# CORS Setup
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Backend service URLs
BACKEND_SERVICES = {
    "subscription": "http://subscription-service:8082/api/subscription",
    "payment": "http://payment-service:8084/api/payment",
    "notification": "http://notification-service:8085/api/notify",
    "watch_history": "http://watch-history-service:8090/api/watch-history",
    "analytics": "http://analytics-service:8086/api/analytics",
    "recommendation": "http://recommendation-service:8087/api/recommendations",
    "media_library": "http://media-library-service:8010"
}

@app.get("/health")
def health():
    return {"status": "API Gateway is running"}

async def proxy_request(service_url: str):
    try:
        async with httpx.AsyncClient() as client:
            response = await client.get(service_url)
            response.raise_for_status()
            return response.json()
    except httpx.HTTPStatusError as e:
        print("HTTP error:", e.response.status_code, e.response.text)
        raise HTTPException(status_code=502, detail=f"Service error: {e.response.text}")
    except httpx.RequestError as e:
        print("Connection failed:", str(e))
        raise HTTPException(status_code=502, detail="Service unreachable")
    except Exception as e:
        print("Unexpected error:", str(e))
        raise HTTPException(status_code=500, detail="Gateway error")

@app.get("/api/subscription")
async def proxy_subscription():
    return await proxy_request(BACKEND_SERVICES["subscription"])

@app.get("/api/payment")
async def proxy_payment():
    return await proxy_request(BACKEND_SERVICES["payment"])

@app.get("/api/notify")
async def proxy_notification():
    return await proxy_request(BACKEND_SERVICES["notification"])

@app.get("/api/watch-history")
async def proxy_watch_history():
    return await proxy_request(BACKEND_SERVICES["watch_history"])

@app.get("/api/analytics")
async def proxy_analytics():
    return await proxy_request(BACKEND_SERVICES["analytics"])

@app.get("/api/recommendations")
async def proxy_recommendation():
    return await proxy_request(BACKEND_SERVICES["recommendation"])

@app.get("/api/videos")
async def proxy_videos():
    return await proxy_request(f"{BACKEND_SERVICES['media_library']}/api/videos")
