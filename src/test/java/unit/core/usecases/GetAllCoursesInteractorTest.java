package unit.core.usecases;

import core.contracts.gateways.repository.ICourseRepository;
import core.entities.Course;
import core.usecases.GetAllCoursesInteractor;
import core.usecases.dto.GetAllCoursesResponseDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllCoursesInteractorTest {

    private static ICourseRepository mockedCourseRepository;

    private static final List<Object> mocks = new ArrayList<>();

    private static final String TEST_COURSE_ID = "TestCourse";

    static {
        mocks.add(mockedCourseRepository = mock(ICourseRepository.class));
    }

    @After
    public void unMock() {
        for (Object mock : mocks) {
            Mockito.reset(mock);
        }
    }

    @Test
    public void shouldGetNoCoursesWhenEmptyCourseList() {
        this.stubCourseRepositoryGetAllReturnEmpty();

        GetAllCoursesResponseDTO getAllCoursesResult = new GetAllCoursesInteractor(mockedCourseRepository).handle();

        Assert.assertNotNull(getAllCoursesResult.getCourseList());
        Assert.assertEquals(getAllCoursesResult.getCourseList().size(), 0);
    }

    @Test
    public void shouldGetAllCoursesWhenNotEmptyList() {
        this.stubCourseRepositoryGetAllReturnOne();

        GetAllCoursesResponseDTO getAllCoursesResult = new GetAllCoursesInteractor(mockedCourseRepository).handle();

        Assert.assertNotNull(getAllCoursesResult.getCourseList());
        Assert.assertEquals(getAllCoursesResult.getCourseList().size(), 1);
        Assert.assertEquals(getAllCoursesResult.getCourseList().get(0).getCode(), TEST_COURSE_ID);
    }

    private void stubCourseRepositoryGetAllReturnEmpty() {
        when(mockedCourseRepository.getAll()).thenReturn(new ArrayList<>());
    }

    private void stubCourseRepositoryGetAllReturnOne() {
        Course dummyCourse = new Course(TEST_COURSE_ID, "Test Course", "Test Course for Testing", new Date(), new Date());
        List<Course> courseList = new ArrayList<>();
        courseList.add(dummyCourse);

        when(mockedCourseRepository.getAll()).thenReturn(courseList);
    }

}
