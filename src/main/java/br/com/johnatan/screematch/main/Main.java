package br.com.johnatan.screematch.main;

import br.com.johnatan.screematch.model.DataEpisode;
import br.com.johnatan.screematch.model.DataSeason;
import br.com.johnatan.screematch.model.DataSeries;
import br.com.johnatan.screematch.model.Episode;
import br.com.johnatan.screematch.service.ConvertData;
import br.com.johnatan.screematch.service.OmdbAPIClient;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private OmdbAPIClient omdbAPIClient = new OmdbAPIClient();

    private ConvertData convert = new ConvertData();

    private static final String URL_PATH = "https://www.omdbapi.com/?t=";

    private static final String API_KEY = "ca12fde4";

    public void showMenu(){
        System.out.println("Digite o nome da série: ");
        var nameSeries = scanner.nextLine();
        var url = URL_PATH + nameSeries.replace(" ", "+") + "&apikey=" + API_KEY;
        var json = omdbAPIClient.getDataFromAPI(url);
        DataSeries dataSeries = convert.getData(json, DataSeries.class);
        System.out.println(dataSeries);

        List<DataSeason> seasons = new ArrayList<>();

		for (int i=1; i<=dataSeries.totalSeasons(); i++) {
			json = omdbAPIClient.getDataFromAPI(url + "&Season=" + i);
			DataSeason dataSeason = convert.getData(json, DataSeason.class);
			seasons.add(dataSeason);
		}
		seasons.forEach(System.out::println);

//        for (int i = 0; i < dataSeries.totalSeasons(); i++) {
//            List<DataEpisode> episodesForSeason = seasons.get(i).episodes();
//            for (int j = 0; j < episodesForSeason.size(); j++) {
//                System.out.println(episodesForSeason.get(j).title());
//            }
//        }
//
//        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

        List<DataEpisode> dataOfEpisodes = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                        .toList();

        System.out.println("\nTop 10 episódios da série " + dataSeries.title() + "\n");
        dataOfEpisodes.stream()
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro (N/A) " + e))
                .sorted(Comparator.comparing(DataEpisode::rating).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .peek(e -> System.out.println("Limite " + e))
                .map(e -> e.title().toUpperCase().concat(" - " + e.rating()))
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);

        System.out.println("\n-------------------------------\n");

//        List<Episode> episodes = seasons.stream()
//                .flatMap(s -> s.episodes().stream()
//                        .map(d -> new Episode(s.number(),d))
//                ).toList();
//        episodes.forEach(System.out::println);
//
//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var year = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate dateSearch = LocalDate.of(year, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(dateSearch))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getSeason() +
//                                "\nEpisódio: " + e.getTitle() +
//                                "\nNota: " + e.getRating() +
//                                "\nData de lançamento: " + e.getReleaseDate().format(formatter)
//                ));
    }

}
