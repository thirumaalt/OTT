from fastapi import FastAPI, HTTPException
from app import database, models, scanner
from sqlalchemy.future import select

app = FastAPI()

@app.on_event("startup")
async def startup():
    async with database.engine.begin() as conn:
        await conn.run_sync(models.Base.metadata.create_all)

@app.post("/api/media/scan")
async def scan_and_store():
    media_files = scanner.scan_media()
    print("Scanned files:", media_files)  # Debug print
    async with database.SessionLocal() as session:
        for media in media_files:
            entry = models.MediaLibrary(
                category=media['category'],
                title=media['title'],
                path=media['path']
            )
            session.add(entry)
        await session.commit()
    return {"status": "Scan complete", "total": len(media_files)}

@app.get("/api/media/list")
async def list_all():
    async with database.SessionLocal() as session:
        result = await session.execute(select(models.MediaLibrary))
        records = result.scalars().all()
        return [dict(
            id=r.id,
            category=r.category,
            title=r.title,
            path=r.path,
            added_at=r.added_at.isoformat()
        ) for r in records]

@app.get("/api/videos")
async def alias_to_list_all():
    return await list_all()
