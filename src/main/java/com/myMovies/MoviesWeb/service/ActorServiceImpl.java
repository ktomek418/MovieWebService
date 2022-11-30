package com.myMovies.MoviesWeb.service;

import com.myMovies.MoviesWeb.model.Actor;
import com.myMovies.MoviesWeb.model.Movie;
import com.myMovies.MoviesWeb.repository.ActorRepository;
import com.myMovies.MoviesWeb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    ActorRepository actorRepository;
    MovieRepository movieRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, MovieRepository movieRepository){
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<Actor> getById(int id) {
        return actorRepository.findById(id);
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void delete(int id) {
        actorRepository.deleteById(id);
    }

    @Override
    public List<Movie> getMovies(int actorId) {
        Optional<Actor> actor = actorRepository.findById(actorId);
        return actor.map(Actor::getMovies).orElse(null);
    }

    @Override
    public Movie addMovie(int actorId, int movieId) {
        Optional<Actor> to = actorRepository.findById(actorId);
        Optional<Movie> toAdd = movieRepository.findById(movieId);
        if(to.isPresent() && toAdd.isPresent()){
            Actor actor = to.get();
            Movie movie = toAdd.get();
            actor.addMovie(movie);
            actorRepository.save(actor);
            return movie;
        }
        return null;
    }

    @Override
    public void removeMovie(int actorId, int movieId) {
        Optional<Actor> from = actorRepository.findById(actorId);
        Optional<Movie> toRemove = movieRepository.findById(movieId);
        if(from.isPresent() && toRemove.isPresent()){
            Actor actor = from.get();
            Movie movie = toRemove.get();
            actor.removeMovie(movie);
            actorRepository.save(actor);
        }
    }
}
