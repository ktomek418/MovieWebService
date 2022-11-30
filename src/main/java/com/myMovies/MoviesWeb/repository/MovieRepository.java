package com.myMovies.MoviesWeb.repository;

import com.myMovies.MoviesWeb.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
