package com.check24.repository;

import com.check24.model.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmRepo extends MongoRepository<Film, String> {

}
