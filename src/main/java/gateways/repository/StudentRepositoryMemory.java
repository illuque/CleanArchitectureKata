package gateways.repository;

import unit.core.contracts.gateways.repository.IStudentRepository;
import unit.core.entities.Course;
import unit.core.entities.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepositoryMemory implements IStudentRepository {

    private static HashMap<String, Student> STUDENT_STORE;

    public Student getById(String id) {
        return STUDENT_STORE.get(id);
    }

    public void save(Student student) {
        STUDENT_STORE.put(student.getId(), student);
    }

    // Testing stuff

    /**
     * Exists only for testing purposes and taking into account that this is a dummy memory database
     * In a real database, this would be substituted by direct queries to check results without calling other repo methods
     */
    public static Student getTestStudentFromStore() {
        Student studentToClone = STUDENT_STORE.get(TEST_STUDENT_ID);
        return new Student(studentToClone.getId(), studentToClone.getFirstName(), studentToClone.getLastName(), studentToClone.getRegisteredCourses());
    }

    public static void seedTest() {
        STUDENT_STORE = new HashMap<>();
        STUDENT_STORE.put(TEST_STUDENT_ID, new Student(TEST_STUDENT_ID, "Test", "User", null));
    }

    public static final String TEST_STUDENT_ID = "TestStudent";

    //
    // Develop stuff
    //

    public static void seedDev() {
        STUDENT_STORE = new HashMap<>();

        final String SPIDERMAN_ID = "Spiderman";
        List<Course> courseList = new ArrayList<>();
        courseList.add(CourseRepositoryMemory.COURSE_STORE.get("JAVA101"));
        STUDENT_STORE.put(SPIDERMAN_ID, new Student(SPIDERMAN_ID, "Peter", "Parker", courseList));

        final String BATMAN_ID = "Batman";
        STUDENT_STORE.put(BATMAN_ID, new Student(BATMAN_ID, "Bruce", "Wayne", null));

        final String SUPERMAN_ID = "Superman";
        STUDENT_STORE.put(SUPERMAN_ID, new Student(SUPERMAN_ID, "Clark", "Kent", null));
    }

}
