package unit.core.entities;

import utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/*
    KATA

    This tests are very fast and they do not depend on external entities
    We are testing the CORE business logic without external dependencies
 */
public class CourseTest {

    // TODO:1 pasar toda la batería de tests

    @Test
    public void registerIsNotOpenWithin5DaysOfStartDate() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(3);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("BIOL-1507EL", "Biology II", "Biology course, level II", courseStartDate, courseEndDate);

        Assert.assertFalse(course.isRegisterOpen(5));
    }

    @Test
    public void registerIsNotOpenExactly5DaysOfStartDate() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(5);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("BIOL-1507EL", "Biology II", "Biology course, level II", courseStartDate, courseEndDate);

        Assert.assertFalse(course.isRegisterOpen(5));
    }

    @Test
    public void registerIsOpen10DaysOfStartDate() {
        Date courseStartDate = TestUtils.getTodayPlusNDays(11);
        Date courseEndDate = TestUtils.getTodayPlusNDays(100);
        Course course = new Course("BIOL-1507EL", "Biology II", "Biology course, level II", courseStartDate, courseEndDate);

        Assert.assertTrue(course.isRegisterOpen(5));
    }

}
