package com.myMovies.MoviesWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@Setter
@NoArgsConstructor
public class Actor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "actor_movie",
            joinColumns = @JoinColumn(name="actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnore
    private List<Movie> movies;

    public Actor(String firstName, String lastName, int age, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.movies = movies;
    }

    public void addMovie(Movie movie){
        if(this.movies == null) this.movies = new ArrayList<>();
        this.movies.add(movie);
    }

    public void removeMovie(Movie movie){
        if(this.movies != null) this.movies.remove(movie);
    }
}
