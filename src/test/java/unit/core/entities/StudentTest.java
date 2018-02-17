package unit.core.entities;

import utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    KATA

    This tests are very fast and they do not depend on external entities
    We are testing the CORE business logic without external dependencies
 */
public class StudentTest {

    @Test
    public void shouldFailRegistrationAlreadyRegistered() {
        Date startDate = TestUtils.getTodayPlusNDays(10);
        Course courseAlreadyRegistered = new Course("JAVA101", "JAVA II", "Java sophomore course", startDate, null);

        List<Course> courseList = new ArrayList<>();
        courseList.add(courseAlreadyRegistered);
        Student student = new Student("Test", "Test", "Test", courseList);

        RegistrationResult registerSuccess = student.registerForCourse(courseAlreadyRegistered);

        Assert.assertEquals(registerSuccess, RegistrationResult.ALREADY_REGISTERED);
    }

    @Test
    public void shouldSucceedRegistrationWhenValidCourse() {
        Date startDate = TestUtils.getTodayPlusNDays(10);
        Course courseAlreadyRegistered = new Course("MONGO101", "MONGO I", "Java freshman course", startDate, null);

        Student student = new Student("s1", "Son", "Goku", null);
        RegistrationResult registerSuccess = student.registerForCourse(courseAlreadyRegistered);

        Assert.assertEquals(registerSuccess, RegistrationResult.SUCCESS);
    }

}
