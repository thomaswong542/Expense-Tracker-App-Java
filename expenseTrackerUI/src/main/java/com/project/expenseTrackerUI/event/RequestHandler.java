package com.project.expenseTrackerUI.event;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public abstract class RequestHandler {

    private static HttpRequest getHttpRequest(String method, String uri, String body){
        return switch (method) {
            case "POST" -> HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(20))
                    .header("Content-Type", "application/json")
                    .header("Accept-Encoding", "gzip")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            case "PUT" -> HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(20))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            case "DELETE" -> HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(20))
                    .DELETE()
                    .build();
            default -> HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(20))
                    .header("Accept-Encoding", "gzip")
                    .GET()
                    .build();
        };
    }

    public static HttpResponse<byte[]> sendHttpRequest(HttpClient client, String method, String uri, String body){
        HttpRequest request = getHttpRequest(method, uri, body);

        if ( (method.equals("POST") || method.equals("PUT")) && body.isEmpty()) {
            EventHandlerTools.showAlert("POST and PUT method require a json body");
            return null;
        }

        HttpResponse<byte[]> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (IOException | InterruptedException e) {
            EventHandlerTools.showAlert("Unable to send Request");
            throw new RuntimeException(e);
        }
        return response;
    }

}
