package unit.core.usecases.dto;

import unit.core.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class GetAllCoursesResponseDTO {

    private List<GetCourseResponseDTO> courseList;

    public GetAllCoursesResponseDTO(List<Course> courseList) {
        super();
        this.courseList = new ArrayList<>();
        courseList.forEach(c -> this.courseList.add(new GetCourseResponseDTO(c)));
    }

    public List<GetCourseResponseDTO> getCourseList() {
        return this.courseList;
    }

}
