package ru.kosmos.restaurantvoting.error;

public class VoteCantBeChangedException extends RuntimeException {

    public VoteCantBeChangedException(String message) {
        super(message);
    }

}
