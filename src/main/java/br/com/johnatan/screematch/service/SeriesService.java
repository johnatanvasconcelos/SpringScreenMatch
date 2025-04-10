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
    private final Scanner scanner;
    private final ApiClient omdbAPIClient;
    private final ConvertData convert;
    private static final String URL_PATH = "https://www.omdbapi.com/?t=";

    private DataSeries currentSeriesData;

    private List<DataSeason> seasons = new ArrayList<>();
    private List<Episode> episodes = new ArrayList<>();

    @Autowired
    public SeriesService (Scanner scanner, ApiClient omdbAPIClient, ConvertData convert){
        this.scanner = scanner;
        this.omdbAPIClient = omdbAPIClient;
        this.convert = convert;

    }

    private boolean hasValidRating(Episode e) {
        return e != null && e.getRating() != null && e.getRating() > 0.0;
    }

    public void analyzeSeries() {
        System.out.println("Digite o nome da série: ");
        var nameSeries = scanner.nextLine();

        String seriesUrl = URL_PATH + nameSeries.replace(" ", "+");
        String seriesJson = omdbAPIClient.getDataFromAPI(seriesUrl);

        currentSeriesData = convert.getData(seriesJson, DataSeries.class);

        if (currentSeriesData == null || currentSeriesData.totalSeasons() == null || currentSeriesData.totalSeasons() <= 0){
            System.out.println("Não foi possível obter dados válidos para a série: " + nameSeries);
            return;
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Dados da série pesquisada: \n");
        System.out.println(currentSeriesData);

        seasons.clear();
        for (int i = 1; i <= currentSeriesData.totalSeasons(); i++){
            String seasonJson = omdbAPIClient.getDataFromAPI(seriesUrl + "&Season=" + i);
            DataSeason dataSeason = convert.getData(seasonJson, DataSeason.class);
            if (dataSeason != null) {
                seasons.add(dataSeason);
            }else {
                System.out.println("Não foi possível obter dados válidos para a temporada " + i + " da série " + nameSeries);
            }
        }

        if (seasons.isEmpty()) {
            System.out.println("Não foi possível carregar os episódios da série.");
            episodes = Collections.emptyList();
        } else {
            episodes = seasons.stream()
                    .filter(Objects::nonNull)
                    .filter(s -> s.episodes() != null)
                    .flatMap(s -> s.episodes().stream()
                            .filter(Objects::nonNull)
                            .map(d -> new Episode(s.number(),d))
                    ).toList();
        }

        if (episodes.isEmpty()){
            System.out.println("Nenhum episódio encontrado ou avaliado para calcular estatísticas.");
        } else {
            DoubleSummaryStatistics est = episodes.stream()
                    .filter(this::hasValidRating)
                    .collect(Collectors.summarizingDouble(Episode::getRating));
            System.out.println("\n--- Estatísticas ---");
            if (est.getCount() > 0){
                System.out.printf("Média de avaliação dos episódios: " + String.format("%.2f", est.getAverage()));
                System.out.println("\nQuantidade de episódios avaliados: " + est.getCount());
            }else{
                System.out.println( "Nenhum episódio avaliado.");
            }
            System.out.println("Total de episódios encontrados: " + episodes.size());
        }
    }

    public void showAllEpisodes() {
        if(seasons.isEmpty() || episodes.isEmpty()) {
            System.out.println("Nenhuma série foi analisada ainda. Por favor, analise uma série primeiro.");
            return;
        }

        System.out.println("\n-------------------------------------------");
        System.out.println("Dados das temporadas da série pesquisada: \n");

        seasons.forEach(season -> {
            System.out.println("\n--- Temporada " + season.number() + " ---");
            episodes.stream()
                    .filter(ep -> ep.getSeason().equals(season.number()))
                    .forEach(ep -> {
                        String releaseDateStr = (ep.getReleaseDate() != null)
                                ? ep.getReleaseDate().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                : "Data indisponível";
                        String ratingStr = (ep.getRating() != null && ep.getRating() > 0.0)
                                ? String.format("%.2f", ep.getRating())
                                : "N/A";
                        System.out.println(" Ep. " + ep.getNumberEpisode() + ": " + ep.getTitle() + " | Nota: " + ratingStr + " | Lançamento: " + releaseDateStr);
                    });
        });
    }

    public void showBestEpisode() {
        if (episodes.isEmpty()) {
            System.out.println("Nenhum episódio carregado. Analise uma série primeiro.");
            return;
        }

        episodes.stream()
                .filter(this::hasValidRating)
                .max(Comparator.comparingDouble(Episode::getRating))
                .ifPresentOrElse(
                        e -> System.out.println("\nMelhor episódio: Temp." + e.getSeason()
                                + " Ep.: " + e.getNumberEpisode()
                                + " | " + e.getTitle()
                                + " | Nota: " + String.format("%.2f", e.getRating())),
                        () -> System.out.println("\nNão foi possível determinar o melhor episódio (sem avaliações válidas).")
                );
    }

    public void showWorstEpisode() {
        if (episodes.isEmpty()) {
            System.out.println("Nenhum episódio carregado. Analise uma série primeiro.");
            return;
        }

        episodes.stream()
                .filter(this::hasValidRating)
                .min(Comparator.comparingDouble(Episode::getRating))
                .ifPresentOrElse(
                        e -> System.out.println("\nPior episódio (avaliado): T: " + e.getSeason()
                                + "E: " + e.getNumberEpisode()
                                + " | " + e.getTitle()
                                + " | Nota: " + String.format("%.2f", e.getRating())),
                        () -> System.out.println("\nNão foi possível determinar o pior episódio (sem avaliações válidas).")
                );
    }

}
