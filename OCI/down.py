import config

def descargar_archivo_de_nube(storage_client, ruta_nube, ruta_destino):
    print(f"Descargando '{ruta_nube}' de forma nativa desde Oracle Cloud...")
    
    respuesta = storage_client.get_object(
        namespace_name=config.NAMESPACE,
        bucket_name=config.BUCKET_NAME,
        object_name=ruta_nube
    )
    
    # El contenido binario viene dentro del atributo '.data' en la respuesta
    with open(ruta_destino, 'wb') as f:
        f.write(respuesta.data.content)
        
    print(f"¡Descarga exitosa! Guardado localmente como '{ruta_destino}'")