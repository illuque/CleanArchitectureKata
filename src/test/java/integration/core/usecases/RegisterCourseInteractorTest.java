package integration.core.usecases;

import gateways.auth.AuthServiceMemory;
import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unit.core.usecases.RegisterCourseInteractor;
import unit.core.usecases.dto.RegisterCourseRequestDTO;
import unit.core.usecases.dto.RegisterCourseResponseDTO;
import utils.TestUtils;

public class RegisterCourseInteractorTest {

    private static AuthServiceMemory authService = new AuthServiceMemory();
    private static CourseRepositoryMemory courseRepository = new CourseRepositoryMemory();
    private static StudentRepositoryMemory studentRepository = new StudentRepositoryMemory();

    @Before
    public void seedTest() {
        TestUtils.seedTest();
    }

    @Test
    public void shouldSucceedRegistrationWhenValidStudentAndCourse() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(authService, studentRepository, courseRepository);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO(TestUtils.TEST_STUDENT_ID, TestUtils.TEST_COURSE_ID);
        RegisterCourseResponseDTO registerResult = registerUseCase.handle(useCaseRequestMessage);

        Assert.assertTrue(registerResult.isSuccess());
        Assert.assertNull(registerResult.getError());
    }
}
