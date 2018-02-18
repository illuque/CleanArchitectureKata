package utils;

import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {

    public static final String TEST_STUDENT_ID = "TestStudent";

    public static final String TEST_COURSE_ID = "TestCourse";

    public static void seedTest() {
        CourseRepositoryMemory.seedTest(TEST_COURSE_ID);
        StudentRepositoryMemory.seedTest(TEST_STUDENT_ID);
    }

    public static Date getTodayPlusNDays(int days) {
        Calendar dummyCalendar = Calendar.getInstance();
        dummyCalendar.add(Calendar.DATE, days);
        return dummyCalendar.getTime();
    }

}
