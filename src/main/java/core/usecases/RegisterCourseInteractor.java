package core.usecases;

import core.usecases.contracts.dto.IRequestResponseHandler;
import core.usecases.contracts.gateways.auth.IAuthService;
import core.usecases.contracts.gateways.repository.ICourseRepository;
import core.usecases.contracts.gateways.repository.IStudentRepository;
import core.usecases.dto.RegisterCourseRequestDTO;
import core.usecases.dto.RegisterCourseResponseDTO;
import core.entities.Course;
import core.entities.RegisterResult;
import core.entities.Student;

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
        if (!this.authService.isAuthenticated()) {
            return new RegisterCourseResponseDTO(false, "Operation failed, not authenticated.");
        }

        Student student = this.studentRepository.getById(request.getStudentId());
        if (student == null) {
            return getStudentNotFoundResponse(request);
        }

        Course course = this.courseRepository.getByCode(request.getSelectedCourseCode());
        if (course == null) {
            return getCourseNotFoundResponse(request);
        }

        RegisterResult registerResult = student.registerForCourse(course);
        if (registerResult != RegisterResult.SUCCESS) {
            return getRegisterErrorResponse(registerResult);
        }

        studentRepository.save(student);

        return new RegisterCourseResponseDTO(true, "Successfully registered!");
    }

    private RegisterCourseResponseDTO getStudentNotFoundResponse(RegisterCourseRequestDTO request) {
        String message = String.format("Operation failed, student not found [%s]", request.getStudentId());
        return new RegisterCourseResponseDTO(false, message);
    }

    private RegisterCourseResponseDTO getCourseNotFoundResponse(RegisterCourseRequestDTO request) {
        String message = String.format("Operation failed, course not found [%s]", request.getSelectedCourseCode());
        return new RegisterCourseResponseDTO(false, message);
    }

    private RegisterCourseResponseDTO getRegisterErrorResponse(RegisterResult registerResult) {
        String message;
        if (registerResult == RegisterResult.ALREADY_REGISTERED) {
            message = "Already registered in this course";
        } else if (registerResult == RegisterResult.OUT_OF_TIME) {
            message = "Period to register has passed";
        } else {
            message = "Unknown error";
        }
        return new RegisterCourseResponseDTO(false, message);
    }


}
