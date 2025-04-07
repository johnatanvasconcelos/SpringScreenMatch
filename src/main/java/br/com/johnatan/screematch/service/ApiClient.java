package br.com.johnatan.screematch.service;

import br.com.johnatan.screematch.utils.ApiKeyReader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final String apiKey;

    public ApiClient() {
        this.apiKey = ApiKeyReader.getApiKey();
        if (this.apiKey == null) {
            throw new RuntimeException("API key not found.");
        }
    }

    public String getDataFromAPI(String apiUrl) {
        var newUrl = apiUrl + "&apikey=" + apiKey;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}


