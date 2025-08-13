import os
from fastapi import Request, HTTPException
from fastapi.responses import StreamingResponse

CHUNK_SIZE = 1024 * 1024  # 1MB
BASE_PATH = "/mnt/hgfs/To Watch"

async def stream_file(request: Request, file_path: str):
    full_path = os.path.join(BASE_PATH, file_path)
    if not os.path.isfile(full_path):
        raise HTTPException(status_code=404, detail="File not found")

    file_size = os.path.getsize(full_path)
    headers = {}
    range_header = request.headers.get('Range')

    def iterfile(start=0, end=None):
        with open(full_path, "rb") as f:
            f.seek(start)
            remaining = (end or file_size) - start
            while remaining > 0:
                chunk_size = min(CHUNK_SIZE, remaining)
                data = f.read(chunk_size)
                if not data:
                    break
                yield data
                remaining -= len(data)

    if range_header:
        start, end = range_header.replace("bytes=", "").split("-")
        start = int(start)
        end = int(end) if end else file_size - 1
        content_length = end - start + 1
        headers["Content-Range"] = f"bytes {start}-{end}/{file_size}"
        headers["Accept-Ranges"] = "bytes"
        headers["Content-Length"] = str(content_length)
        return StreamingResponse(iterfile(start, end + 1), status_code=206, headers=headers)

    headers["Content-Length"] = str(file_size)
    return StreamingResponse(iterfile(), headers=headers)
