package com.myMovies.MoviesWeb.repository;

import com.myMovies.MoviesWeb.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
