package ru.kosmos.restaurantvoting.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor()
public class AppException extends RuntimeException {

    private final String msgCode;
    private final ErrorType type;

}