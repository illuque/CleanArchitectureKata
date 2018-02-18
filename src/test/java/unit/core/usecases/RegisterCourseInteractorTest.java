package unit.core.usecases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import unit.core.contracts.gateways.auth.IAuthService;
import unit.core.contracts.gateways.repository.ICourseRepository;
import unit.core.contracts.gateways.repository.IStudentRepository;
import unit.core.entities.Course;
import unit.core.entities.Student;
import unit.core.usecases.dto.RegisterCourseRequestDTO;
import unit.core.usecases.dto.RegisterCourseResponseDTO;
import unit.core.usecases.dto.errors.RegistrationError;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
    KATA

    This tests are very fast, since they use just mocked repositories and authService
    We are unit testing the UseCases without going towards Database, but an integration test using Database is required
 */
public class RegisterCourseInteractorTest {

    // TODO:2 This test is not truly "unit" because it calls Entities logic that is not mocked, check how to do that

    private static IAuthService mockedAuthService;
    private static ICourseRepository mockedCourseRepository;
    private static IStudentRepository mockedStudentRepository;

    private static final List<Object> MOCKS = new ArrayList<>();

    static {
        MOCKS.add(mockedAuthService = mock(IAuthService.class));
        MOCKS.add(mockedCourseRepository = mock(ICourseRepository.class));
        MOCKS.add(mockedStudentRepository = mock(IStudentRepository.class));
    }

    @After
    public void unMock() {
        for (Object mock : MOCKS) {
            Mockito.reset(mock);
        }
    }

    @Test
    public void shouldFailRegistrationStudentNotAuthenticated() {
        this.stubAuthServiceIsAuthenticatedReturnFalse();
        this.stubStudentRepositoryGetByIdReturnNull();
        this.stubCourseRepositoryGetByCodeReturnValid();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertFalse(registerResult.isSuccess());
        Assert.assertEquals(registerResult.getError(), RegistrationError.USER_NOT_AUTHENTICATED);
    }

    @Test
    public void shouldFailRegistrationStudentNotFound() {
        this.stubAuthServiceIsAuthenticatedReturnTrue();
        this.stubStudentRepositoryGetByIdReturnNull();
        this.stubCourseRepositoryGetByCodeReturnValid();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertFalse(registerResult.isSuccess());
        Assert.assertEquals(registerResult.getError(), RegistrationError.USER_NOT_FOUND);
    }

    @Test
    public void shouldFailRegistrationCourseNotFound() {
        this.stubAuthServiceIsAuthenticatedReturnTrue();
        this.stubStudentRepositoryGetByIdReturnValid();
        this.stubCourseRepositoryGetByCodeReturnNull();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertFalse(registerResult.isSuccess());
        Assert.assertEquals(registerResult.getError(), RegistrationError.COURSE_NOT_FOUND);
    }

    @Test
    public void shouldFailRegistrationOutOfTime() {
        // TODO:2 Check how to test this, to be truly an unit test we would need to mock all Student and Course instances, alreadyRegistered() is allocated in Student Entity and isRegisterOpen() in Course Entity

    }

    @Test
    public void shouldFailRegistrationAlreadyRegistered() {
        // TODO:2 Check how to test this, to be truly an unit test we would need to mock all Student instances, alreadyRegistered() is allocated in Student Entity
    }

    @Test
    public void shouldSucceedRegistrationWhenValidStudentAndCourse() {
        this.stubAuthServiceIsAuthenticatedReturnTrue();
        this.stubStudentRepositoryGetByIdReturnValid();
        this.stubCourseRepositoryGetByCodeReturnValid();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertTrue(registerResult.isSuccess());
        Assert.assertNull(registerResult.getError());
    }

    // Private methods

    private RegisterCourseResponseDTO callRegisterCourseUseCase() {
        RegisterCourseInteractor registerUseCase = new RegisterCourseInteractor(mockedAuthService, mockedStudentRepository, mockedCourseRepository);

        RegisterCourseRequestDTO useCaseRequestMessage = new RegisterCourseRequestDTO("Spiderman", "CourseThatRules");
        return registerUseCase.handle(useCaseRequestMessage);
    }

    // Stubs

    private void stubAuthServiceIsAuthenticatedReturnFalse() {
        when(mockedAuthService.isAuthenticated(any(String.class))).thenReturn(false);
    }

    private void stubAuthServiceIsAuthenticatedReturnTrue() {
        when(mockedAuthService.isAuthenticated(any(String.class))).thenReturn(true);
    }

    private void stubStudentRepositoryGetByIdReturnNull() {
        when(mockedStudentRepository.getById(any(String.class))).thenReturn(null);
    }

    private void stubStudentRepositoryGetByIdReturnValid() {
        Student student = new Student("Spiderman", "Peter", "Parker", null);
        when(mockedStudentRepository.getById(any(String.class))).thenReturn(student);
    }

    private void stubCourseRepositoryGetByCodeReturnNull() {
        when(mockedCourseRepository.getByCode(any(String.class))).thenReturn(null);
    }

    private void stubCourseRepositoryGetByCodeReturnValid() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(10);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("CourseThatRules", "Course That Rules", "The most ruling course you've ever seen", courseStartDate, courseEndDate);
        when(mockedCourseRepository.getByCode(any(String.class))).thenReturn(course);
    }

}
