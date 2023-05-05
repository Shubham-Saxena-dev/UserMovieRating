package com.check24.services;

import com.check24.model.Film;
import com.check24.model.Rating;
import com.check24.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RecommendationService {

    private final FilmService filmService;
    private final UserService userService;

    public RecommendationService(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    public List<Film> filmRecommendations(String name) {

        User user = this.userService.findUser(name);
        List<Film> films = this.filmService.getAllFilms();

        List<String> filmNames = new ArrayList<>();

        if(Objects.nonNull(user.getRatings())) {
            for (Rating rating : user.getRatings()) {
                String filmName = rating.getFilmName();
                filmNames.add(filmName);
            }
        }
        List<Film> filteredFilms = new ArrayList<>();
        for (Film film1 : films) {
            if (!filmNames.contains(film1.getFilmName())) {
                filteredFilms.add(film1);
            }
        }
        List<Film> filmRecommendations = new ArrayList<>();
        for (Film film : filteredFilms) {
            if (user.getGenre().contains(film.getGenre()) && user.getDirectors().contains(film.getDirector())) {
                filmRecommendations.add(film);
            }
        }
        return filmRecommendations;
    }
}
