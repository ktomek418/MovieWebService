package com.myMovies.MoviesWeb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "movie_genres")
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenre;

    @Column(name = "genre")
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }
}
