package ru.kosmos.restaurantvoting.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class UpdateRestrictionException extends AppException {

    public static final String EXCEPTION_UPDATE_RESTRICTION = "exception.user.updateRestriction";

    public UpdateRestrictionException(String Message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, Message, ErrorAttributeOptions.of(MESSAGE));
    }

}
