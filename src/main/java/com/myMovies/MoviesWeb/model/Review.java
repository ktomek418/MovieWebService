package com.myMovies.MoviesWeb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name= "title")
    private String title;
    @Column(name= "review")
    private String review;

    public Review(String title, String review) {
        this.title = title;
        this.review = review;
    }
}
