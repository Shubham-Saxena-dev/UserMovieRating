package com.check24.controllers;

import com.check24.model.Film;
import com.check24.services.FilmService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/films/")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createFilm(@RequestBody Film film) {
        filmService.createFilm(film);
        return ResponseEntity.ok("Film created successfully");
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> getFilmByName(@PathVariable String name) {
        return ResponseEntity.ok(this.filmService.getFilmByName(name));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(this.filmService.getAllFilms());
    }

    @GetMapping("/rating")
    public ResponseEntity<Object> getAverageRating(@RequestParam("name") String filmName) {
        return ResponseEntity.ok(this.filmService.getAverageRating(filmName));
    }

    @PostMapping("/{name}/rate")
    public ResponseEntity<String> rateFilm(
            @PathVariable("name") String filmName,
            @RequestParam("rating") int rating) {
        filmService.rateFilm(filmName, rating);
        return ResponseEntity.ok("Film rated successfully");
    }
}
