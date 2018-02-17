package unit.core.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student {

    private String id;
    private String firstName;
    private String lastName;

    private List<Course> registeredCourses;

    public Student(String id, String firstName, String lastName, List<Course> registeredCourses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredCourses = registeredCourses != null ? registeredCourses : new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getRegisteredCourses() {
        return this.registeredCourses;
    }

    // TODO:1 Check all Kata comments
    /*
        KATA

        Question: Is this domain logic or should be in a useCase?
      */

    public RegistrationResult registerForCourse(final Course course) {
        if (isAlreadyRegistered(course)) {
            return RegistrationResult.ALREADY_REGISTERED;
        }

        this.registeredCourses.add(course);

        return RegistrationResult.SUCCESS;
    }

    // Private region

    private boolean isAlreadyRegistered(Course course) {
        return checkCourseIncludedInList(course, this.registeredCourses);
    }

    private boolean checkCourseIncludedInList(Course course, List<Course> courseList) {
        Optional<Course> courseFound = courseList
                .stream()
                .filter(c -> c.getCode().equals(course.getCode()))
                .findFirst();

        return courseFound.isPresent();
    }

}
