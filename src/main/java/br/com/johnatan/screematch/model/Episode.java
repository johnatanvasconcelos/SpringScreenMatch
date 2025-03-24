package br.com.johnatan.screematch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer numberEpisode;
    private Double rating;
    private LocalDate releaseDate;

    public Episode(Integer numberSeason, DataEpisode dataEpisode) {
        this.season = numberSeason;
        this.title = dataEpisode.title();
        this.numberEpisode = dataEpisode.number();

        try {
            this.rating = Double.valueOf(dataEpisode.rating());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(dataEpisode.dateRelease());
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", numberEpisode=" + numberEpisode +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate;
    }
}
