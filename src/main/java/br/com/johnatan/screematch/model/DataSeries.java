package br.com.johnatan.screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeries(@JsonAlias ("Title") String title,
                         @JsonAlias ("totalSeasons") Integer totalSeasons,
                         @JsonAlias ("imdbRating") String rating) {

    @Override
    public String toString() {
        return  "Título: " + title + '\n' +
                "Total de temporadas: " + totalSeasons + " temporadas" + '\n' +
                "Nota do Imdb: " + rating + '\n';
    }
}
