package com.check24.model;

public class Rating {
    private  double averageRating;
    private  int ratingCount;
    private  String filmName;

    public Rating(){

    }
    public Rating(Builder builder) {
        this.averageRating = builder.rating;
        this.ratingCount = builder.ratingCount;
        this.filmName = builder.filmName;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getFilmName() {
        return filmName;
    }

    public static final class Builder {

        private double rating;
        private int ratingCount;
        private String filmName;
        public Builder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setRatingCount(int ratingCount) {
             this.ratingCount = ratingCount;
             return this;
        }

        public Builder setFilmName(String filmName) {
            this.filmName = filmName;
            return this;
        }
        public Rating build() {
            return new Rating(this);
        }
    }
}
