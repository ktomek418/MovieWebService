package com.myMovies.MoviesWeb.repository;

import com.myMovies.MoviesWeb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
