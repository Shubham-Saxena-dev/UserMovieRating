package com.check24.controllers;

import com.check24.model.Film;
import com.check24.model.User;
import com.check24.services.RecommendationService;
import com.check24.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RecommendationService recommendationService;

    public UserController(UserService userService, RecommendationService recommendationService) {
        this.userService = userService;
        this.recommendationService = recommendationService;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        this.userService.createUser(user);
        return ResponseEntity.ok("user created successfully");
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(this.userService.getUser(name));
    }

    @GetMapping(value = "recommendations/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Film>> filmRecommendations(@PathVariable String userName) {
        return ResponseEntity.ok(this.recommendationService.filmRecommendations(userName));
    }
}
