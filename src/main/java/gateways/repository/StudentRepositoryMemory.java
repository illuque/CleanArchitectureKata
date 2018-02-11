package gateways.repository;

import core.usecases.contracts.gateways.repository.IStudentRepository;
import core.entities.Course;
import core.entities.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepositoryMemory implements IStudentRepository {

    private static final HashMap<String, Student> STUDENT_STORY;

    public Student getById(String id) {
        return STUDENT_STORY.get(id);
    }

    public void save(Student student) {
        STUDENT_STORY.put(student.getId(), student);
    }

    static {
        STUDENT_STORY = new HashMap<>();

        final String SPIDERMAN_CODE = "Spiderman";
        List<Course> courseList = new ArrayList<>();
        courseList.add(CourseRepositoryMemory.COURSE_STORE.get("JAVA101"));
        STUDENT_STORY.put(SPIDERMAN_CODE, new Student(SPIDERMAN_CODE, "Peter", "Parker", courseList));

        final String BATMAN_CODE = "Batman";
        STUDENT_STORY.put(BATMAN_CODE, new Student(BATMAN_CODE, "Bruce", "Wayne", null));

        final String SUPERMAN_CODE = "Superman";
        STUDENT_STORY.put(SUPERMAN_CODE, new Student(SUPERMAN_CODE, "Clark", "Kent", null));
    }

}
