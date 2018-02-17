package unit.core.usecases;

import unit.core.contracts.dto.IRequestResponseHandler;
import unit.core.contracts.gateways.auth.IAuthService;
import unit.core.contracts.gateways.repository.ICourseRepository;
import unit.core.contracts.gateways.repository.IStudentRepository;
import unit.core.entities.Course;
import unit.core.entities.RegistrationResult;
import unit.core.entities.Student;
import unit.core.usecases.dto.RegisterCourseRequestDTO;
import unit.core.usecases.dto.RegisterCourseResponseDTO;
import unit.core.usecases.dto.errors.RegistrationError;

/*
    KATA

    This interactor just takes a request object as parameter, typically contains any data passed in from the outer
    input layer (typically the UI) and returns a response object. Both DTO types are dictated by the IRequestResponseHandler
    interface. All of our application specific logic for the use case will go into this method.f
 */
public class RegisterCourseInteractor implements IRequestResponseHandler<RegisterCourseRequestDTO, RegisterCourseResponseDTO> {

    /*
        KATA

        This gateways services are usually injected into the Use Case layer with dependency injection (IoC)
    */
    private IStudentRepository studentRepository;
    private ICourseRepository courseRepository;
    private IAuthService authService;

    public RegisterCourseInteractor(IAuthService authService, IStudentRepository studentRepository, ICourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.authService = authService;
    }

    public RegisterCourseResponseDTO handle(RegisterCourseRequestDTO request) {
        if (!this.authService.isAuthenticated(request.getStudentId())) {
            return getStudentNotAuthenticadedResponse(request);
        }

        Student student = this.studentRepository.getById(request.getStudentId());
        if (student == null) {
            return getStudentNotFoundResponse(request);
        }

        Course course = this.courseRepository.getByCode(request.getSelectedCourseCode());
        if (course == null) {
            return getCourseNotFoundResponse(request);
        }

        RegistrationResult registrationResult = student.registerForCourse(course);
        if (registrationResult != RegistrationResult.SUCCESS) {
            return getRegisterErrorResponse(registrationResult);
        }

        int courseOpenDaysBeforeStarting = 5;
        if (!course.isRegisterOpen(courseOpenDaysBeforeStarting)) {
            return getRegisterErrorResponse(RegistrationResult.OUT_OF_TIME);
        }

        studentRepository.save(student);

        return getSuccessResponse();
    }

    private RegisterCourseResponseDTO getStudentNotAuthenticadedResponse(RegisterCourseRequestDTO request) {
        String message = String.format("Operation failed, student not authenticated [%s]", request.getStudentId());
        return new RegisterCourseResponseDTO(false, RegistrationError.USER_NOT_AUTHENTICATED, message);
    }

    private RegisterCourseResponseDTO getStudentNotFoundResponse(RegisterCourseRequestDTO request) {
        String message = String.format("Operation failed, student not found [%s]", request.getStudentId());
        return new RegisterCourseResponseDTO(false, RegistrationError.USER_NOT_FOUND, message);
    }

    private RegisterCourseResponseDTO getCourseNotFoundResponse(RegisterCourseRequestDTO request) {
        String message = String.format("Operation failed, course not found [%s]", request.getSelectedCourseCode());
        return new RegisterCourseResponseDTO(false, RegistrationError.COURSE_NOT_FOUND, message);
    }

    private RegisterCourseResponseDTO getRegisterErrorResponse(RegistrationResult registrationResult) {
        String message;
        if (registrationResult == RegistrationResult.ALREADY_REGISTERED) {
            message = "Already registered in this course";
        } else if (registrationResult == RegistrationResult.OUT_OF_TIME) {
            message = "Period to register has passed";
        } else {
            message = "Unknown error";
        }
        return new RegisterCourseResponseDTO(false, RegistrationError.fromRegistrationResult(registrationResult), message);
    }

    private RegisterCourseResponseDTO getSuccessResponse() {
        return new RegisterCourseResponseDTO(true, null, "Successfully registered");
    }


}
