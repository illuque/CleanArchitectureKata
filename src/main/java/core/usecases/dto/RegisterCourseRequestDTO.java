package core.usecases.dto;

public class RegisterCourseRequestDTO {

    private String studentId;
    private String selectedCourseCode;

    public RegisterCourseRequestDTO(String studentId, String selectedCourseCode) {
        this.studentId = studentId;
        this.selectedCourseCode = selectedCourseCode;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getSelectedCourseCode() {
        return this.selectedCourseCode;
    }
}
