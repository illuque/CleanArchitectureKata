package core.usecases.dto.errors;

import core.entities.RegistrationResult;

public enum RegistrationError {

    USER_NOT_FOUND,
    USER_NOT_AUTHENTICATED,

    COURSE_NOT_FOUND,

    REGISTRATION_ALREADY_REGISTERED,
    REGISTRATION_OUT_OF_TIME,

    UNKNOWN();

    public static RegistrationError fromRegistrationResult(RegistrationResult result) {
        if (result == RegistrationResult.ALREADY_REGISTERED) return REGISTRATION_ALREADY_REGISTERED;
        if (result == RegistrationResult.OUT_OF_TIME) return REGISTRATION_OUT_OF_TIME;
        return UNKNOWN;
    }

}
