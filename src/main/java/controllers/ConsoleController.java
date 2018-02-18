package controllers;

import core.usecases.dto.GetAllCoursesResponseDTO;
import core.usecases.dto.RegisterCourseRequestDTO;
import core.usecases.dto.RegisterCourseResponseDTO;
import core.usecases.GetAllCoursesInteractor;
import core.usecases.RegisterCourseInteractor;
import gateways.auth.AuthServiceMemory;
import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;

public class ConsoleController {

    /*
        KATA

        Init gateways, in a real app these would get wired up via Dependency Injection & IoC
    */
    private static final AuthServiceMemory authService = new AuthServiceMemory();
    private static final CourseRepositoryMemory courseRepository = new CourseRepositoryMemory();
    private static final StudentRepositoryMemory studentRepository = new StudentRepositoryMemory();

    public static GetAllCoursesResponseDTO getAllCourses() {
        return new GetAllCoursesInteractor(courseRepository).handle();
    }

    public static String registerCourse(String studentId, String courseCode) {

        /*
            KATA

            Here we're connecting our app framework layer with our Use Case Interactors
            This would typically be implemented in a Controller Action in an MVC context
        */

        // 1. instantiate Course Registration Use Case injecting Gateways implemented in this layer
        RegisterCourseInteractor courseRegistrationRequestUseCase = new RegisterCourseInteractor(authService, studentRepository, courseRepository);

        // 2. create the request message passing with the target student id and a list of selected course codes
        String inputCourseCode = courseCode.toUpperCase();
        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO(studentId, inputCourseCode);

        // 3. call the use case and store the response
        RegisterCourseResponseDTO responseMessage = courseRegistrationRequestUseCase.handle(useCaseRequestMessage);

        String message = responseMessage.getMessage();
        if (!responseMessage.isSuccess()) {
            message = "Error => " + responseMessage.getMessage();
        }

        return message;
    }

}
