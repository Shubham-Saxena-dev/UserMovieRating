package com.check24.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;


public class Film {

    @Id
    private String filmName;
    private String director;
    private String genre;
    @JsonIgnore
    private Rating rating;

    public Film() {

    }

    public Film(Builder builder) {
        this.filmName = builder.filmName;
        this.director = builder.director;
        this.genre = builder.genre;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getFilmName() {
        return filmName;
    }


    public static final class Builder {
        private String filmName;
        private String director;
        private String genre;

        public Builder setFilmName(String filmName) {
            this.filmName = filmName;
            return this;
        }

        public Builder setDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Film build() {
            return new Film(this);
        }

    }

}
