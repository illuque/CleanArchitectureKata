package unit.core.usecases;

import core.contracts.gateways.repository.IStudentRepository;
import core.entities.Student;
import core.usecases.RegisterCourseInteractor;
import core.usecases.dto.RegisterCourseResponseDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import core.contracts.gateways.auth.IAuthService;
import core.contracts.gateways.repository.ICourseRepository;
import core.entities.Course;
import core.usecases.dto.RegisterCourseRequestDTO;
import core.usecases.dto.errors.RegistrationError;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
    KATA

    This tests are very fast, since they use just mocked repositories and external services
    We are unit testing the UseCases without going towards real databases and services, but an integration test using
    "real world" resources is also required (nevertheless, the number of integration tests will/should be smaller)
 */
public class RegisterCourseInteractorTest {

    // TODO:2 This test is not truly "unit" because it calls Entities logic that is not mocked, check how to do that

    private static IAuthService mockedAuthService;
    private static ICourseRepository mockedCourseRepository;
    private static IStudentRepository mockedStudentRepository;

    private static final List<Object> mocks = new ArrayList<>();

    static {
        mocks.add(mockedAuthService = mock(IAuthService.class));
        mocks.add(mockedCourseRepository = mock(ICourseRepository.class));
        mocks.add(mockedStudentRepository = mock(IStudentRepository.class));
    }

    @After
    public void unMock() {
        for (Object mock : mocks) {
            Mockito.reset(mock);
        }
    }

    @Test
    public void shouldFailRegistrationWhenStudentNotAuthenticated() {
        this.stubAuthServiceIsAuthenticatedReturnFalse();
        this.stubStudentRepositoryGetByIdReturnNull();
        this.stubCourseRepositoryGetByCodeReturnValid();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertFalse(registerResult.isSuccess());
        Assert.assertEquals(registerResult.getError(), RegistrationError.USER_NOT_AUTHENTICATED);
    }

    @Test
    public void shouldFailRegistrationWhenStudentNotFound() {
        this.stubAuthServiceIsAuthenticatedReturnTrue();
        this.stubStudentRepositoryGetByIdReturnNull();
        this.stubCourseRepositoryGetByCodeReturnValid();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertFalse(registerResult.isSuccess());
        Assert.assertEquals(registerResult.getError(), RegistrationError.USER_NOT_FOUND);
    }

    @Test
    public void shouldFailRegistrationWhenCourseNotFound() {
        this.stubAuthServiceIsAuthenticatedReturnTrue();
        this.stubStudentRepositoryGetByIdReturnValid();
        this.stubCourseRepositoryGetByCodeReturnNull();

        RegisterCourseResponseDTO registerResult = callRegisterCourseUseCase();

        Assert.assertFalse(registerResult.isSuccess());
        Assert.assertEquals(registerResult.getError(), RegistrationError.COURSE_NOT_FOUND);
    }

    @Test
    public void shouldFailRegistrationWhenOutOfTime() {
        // TODO:2 Check how to test this, to be truly an unit test we would need to mock all Student and Course instances, alreadyRegistered() is allocated in Student Entity and isRegisterOpen() in Course Entity

    }

    @Test
    public void shouldFailRegistrationWhenAlreadyRegistered() {
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
