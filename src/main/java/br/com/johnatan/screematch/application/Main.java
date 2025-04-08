package br.com.johnatan.screematch.application;

import br.com.johnatan.screematch.service.SeriesService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Main {
    private final Scanner scanner;
    private final SeriesService seriesService;

    public Main(SeriesService seriesService, Scanner scanner) {
        this.seriesService = seriesService;
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

            switch (option) {
                case 1:
//                    analyzeMovie();
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


//        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
//
//        List<DataEpisode> dataOfEpisodes = seasons.stream()
//                .flatMap(s -> s.episodes().stream())
//                .toList();
//
//        System.out.println("\nTop 10 episódios da série " + dataSeries.title() + "\n");
//        dataOfEpisodes.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro filtro (N/A) " + e))
//                .sorted(Comparator.comparing(DataEpisode::rating).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite " + e))
//                .map(e -> e.title().toUpperCase().concat(" - " + e.rating()))
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);
//
//        System.out.println("\n-------------------------------\n");
//
//        List<Episode> episodes = seasons.stream()
//                .flatMap(s -> s.episodes().stream()
//                        .map(d -> new Episode(s.number(),d))
//                ).toList();
//
//        episodes.forEach(System.out::println);
//
//        System.out.println("Digite o nome do episódio: ");
//        var titleExcerpt = scanner.nextLine();
//
//        Optional<Episode> episodeSearched = episodes.stream()
//                .filter(e -> e.getTitle().toLowerCase().contains(titleExcerpt.toLowerCase()))
//                .findFirst();
//        if(episodeSearched.isPresent()){
//            System.out.println("Episódio encontrado: " + episodeSearched.get());
//            System.out.println("Temporada: " + episodeSearched.get().getSeason());
//        }else{
//            System.out.println("Episódio não encontrado.");
//        }
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
//
//        Map<Integer, String> ratingsPerSeason = episodes.stream()
//                .filter(e -> e.getRating() > 0.0)
//                .collect(Collectors.groupingBy(
//                        Episode::getSeason,
//                        Collectors.collectingAndThen(
//                                Collectors.averagingDouble(Episode::getRating),
//                                avg -> String.format("%.2f", avg)
//                )
//                ));
//
//        System.out.println("\n------------------------------------------------------");
//        ratingsPerSeason.forEach((season, avgRating) ->
//                System.out.println("Season " + season + " - Rate average: " + avgRating)
//        );
//
//        DoubleSummaryStatistics est = episodes.stream()
//                .filter(e -> e.getRating() > 0.0)
//                .collect(Collectors.summarizingDouble(Episode::getRating));
//
//        System.out.println("\nMédia dos episódios: " + String.format("%.2f",est.getAverage()) );
//        System.out.println("Quantidade de episódios avaliados: " + est.getCount());
//
//        episodes.stream()
//                        .filter(e -> e.getRating() > 0.0)
//                        .max(Comparator.comparingDouble(Episode::getRating))
//                        .ifPresent(e ->
//                                System.out.println(
//                                        "Melhor episódio: "
//                                                + e.getTitle()
//                                                + " - Nota: "
//                                                + e.getRating()
//                                ));
//
//        episodes.stream()
//                .filter(e -> e.getRating() > 0.0)
//                .min(Comparator.comparingDouble(Episode::getRating))
//                .ifPresent(e ->
//                        System.out.println(
//                                "Pior episódio: "
//                                        + e.getTitle()
//                                        + " - Nota: "
//                                        + e.getRating()
//                        ));

   }
}

