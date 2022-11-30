package com.myMovies.MoviesWeb.service;

import com.myMovies.MoviesWeb.model.Actor;
import com.myMovies.MoviesWeb.model.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    List<Actor> getAll();
    Optional<Actor> getById(int actorId);
    Actor save(Actor actor);
    void delete(int actorId);

    List<Movie> getMovies(int actorId);
    Movie addMovie(int actorId, int movieId);
    void removeMovie(int actorId, int movieId);
}
