package com.akihabara.market.service;

import com.google.gson.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.*;
import java.util.Properties;

public class LlmService {

    private final String apiKey;

    public LlmService() {
        this.apiKey = cargarApiKey();
    }

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

    public String sugerirNombreProducto(String tipo, String franquicia) {
        if (apiKey == null) return "API Key no disponible.";

        String prompt = "Sugiere un nombre llamativo y original para un producto otaku del tipo '" +
                tipo + "' basado en la franquicia '" + franquicia + "'.";

        try {
            HttpClient client = HttpClient.newHttpClient();

            JsonObject mensaje = new JsonObject();
            mensaje.addProperty("role", "user");
            mensaje.addProperty("content", prompt);

            JsonArray messages = new JsonArray();
            messages.add(mensaje);

            JsonObject body = new JsonObject();
            body.addProperty("model", "mistralai/mistral-7b-instruct:free");
            body.add("messages", messages);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

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
