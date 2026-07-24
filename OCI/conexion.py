# conexion.py
import oci
import config

def obtener_cliente_oci():

    config_dict = config.CONFIG_OCI
    cliente_storage = oci.object_storage.ObjectStorageClient(config_dict)
    
    return cliente_storage