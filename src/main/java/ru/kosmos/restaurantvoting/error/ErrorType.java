package ru.kosmos.restaurantvoting.error;

import org.springframework.http.HttpStatus;

public enum ErrorType {

    //  http://stackoverflow.com/a/22358422/548473
    APP_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR(HttpStatus.CONFLICT),
    VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY),
    WRONG_REQUEST(HttpStatus.NOT_FOUND);

    private final HttpStatus status;

    ErrorType(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
