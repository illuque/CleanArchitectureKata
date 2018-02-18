package unit.gateways.repository;

import core.entities.Course;
import gateways.repository.CourseRepositoryMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        Course course = courseRepository.getByCode(TestUtils.TEST_COURSE_ID);
        Assert.assertNotNull(course);
        Assert.assertEquals(course.getCode(), TestUtils.TEST_COURSE_ID);
    }

    @Test
    public void getAll_shouldFindAllCourses() {
        List<Course> allCourses = courseRepository.getAll();
        Assert.assertNotNull(allCourses);
        Assert.assertEquals(allCourses.size(), 1);
        Assert.assertEquals(allCourses.get(0).getCode(), TestUtils.TEST_COURSE_ID);
    }

}
