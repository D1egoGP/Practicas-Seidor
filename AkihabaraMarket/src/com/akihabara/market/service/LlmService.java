package com.akihabara.market.service;

import com.google.gson.*; // Librería para manejar JSON con Gson
import java.io.FileInputStream; // Para leer archivos
import java.io.InputStream;     // Flujo de entrada
import java.net.URI;            // Para construir URLs
import java.net.http.*;         // Cliente HTTP de Java
import java.util.Properties;    // Para manejar archivos de configuración

public class LlmService {

    private final String apiKey; // Clave API para acceder a OpenRouter

    // Constructor que carga la clave API
    public LlmService() {
        this.apiKey = cargarApiKey();
    }

    // Método para cargar la clave API desde el archivo config.properties
    private String cargarApiKey() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("OPENROUTER_API_KEY");
        } catch (Exception e) {
            System.out.println("No se pudo cargar la API Key: " + e.getMessage());
            return null;
        }
    }

    // Genera un nombre original de producto usando el modelo LLM
    public String sugerirNombreProducto(String tipo, String franquicia) {
        if (apiKey == null) return "API Key no disponible.";

        // Prompt que se le envía al modelo
        String prompt = "Sugiere un nombre llamativo y original para un producto otaku del tipo '" +
                tipo + "' basado en la franquicia '" + franquicia + "'.";

        try {
            HttpClient client = HttpClient.newHttpClient(); // Cliente HTTP

            JsonObject mensaje = new JsonObject(); // Mensaje con rol y contenido
            mensaje.addProperty("role", "user");
            mensaje.addProperty("content", prompt);

            JsonArray messages = new JsonArray(); // Array de mensajes
            messages.add(mensaje);

            JsonObject body = new JsonObject(); // Cuerpo de la petición
            body.addProperty("model", "mistralai/mistral-7b-instruct:free");
            body.add("messages", messages);

            // Construcción de la solicitud POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            // Envío de la solicitud y captura de la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Extracción del contenido de la respuesta
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            return json.getAsJsonArray("choices")
                       .get(0).getAsJsonObject()
                       .getAsJsonObject("message")
                       .get("content").getAsString();

        } catch (Exception e) {
            return "Error al contactar con OpenRouter: " + e.getMessage();
        }
    }
}
