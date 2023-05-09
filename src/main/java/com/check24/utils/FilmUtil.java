package com.check24.utils;

import com.check24.model.Film;
import com.check24.model.Rating;

import java.util.Objects;

public class FilmUtil {

    public static Rating getAverageForAFilm(Film film, int rating) {
        int ratingCount = Objects.nonNull(film.getRating()) ? film.getRating().getRatingCount() : 0;
        double averageRating = Objects.nonNull(film.getRating()) ? film.getRating().getAverageRating() : 0.0;
        double newAverageRating = (averageRating * ratingCount + rating) / (ratingCount + 1);
        Rating newRating = new Rating.Builder().setRating(newAverageRating).setRatingCount(ratingCount + 1).setFilmName(film.getFilmName()).build();
        return newRating;
    }
}
