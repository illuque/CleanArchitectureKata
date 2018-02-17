package unit.gateways;

import gateways.repository.CourseRepositoryMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unit.core.entities.Course;
import utils.TestUtils;

import java.util.List;

public class CourseRepositoryTest {

    private CourseRepositoryMemory courseRepository = new CourseRepositoryMemory();

    @Before
    public void seed() {
        TestUtils.seedTest();
    }

    @Test
    public void getByCode_shouldNotFindAnyCourse() {
        Course course = courseRepository.getByCode("fake");
        Assert.assertNull(course);
    }

    @Test
    public void getByCode_shouldFindACourse() {
        Course course = courseRepository.getByCode("fake");
        Assert.assertNull(course);
    }

    @Test
    public void getByCode_shouldFindAllCourses() {
        List<Course> allCourses = courseRepository.getAll();
        Assert.assertNotNull(allCourses);
        Assert.assertEquals(allCourses.size(), 1);
        Assert.assertEquals(allCourses.get(0).getCode(), CourseRepositoryMemory.TEST_COURSE_ID);
    }

}
