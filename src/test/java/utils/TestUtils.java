package utils;

import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {

    public static void seedTest() {
        CourseRepositoryMemory.seedTest();
        StudentRepositoryMemory.seedTest();
    }

    public static Date getTodayPlusNDays(int days) {
        Calendar dummyCalendar = Calendar.getInstance();
        dummyCalendar.add(Calendar.DATE, days);
        return dummyCalendar.getTime();
    }

}
