from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import os
import requests
import mimetypes

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

VIDEO_EXTENSIONS = [".mp4", ".mkv", ".avi", ".mov"]
VIDEO_DIR = "/mnt/hgfs/To Watch"
TMDB_API_KEY = os.getenv("TMDB_API_KEY")
TMDB_API_URL = "http://metadata-service:8088/api/metadata"

class Metadata(BaseModel):
    title: str
    path: str
    category: str


def scan_videos():
    print("Scanning recursively inside:", VIDEO_DIR)
    video_files = []
    for root, dirs, files in os.walk(VIDEO_DIR):
        for file in files:
            ext = os.path.splitext(file)[1].lower()
            if ext in VIDEO_EXTENSIONS:
                full_path = os.path.join(root, file)
                category = infer_category(full_path)
                video_files.append({
                    "title": os.path.splitext(file)[0],
                    "path": full_path,
                    "category": category
                })
    print("Found:", len(video_files), "videos")
    return video_files


def infer_category(path):
    path_lower = path.lower()
    if "anime" in path_lower:
        return "anime"
    elif "movie" in path_lower:
        return "movie"
    elif "tv" in path_lower or "series" in path_lower:
        return "series"
    return "unknown"


def push_to_metadata_service(media):
    try:
        resp = requests.post(TMDB_API_URL, json=media, timeout=10)
        return resp.status_code == 200
    except Exception as e:
        print("Error pushing metadata:", e)
        return False


@app.post("/scan")
def scan_and_push():
    scanned = scan_videos()
    pushed = 0
    results = []
    for item in scanned:
        success = push_to_metadata_service(item)
        results.append({"title": item["title"], "status": "ok" if success else "failed"})
        if success:
            pushed += 1
    return {"scanned": len(scanned), "pushed": pushed, "results": results}
