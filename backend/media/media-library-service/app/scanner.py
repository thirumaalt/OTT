import os

CATEGORIES = ['Movies', 'Series', 'Anime']
BASE_PATH = '/mnt/hgfs/To Watch'

def scan_media():
    media = []
    for category in CATEGORIES:
        cat_path = os.path.join(BASE_PATH, category)
        if os.path.exists(cat_path):
            for file in os.listdir(cat_path):
                full_path = os.path.join(cat_path, file)
                if os.path.isfile(full_path):
                    media.append({
                        "category": category,
                        "title": file,
                        "path": full_path
                    })
    return media
