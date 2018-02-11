package core.entities;

import java.util.Calendar;
import java.util.Date;

public class Course {

    private String code;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    public Course(String code, String name, String description, Date startDate, Date endDate) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    boolean isRegisterOpen() {
        Date limitRegistrationDate = getLimitDateToRegister();
        return new Date().before(limitRegistrationDate);
    }

    private Date getLimitDateToRegister() {
        Calendar limitRegistrationCalendar = Calendar.getInstance();
        limitRegistrationCalendar.setTime(this.getStartDate());
        limitRegistrationCalendar.add(Calendar.DATE, -5);
        return limitRegistrationCalendar.getTime();
    }
}
