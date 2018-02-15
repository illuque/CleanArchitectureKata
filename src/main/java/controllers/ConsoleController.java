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

        Init gateways, in a real app these would get wired up via IoC
    */
    private static AuthServiceMemory AUTH_SERVICE = new AuthServiceMemory();
    private static CourseRepositoryMemory COURSE_REPOSITORY = new CourseRepositoryMemory();
    private static StudentRepositoryMemory STUDENT_REPOSITORY = new StudentRepositoryMemory();

    public static GetAllCoursesResponseDTO getAllCourses() {
        return new GetAllCoursesInteractor(COURSE_REPOSITORY).handle();
    }

    public static String registerCourse(String studentId, String courseCode) {

        /*
            KATA

            Here we're connecting our app framework layer with our Use Case Interactors
            This would typically go in a Controller Action in an MVC context
        */

        // 1. instantiate Course Registration Use Case injecting Gateways implemented in this layer
        RegisterCourseInteractor courseRegistrationRequestUseCase = new RegisterCourseInteractor(AUTH_SERVICE, STUDENT_REPOSITORY, COURSE_REPOSITORY);

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
