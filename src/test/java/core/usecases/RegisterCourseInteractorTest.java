package core.usecases;

import core.usecases.dto.RegisterCourseRequestDTO;
import core.usecases.dto.RegisterCourseResponseDTO;
import gateways.auth.AuthServiceMemory;
import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;
import org.junit.Assert;
import org.junit.Test;

/*
    KATA

    This tests are very fast, since they use in memory repository
    This test could also use a mocked repository dedicated solely to tests, but in this case I reuse memory repository
    This way we can unit test the UseCases without going towards Database, but an integration test using Database is
    advisable
 */
public class RegisterCourseInteractorTest {

    private static AuthServiceMemory AUTH_SERVICE = new AuthServiceMemory();
    private static CourseRepositoryMemory COURSE_REPOSITORY = new CourseRepositoryMemory();
    private static StudentRepositoryMemory STUDENT_REPOSITORY = new StudentRepositoryMemory();

    // TODO: Do not hardcode studentId's and courseId's as passing or failing, if they change in memory repository this will fail

    @Test
    public void shouldFailRegistrationStudentNotFound() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(AUTH_SERVICE, STUDENT_REPOSITORY, COURSE_REPOSITORY);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO("Goku", "JAVA101");
        RegisterCourseResponseDTO registerResult = registerUseCase.handle(useCaseRequestMessage);

        Assert.assertFalse(registerResult.isSuccess());
    }

    @Test
    public void shouldFailRegistrationCourseNotFound() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(AUTH_SERVICE, STUDENT_REPOSITORY, COURSE_REPOSITORY);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO("Spiderman", "FakeCourse");
        RegisterCourseResponseDTO registerResult = registerUseCase.handle(useCaseRequestMessage);

        Assert.assertFalse(registerResult.isSuccess());
    }

    @Test
    public void shouldFailRegistrationOutOfTime() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(AUTH_SERVICE, STUDENT_REPOSITORY, COURSE_REPOSITORY);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO("Spiderman", "JAVA201");
        RegisterCourseResponseDTO registerResult = registerUseCase.handle(useCaseRequestMessage);

        Assert.assertFalse(registerResult.isSuccess());
    }

    @Test
    public void shouldFailRegistrationAlreadyRegistered() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(AUTH_SERVICE, STUDENT_REPOSITORY, COURSE_REPOSITORY);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO("Spiderman", "JAVA101");
        RegisterCourseResponseDTO registerResult = registerUseCase.handle(useCaseRequestMessage);

        Assert.assertFalse(registerResult.isSuccess());
    }

    @Test
    public void shouldSucceedRegistrationWhenValidStudentAndCourse() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(AUTH_SERVICE, STUDENT_REPOSITORY, COURSE_REPOSITORY);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO("Spiderman", "MONGO101");
        RegisterCourseResponseDTO registerResult = registerUseCase.handle(useCaseRequestMessage);

        Assert.assertTrue(registerResult.isSuccess());
    }

}
