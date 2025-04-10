package br.com.johnatan.screematch.application;

import br.com.johnatan.screematch.service.MovieService;
import br.com.johnatan.screematch.service.SeriesService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Main {
    private final Scanner scanner;
    private final SeriesService seriesService;
    private final MovieService movieService;

    public Main(SeriesService seriesService, Scanner scanner, MovieService movieService) {
        this.seriesService = seriesService;
        this.movieService = movieService;
        this.scanner = scanner;
    }

    public void showMenu(){
        boolean running = true;
        while (running) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("Digite 1 - Analisar um filme.");
            System.out.println("Digite 2 - Analisar uma série.");
            System.out.println("Digite 0 - Sair");
            int option = scanner.nextInt();
            scanner.nextLine();


            switch (option) {
                case 1:
                    movieService.analyzeMovie();
                    break;
                case 2:
                    seriesService.analyzeSeries();
                    boolean submenu = true;
                    while (submenu) {
                        System.out.println("\nO que mais deseja saber desta série?");
                        System.out.println("1 - Mostrar todos os episódios");
                        System.out.println("2 - Melhor Episódio");
                        System.out.println("3 - Pior Episódio");
                        System.out.println("0 - Voltar");
                        int subOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (subOption) {
                            case 1: seriesService.showAllEpisodes(); break;
                            case 2: seriesService.showBestEpisode(); break;
                            case 3: seriesService.showWorstEpisode(); break;
                            case 0: submenu = false; break;
                            default: System.out.println("Opção inválida.");
                        }
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
   }
}

