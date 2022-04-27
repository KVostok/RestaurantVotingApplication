package ru.kosmos.restaurantvoting.error;

import static ru.kosmos.restaurantvoting.error.ErrorType.VALIDATION_ERROR;

public class UpdateRestrictionException extends AppException {
    public static final String EXCEPTION_UPDATE_RESTRICTION = "exception.user.updateRestriction";

    public UpdateRestrictionException() {
        super(EXCEPTION_UPDATE_RESTRICTION, VALIDATION_ERROR);
    }
}
