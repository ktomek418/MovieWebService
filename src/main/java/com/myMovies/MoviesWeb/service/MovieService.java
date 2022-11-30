package com.myMovies.MoviesWeb.service;

import com.myMovies.MoviesWeb.model.Actor;
import com.myMovies.MoviesWeb.model.Movie;
import com.myMovies.MoviesWeb.model.Review;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getMovies();
    Optional<Movie> getMovie(int movieId);
    Movie addMovie(Movie movie);
    void deleteMovie(int movieId);

    List<Review> getReviews(int movieId);
    Optional<Review> getReview(int reviewId);
    Review addReview(int movieId, Review review);
    Review updateReview(int reviewId, Review review);
    void removeReview(int reviewId);

    List<Actor> getActors(int movieId);
    Actor addActor(int movieId, int actorId);
    void removeActor(int movieId, int actorId);
}
