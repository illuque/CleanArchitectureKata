package core;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {

    public static Date getTodayPlusNDays(int days) {
        Calendar dummyCalendar = Calendar.getInstance();
        dummyCalendar.add(Calendar.DATE, days);
        return dummyCalendar.getTime();
    }

}
