package ru.kosmos.restaurantvoting.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
