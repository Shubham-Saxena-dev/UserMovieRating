package com.check24.services;

import com.check24.exceptions.NotFoundException;
import com.check24.model.Film;
import com.check24.repository.FilmRepo;
import com.check24.utils.FilmUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FilmService {

    private final FilmRepo filmRepo;

    public FilmService(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    public void createFilm(Film film) {
        filmRepo.save(film);
    }

    public Film getFilmByName(String name) {
        return this.findFilm(name);
    }

    public List<Film> getAllFilms() {
        var films = filmRepo.findAll();
        if (films.isEmpty()) {
            throw new NotFoundException("No films found");
        }

        return films;
    }

    public void rateFilm(String filmName, int rating) {
        var film = this.findFilm(filmName);
        var newRating = FilmUtil.getAverageForAFilm(film, rating);
        film.setRating(newRating);
        filmRepo.save(film);
    }

    public double getAverageRating(String filmName) {
        var film = this.findFilm(filmName);
        var rating = film.getRating();

        if (Objects.isNull(rating)) {
            return 0.0;
        }
        return rating.getAverageRating();
    }

    public Film findFilm(String filmName) {
        var film = filmRepo.findById(filmName);
        if (film.isEmpty()) {
            throw new NotFoundException("No film found");
        }
        return film.get();
    }
}
