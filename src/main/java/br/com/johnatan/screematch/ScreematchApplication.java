package br.com.johnatan.screematch;

import br.com.johnatan.screematch.model.DataEpisode;
import br.com.johnatan.screematch.model.DataSeries;
import br.com.johnatan.screematch.service.ConvertData;
import br.com.johnatan.screematch.service.OmdbAPIClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OmdbAPIClient omdbAPIClient = new OmdbAPIClient();
		var json = omdbAPIClient.getDataFromAPI("https://www.omdbapi.com/?t=game+of+thrones&apikey=ca12fde4");
		System.out.println(json);
		ConvertData convert = new ConvertData();
		DataSeries data = convert.getData(json, DataSeries.class);
		System.out.println(data);
		System.out.println("------------------------------------------");
		json = omdbAPIClient.getDataFromAPI("https://www.omdbapi.com/?t=game+of+thrones&Season=1&Episode=1&apikey=ca12fde4");

		DataEpisode dataEpisode = convert.getData(json, DataEpisode.class);
		System.out.println(dataEpisode);
	}
}
