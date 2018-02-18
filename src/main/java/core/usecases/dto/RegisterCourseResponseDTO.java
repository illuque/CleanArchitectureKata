package core.usecases.dto;

import core.usecases.dto.errors.RegistrationError;

public class RegisterCourseResponseDTO {
    private boolean success;
    private RegistrationError error;
    private String message;

    public RegisterCourseResponseDTO(boolean success, RegistrationError error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public RegistrationError getError() {
        return this.error;
    }
}
