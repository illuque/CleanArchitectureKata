package unit.core.entities;

import core.entities.Course;
import org.junit.Assert;
import org.junit.Test;
import utils.TestUtils;

import java.util.Date;

/*
    KATA

    This tests are very fast and they do not depend on external entities
    We are testing the CORE business logic without external dependencies
 */
public class CourseTest {

    @Test
    public void isRegisterOpen_shouldBeNotOpenAfterLimitDay() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(3);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("BIOL-1507EL", "Biology II", "Biology course, level II", courseStartDate, courseEndDate);

        Assert.assertFalse(course.isRegisterOpen(5));
    }

    @Test
    public void isRegisterOpen_shouldBeNotOpenOnLimitDay() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(5);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("BIOL-1507EL", "Biology II", "Biology course, level II", courseStartDate, courseEndDate);

        Assert.assertFalse(course.isRegisterOpen(5));
    }

    @Test
    public void isRegisterOpen_shouldBeOpenBeforeLimitDay() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(11);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("BIOL-1507EL", "Biology II", "Biology course, level II", courseStartDate, courseEndDate);

        Assert.assertTrue(course.isRegisterOpen(5));
    }

}
