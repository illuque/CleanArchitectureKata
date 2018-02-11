package core.usecases.dto;

public class RegisterCourseResponseDTO {
    private boolean success;
    private String message;

    public RegisterCourseResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

}
