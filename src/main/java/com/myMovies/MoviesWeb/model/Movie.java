package com.myMovies.MoviesWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "idGenre")
    private Genre genre;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_movie")
    @JsonIgnore
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "actor_movie",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonIgnore
    private List<Actor> actors;

    public Movie(String title, Genre genre, List<Review> reviews, List<Actor> actors) {
        this.title = title;
        this.genre = genre;
        this.reviews = reviews;
        this.actors = actors;
    }

    public void addActor(Actor actor){
        if(this.actors == null) this.actors = new ArrayList<>();
        this.actors.add(actor);
    }

    public void removeActor(Actor actor){
        if(this.actors != null) this.actors.remove(actor);
    }

    public void addReview(Review review){
        if(this.reviews == null) this.reviews = new ArrayList<>();
        this.reviews.add(review);
    }


}
