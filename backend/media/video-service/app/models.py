from sqlalchemy import Column, Integer, String, DateTime
from app.database import Base
import datetime

class Video(Base):
    __tablename__ = "videos"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String)
    description = Column(String)
    file_path = Column(String)
    upload_time = Column(DateTime, default=datetime.datetime.utcnow)
