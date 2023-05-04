package com.check24.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

    @Id
    private String name;

    private String password;

    @JsonIgnore
    private List<Rating> ratings;

    private List<String> genre;

    private List<String> directors;

    public User() {

    }

    public User(Builder builder) {
        this.name = builder.name;
        this.ratings = builder.ratings;
        this.genre = builder.genre;
        this.directors = builder.directors;
        this.password = builder.password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public List<Rating> getRatings() {
        return ratings;
    }

    public List<String> getGenre() {
        return genre;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public static final class Builder {

        private String name;
        private List<Rating> ratings;
        private List<String> genre;
        private String password;
        private List<String> directors;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRatings(List<Rating> ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder setGenre(List<String> genre) {
            this.genre = genre;
            return this;
        }

        public Builder setDirectors(List<String> directors) {
            this.directors = directors;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
