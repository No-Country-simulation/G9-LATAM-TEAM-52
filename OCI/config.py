import os
from dotenv import load_dotenv

# Carga las variables desde el archivo .env si estás en tu entorno local
load_dotenv()

CONFIG_OCI = {
    "user": os.getenv("OCI_USER"),
    "fingerprint": os.getenv("OCI_FINGERPRINT"),
    "tenancy": os.getenv("OCI_TENANCY"),
    "region": os.getenv("OCI_REGION"),
    "key_file": os.getenv("OCI_KEY_FILE")
}

NAMESPACE = os.getenv("OCI_NAMESPACE")
BUCKET_NAME = os.getenv("OCI_BUCKET_NAME")