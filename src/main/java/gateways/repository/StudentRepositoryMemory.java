package gateways.repository;

import unit.core.contracts.gateways.repository.IStudentRepository;
import unit.core.entities.Course;
import unit.core.entities.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepositoryMemory implements IStudentRepository {

    private static HashMap<String, Student> STUDENT_STORE;

    //
    // API
    //

    public Student getById(String id) {
        return STUDENT_STORE.get(id);
    }

    public void save(Student student) {
        STUDENT_STORE.put(student.getId(), student);
    }

    // Test stuff

    /**
     * Exists only for testing purposes and taking into account that this is a dummy memory database
     * In a real world example with an actual database, this would be substituted by direct queries to check results
     * without calling tested or any other repo method
     */
    public static Student getTestStudentFromStore(String studentId) {
        Student studentToClone = STUDENT_STORE.get(studentId);
        return new Student(studentToClone.getId(), studentToClone.getFirstName(), studentToClone.getLastName(), studentToClone.getRegisteredCourses());
    }

    public static void seedTest(String studentId) {
        STUDENT_STORE = new HashMap<>();
        STUDENT_STORE.put(studentId, new Student(studentId, "Test", "User", null));
    }

    //
    // Develop stuff
    //

    public static void seedDev() {
        STUDENT_STORE = new HashMap<>();

        final String SPIDERMAN_ID = "Spiderman";
        List<Course> courseList = new ArrayList<>();
        /*
         * This is done for Demo purposes, to have a working example with a user already registered into a course
         */
        courseList.add(CourseRepositoryMemory.getExistingCourse());
        STUDENT_STORE.put(SPIDERMAN_ID, new Student(SPIDERMAN_ID, "Peter", "Parker", courseList));

        final String BATMAN_ID = "Batman";
        STUDENT_STORE.put(BATMAN_ID, new Student(BATMAN_ID, "Bruce", "Wayne", null));

        final String SUPERMAN_ID = "Superman";
        STUDENT_STORE.put(SUPERMAN_ID, new Student(SUPERMAN_ID, "Clark", "Kent", null));
    }

}
