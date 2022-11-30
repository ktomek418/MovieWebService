package com.myMovies.MoviesWeb.controller;

import com.myMovies.MoviesWeb.model.Actor;
import com.myMovies.MoviesWeb.model.Movie;
import com.myMovies.MoviesWeb.model.Review;
import com.myMovies.MoviesWeb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieService service;

    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping("")
    public List<Movie> getMovies(){
        return service.getMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) throws MovieNotFoundException{
        return service.getMovie(id).orElseThrow(MovieNotFoundException::new);
    }

    @PostMapping("")
    public Movie addMovie(@RequestBody Movie movie){
        return service.addMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie){
        movie.setId(id);
        return service.addMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){
        service.deleteMovie(id);
    }


    @GetMapping("/{id}/reviews")
    public List<Review> getReviews(@PathVariable int id){
        return service.getReviews(id);
    }

    @GetMapping("/{id}/reviews/{reviewId}")
    public Review getReview(@PathVariable int id, @PathVariable int reviewId){
        return service.getReview(reviewId).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/{id}/reviews")
    public Review addReview(@PathVariable int id, @RequestBody Review review){
        service.addReview(id, review);
        return review;
    }

    @PutMapping("/{id}/reviews/{reviewId}")
    public Review updateReview(@PathVariable int id, @PathVariable int reviewId, @RequestBody Review review){
        return service.updateReview(reviewId, review);
    }

    @DeleteMapping("{id}/reviews/{reviewId}")
    public void deleteReview(@PathVariable int id, @PathVariable int reviewId){
        service.removeReview(reviewId);
    }


    @GetMapping("/{id}/actors")
    public List<Actor> getActors(@PathVariable int id){
        return service.getActors(id);
    }

    @PostMapping("/{id}/actors/{actorId}")
    public Actor addActor(@PathVariable int id, @PathVariable int actorId){
        return service.addActor(id, actorId);
    }

    @DeleteMapping("/{id}/actors/{actorId}")
    public void removeActor(@PathVariable int id, @PathVariable int actorId){
        service.removeActor(id, actorId);
    }

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleException(MovieNotFoundException exception){
        MovieErrorResponse errorResponse = new MovieErrorResponse(
                HttpStatus.NOT_FOUND.value(), exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
