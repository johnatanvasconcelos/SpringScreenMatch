package br.com.johnatan.screematch.main;

import br.com.johnatan.screematch.model.DataEpisode;
import br.com.johnatan.screematch.model.DataSeason;
import br.com.johnatan.screematch.model.DataSeries;
import br.com.johnatan.screematch.service.ConvertData;
import br.com.johnatan.screematch.service.OmdbAPIClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        for (int i = 0; i < dataSeries.totalSeasons(); i++) {
            List<DataEpisode> episodesForSeason = seasons.get(i).episodes();
            for (int j = 0; j < episodesForSeason.size(); j++) {
                System.out.println(episodesForSeason.get(j).title());
            }
        }

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

    }

}
