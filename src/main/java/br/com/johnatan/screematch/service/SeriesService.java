package br.com.johnatan.screematch.service;

import br.com.johnatan.screematch.model.DataEpisode;
import br.com.johnatan.screematch.model.DataSeason;
import br.com.johnatan.screematch.model.DataSeries;
import br.com.johnatan.screematch.client.ApiClient;

import br.com.johnatan.screematch.model.Episode;
import br.com.johnatan.screematch.utils.ConvertData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    private static final Scanner scanner = new Scanner(System.in);
    private final ApiClient omdbAPIClient;
    private final ConvertData convert;
    private static final String URL_PATH = "https://www.omdbapi.com/?t=";

    private String lastUrl;
    private String json;
    private DataSeries lastDataSeries;

    private List<DataSeason> seasons = new ArrayList<>();;
    private List<Episode> episodes;

    @Autowired
    public SeriesService (ApiClient omdbAPIClient, ConvertData convert){
        this.omdbAPIClient = omdbAPIClient;
        this.convert = convert;

    }

    public void analyzeSeries() {
        System.out.println("Digite o nome da série: ");
        var nameSeries = scanner.nextLine();

        lastUrl = URL_PATH + nameSeries.replace(" ", "+");
        json = omdbAPIClient.getDataFromAPI(lastUrl);

        lastDataSeries = convert.getData(json, DataSeries.class);

        System.out.println("-------------------------------------------------");
        System.out.println("Dados da série pesquisada: \n");
        System.out.println(lastDataSeries.toString());
    }

    public void showAllEpisodes() {
        if(lastDataSeries == null || lastUrl == null) {
            System.out.println("Nenhuma série foi analisada ainda. Por favor, analise uma série primeiro.");
            return;
        }

        seasons = new ArrayList<>();

        for (int i = 1; i<= lastDataSeries.totalSeasons(); i++) {
            String seasonJson = omdbAPIClient.getDataFromAPI(lastUrl + "&Season=" + i);
            DataSeason dataSeason = convert.getData(seasonJson, DataSeason.class);
            seasons.add(dataSeason);
        }
        System.out.println("\n-------------------------------------------");
        System.out.println("Dados das temporadas da série pesquisada: \n");
        seasons.forEach(System.out::println);

        for (int i = 0; i < lastDataSeries.totalSeasons(); i++) {
            System.out.println("\nTemporada " + (i+1) + ": \n");
            List<DataEpisode> episodesForSeason = seasons.get(i).episodes();

            List<DataEpisode> validEpisodes = episodesForSeason.stream()
                    .filter(e -> e.dateRelease() != null && !e.dateRelease().equalsIgnoreCase("N/A"))
                    .toList();

            for (DataEpisode episode : validEpisodes) {
                System.out.println(episode.title() + " | Nota: " + episode.rating() + " | Data de lançamento: " + episode.dateRelease());
            }
        }
    }

    public void showBestEpisode() {
        episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(s.number(),d))
                ).toList();

        DoubleSummaryStatistics est = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));

        System.out.println("\nMédia dos episódios: " + String.format("%.2f",est.getAverage()) );
        System.out.println("Quantidade de episódios avaliados: " + est.getCount());

        episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .max(Comparator.comparingDouble(Episode::getRating))
                .ifPresent(e ->
                        System.out.println(
                                "Melhor episódio: "
                                        + e.getTitle()
                                        + " - Nota: "
                                        + e.getRating()
                        ));
    }

    public void showWorstEpisode() {
        episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .min(Comparator.comparingDouble(Episode::getRating))
                .ifPresent(e ->
                        System.out.println(
                                "Pior episódio: "
                                        + e.getTitle()
                                        + " - Nota: "
                                        + e.getRating()
                        ));
    }

}
