import config

def subir_archivo_a_nube(storage_client, ruta_local, ruta_nube):
    print(f"Subiendo '{ruta_local}' de forma nativa a Oracle Cloud...")
    
    with open(ruta_local, 'rb') as f:
        contenido_bytes = f.read()
        
    storage_client.put_object(
        namespace_name=config.NAMESPACE,
        bucket_name=config.BUCKET_NAME,
        object_name=ruta_nube,
        put_object_body=contenido_bytes
    )
    
    print("¡Subida exitosa sin problemas de compatibilidad AWS!")