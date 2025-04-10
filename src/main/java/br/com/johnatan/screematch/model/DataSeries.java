package br.com.johnatan.screematch.model;

import br.com.johnatan.screematch.config.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeries(@JsonAlias ("Title") String title,
                         @JsonAlias ("totalSeasons") Integer totalSeasons,
                         @JsonAlias ("imdbRating") String rating,
                         @JsonAlias ("Released")
                         @JsonDeserialize (using = LocalDateDeserializer.class) LocalDate released,
                         @JsonAlias ("Genre") String genre,
                         @JsonAlias ("Year") String yearsInStreaming) {

    @Override
    public String toString() {
        // Formata a data para exibição, tratando o caso de ser null
        String releasedStr =
                (released != null) ? released.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Data indisponível";

        return  "Título: " + title + '\n' +
                "Total de temporadas: " + totalSeasons + " temporadas" + '\n' +
                "Nota do Imdb: " + rating + '\n' +
                "Data de lançamento: " + releasedStr + '\n' +
                "Gênero da série: " + genre + '\n' +
                "Atividade: " + yearsInStreaming;
    }
}
