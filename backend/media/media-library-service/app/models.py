from sqlalchemy import Column, Integer, String, TIMESTAMP
from .database import Base
import datetime

class MediaLibrary(Base):
    __tablename__ = "media_library"

    id = Column(Integer, primary_key=True, index=True)
    category = Column(String, index=True)
    title = Column(String, index=True)
    path = Column(String)
    added_at = Column(TIMESTAMP, default=datetime.datetime.utcnow)
