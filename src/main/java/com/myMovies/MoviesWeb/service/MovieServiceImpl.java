package com.myMovies.MoviesWeb.service;

import com.myMovies.MoviesWeb.model.Actor;
import com.myMovies.MoviesWeb.model.Movie;
import com.myMovies.MoviesWeb.model.Review;
import com.myMovies.MoviesWeb.repository.ActorRepository;
import com.myMovies.MoviesWeb.repository.MovieRepository;
import com.myMovies.MoviesWeb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;
    ReviewRepository reviewRepository;
    ActorRepository actorRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ReviewRepository reviewRepository,
                            ActorRepository actorRepository){
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.actorRepository = actorRepository;
    }


    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovie(int id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }



    @Override
    public List<Review> getReviews(int movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.map(Movie::getReviews).orElse(null);
    }

    @Override
    public Optional<Review> getReview(int reviewId) {
        return reviewRepository.findById(reviewId);
    }

    @Override
    public Review addReview(int movieId, Review review){
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()){
            Movie test = movie.get();
            test.addReview(review);
            movieRepository.save(test);
            return review;
        }
        return null;
    }

    @Override
    public Review updateReview(int reviewId, Review review) {
        review.setId(reviewId);
        return reviewRepository.save(review);
    }

    @Override
    public void removeReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }



    @Override
    public List<Actor> getActors(int movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.map(Movie::getActors).orElse(null);
    }

    @Override
    public Actor addActor(int movieId, int actorId) {
        Optional<Movie> to = movieRepository.findById(movieId);
        Optional<Actor> toAdd = actorRepository.findById(actorId);
        if(to.isPresent() && toAdd.isPresent()){
            Movie movie = to.get();
            Actor actor = toAdd.get();
            movie.addActor(actor);
            movieRepository.save(movie);
            return actor;
        }
        return null;
    }

    @Override
    public void removeActor(int movieId, int idActor) {
        Optional<Movie> from = movieRepository.findById(movieId);
        Optional<Actor> toRemove = actorRepository.findById(idActor);
        if(from.isPresent() && toRemove.isPresent()){
            Movie movie = from.get();
            Actor actor = toRemove.get();
            movie.removeActor(actor);
            movieRepository.save(movie);
        }
    }

}
