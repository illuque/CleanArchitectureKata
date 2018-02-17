package unit.core.usecases.dto;

import unit.core.entities.Course;

import java.util.Date;

public class GetCourseResponseDTO {

    private String code;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    public GetCourseResponseDTO(Course course) {
        this.code = course.getCode();
        this.name = course.getName();
        this.description = course.getDescription();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

}
