package com.HackathonONEG9_52.HackathonG52.domain.pythonapi;

import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.Clasificacion;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.Contenido;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PythonAPI {

    private final String pythonApiUrl;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    // Traemos la URL de las propiedades o usamos el fallback de Denisse
    public PythonAPI(@Value("${python.api.url:http://136.248.240.201:8000/contenido}") String pythonApiUrl) {
        this.pythonApiUrl = pythonApiUrl;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Clasificacion clasificar(Contenido contenido) {
        try {
            //Convertimos el contenido a formato JSON
            String jsonContenido = objectMapper.writeValueAsString(contenido);

            //Armamos la peticion
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(pythonApiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonContenido))
                    .build();

            //Enviamos peticion y recibimos la respuesta en formato String(JSON)
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Convertimos el JSON enviado por la API a un objeto Clasificacion
            return objectMapper.readValue(response.body(), Clasificacion.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al comunicarse con el servicio de IA en " + pythonApiUrl, e);
        }
    }
}
