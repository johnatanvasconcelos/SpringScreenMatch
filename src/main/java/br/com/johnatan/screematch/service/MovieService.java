package br.com.johnatan.screematch.service;

import br.com.johnatan.screematch.client.ApiClient;
import br.com.johnatan.screematch.model.DataMovie;
import br.com.johnatan.screematch.model.DataSeries;
import br.com.johnatan.screematch.utils.ConvertData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MovieService {
    private final Scanner scanner;
    private final ApiClient omdbAPIClient;
    private final ConvertData convert;
    private static final String URL_PATH = "https://www.omdbapi.com/?t=";

    @Autowired
    public MovieService(ApiClient omdbAPIClient, ConvertData convert, Scanner scanner) {
        this.omdbAPIClient = omdbAPIClient;
        this.convert = convert;
        this.scanner = scanner;
    }

    public void analyzeMovie() {
        System.out.println("Digite o nome do filme: ");
        var nameMovie = scanner.nextLine();

        String lastUrl = URL_PATH + nameMovie.replace(" ", "+");
        String json = omdbAPIClient.getDataFromAPI(lastUrl);

        DataMovie dataMovie = convert.getData(json, DataMovie.class);

        System.out.println("-------------------------------------------------");
        System.out.println("Dados do filme pesquisado: \n");
        System.out.println(dataMovie.toString());
    }
}
