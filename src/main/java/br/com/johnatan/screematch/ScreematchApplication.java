package br.com.johnatan.screematch;

import br.com.johnatan.screematch.application.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreematchApplication implements CommandLineRunner {
	private final Main main;

	@Autowired
    public ScreematchApplication(Main main) {
        this.main = main;
    }

    public static void main(String[] args) {
		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.showMenu();
	}
}
