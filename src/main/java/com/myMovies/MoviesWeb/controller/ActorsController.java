package com.myMovies.MoviesWeb.controller;

import com.myMovies.MoviesWeb.model.Actor;
import com.myMovies.MoviesWeb.model.Movie;
import com.myMovies.MoviesWeb.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    ActorService service;

    @Autowired
    public ActorsController(ActorService service){
        this.service = service;
    }

    @GetMapping("")
    public List<Actor> allActors(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable int id) throws ActorNotFoundException{
        return service.getById(id).orElseThrow(ActorNotFoundException::new);
    }

    @PostMapping
    public Actor addActor(@RequestBody Actor actor){
        return service.save(actor);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor actor){
        actor.setId(id);
        return service.save(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable int id){
        service.delete(id);
    }

    @GetMapping("/{id}/movies")
    public List<Movie> getMovies(@PathVariable int id){
        return service.getMovies(id);
    }

    @PostMapping("/{id}/movies/{movieId}")
    public Movie addMovie(@PathVariable int id, @PathVariable int movieId){
        return service.addMovie(id, movieId);
    }

    @DeleteMapping("/{id}/movies/{movieId}")
    public void removeMovie(@PathVariable int id, @PathVariable int movieId){
        service.removeMovie(id, movieId);
    }

    @ExceptionHandler
    public ResponseEntity<ActorErrorResponse> handleException(ActorNotFoundException exception){
        ActorErrorResponse errorResponse = new ActorErrorResponse(
                HttpStatus.NOT_FOUND.value(), exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
