package br.com.johnatan.screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataMovie(@JsonAlias("Title") String title,
                        @JsonAlias ("Year") Integer year,
                        @JsonAlias ("imdbRating") String rating,
                        @JsonAlias ("Director") String director,
                        @JsonAlias ("Runtime") String duration,
                        @JsonAlias ("Genre") String genre,
                        @JsonAlias ("Awards") String awards,
                        @JsonAlias ("Country") String country){

    @Override
    public String awards() {
        if(Objects.equals(awards, "N/A")) {
            return "Não possui nenhuma premiação ou indicação";
        } else{
            return awards;
        }
    }

    @Override
    public String toString() {
        return  "Título: " + title + '\n' +
                "Ano de lançamento: " + year + '\n' +
                "Nota do Imdb: " + rating + '\n' +
                "Direção: " + director + '\n' +
                "Duração do filme: " + duration + '\n' +
                "Gênero: " + genre + '\n' +
                "Premiações e indicações: " + awards() + '\n' +
                "País de origem: " + country + '\n';
    }


}
