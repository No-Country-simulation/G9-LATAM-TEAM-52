from conexion import obtener_cliente_oci
import up as subidor
import down as descargador

def main():

    storage_client = obtener_cliente_oci()

    archivo_original = "modelo_reconocedor_numeros.pkl"
    archivo_en_nube = "model/modelo_reconocedor_numeros.pkl"
    archivo_descargado = "descargado_" + archivo_original

    print("Subir Archivo = 1 / Bajar Archivo = 2 / Cancelar = 0")

    res = int(input("Seleccione una opción: "))

    if res == 1:
        subidor.subir_archivo_a_nube(storage_client, archivo_original, archivo_en_nube)
    
    if res == 2:
        descargador.descargar_archivo_de_nube(storage_client, archivo_en_nube, archivo_descargado)


main()