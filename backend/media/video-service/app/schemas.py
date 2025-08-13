from pydantic import BaseModel

class VideoBase(BaseModel):
    title: str
    description: str

class VideoCreate(VideoBase):
    pass

class Video(VideoBase):
    id: int
    file_path: str

    class Config:
        orm_mode = True
