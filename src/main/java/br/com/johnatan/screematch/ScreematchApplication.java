package br.com.johnatan.screematch;

import br.com.johnatan.screematch.service.OmdbAPIClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OmdbAPIClient omdbAPIClient = new OmdbAPIClient();
		var json = omdbAPIClient.obterDados("https://www.omdbapi.com/?t=Matrix&y=1999&apikey=ca12fde4");
		System.out.println(json);
	}
}
