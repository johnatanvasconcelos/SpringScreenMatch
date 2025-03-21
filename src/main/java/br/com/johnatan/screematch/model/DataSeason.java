package br.com.johnatan.screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason(@JsonAlias("Season") Integer number,
                         @JsonAlias("Episodes") List<DataEpisode> episodes) {
}
