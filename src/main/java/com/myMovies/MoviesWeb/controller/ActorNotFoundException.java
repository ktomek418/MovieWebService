package com.myMovies.MoviesWeb.controller;

public class ActorNotFoundException extends Exception{
    public ActorNotFoundException() {
        super("Actor with this id doesn't exist");
    }

    public ActorNotFoundException(String message) {
        super(message);
    }

    public ActorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ActorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
