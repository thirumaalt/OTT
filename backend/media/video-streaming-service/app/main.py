from fastapi import FastAPI, Request
from app import streamer

app = FastAPI()

@app.get("/api/stream/{category}/{filename}")
async def stream(request: Request, category: str, filename: str):
    file_path = f"{category}/{filename}"
    return await streamer.stream_file(request, file_path)
