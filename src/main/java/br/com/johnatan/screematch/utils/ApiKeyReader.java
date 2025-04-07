package br.com.johnatan.screematch.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApiKeyReader {
    public static String getApiKey() {
        String filePath = "api.key";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de chave da API: " + e.getMessage());
            return null;
        }
    }
}
