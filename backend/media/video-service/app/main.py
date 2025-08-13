from fastapi import FastAPI, UploadFile, File, Depends, HTTPException
from sqlalchemy.orm import Session
from app import models, schemas, database
import shutil
import os

app = FastAPI()

models.Base.metadata.create_all(bind=database.engine)

UPLOAD_FOLDER = "/videos"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

def get_db():
    db = database.SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.post("/upload/", response_model=schemas.Video)
def upload_video(title: str, description: str, file: UploadFile = File(...), db: Session = Depends(get_db)):
    file_location = f"{UPLOAD_FOLDER}/{file.filename}"
    with open(file_location, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)

    db_video = models.Video(title=title, description=description, file_path=file_location)
    db.add(db_video)
    db.commit()
    db.refresh(db_video)
    return db_video

@app.get("/videos/", response_model=list[schemas.Video])
def list_videos(db: Session = Depends(get_db)):
    return db.query(models.Video).all()

@app.get("/health")
def health():
    return {"status": "ok"}
