package integration.core.usecases;

import core.usecases.GetAllCoursesInteractor;
import core.usecases.dto.GetAllCoursesResponseDTO;
import gateways.repository.CourseRepositoryMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.TestUtils;

public class GetAllCoursesInteractorTest {

    private static final CourseRepositoryMemory courseRepository = new CourseRepositoryMemory();

    @Before
    public void seedTest() {
        TestUtils.seedTest();
    }

    @Test
    public void shouldGetAllCourses() {
        GetAllCoursesInteractor getAllCoursesUseCase = new GetAllCoursesInteractor(courseRepository);

        GetAllCoursesResponseDTO getAllCoursesResult = getAllCoursesUseCase.handle();

        Assert.assertNotNull(getAllCoursesResult.getCourseList());
        Assert.assertEquals(getAllCoursesResult.getCourseList().size(), 1);
        Assert.assertEquals(getAllCoursesResult.getCourseList().get(0).getCode(), TestUtils.TEST_COURSE_ID);
    }

}