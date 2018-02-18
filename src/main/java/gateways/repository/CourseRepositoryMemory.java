package gateways.repository;

import unit.core.contracts.gateways.repository.ICourseRepository;
import unit.core.entities.Course;

import java.util.*;

public class CourseRepositoryMemory implements ICourseRepository {

    private static HashMap<String, Course> COURSE_STORE;

    //
    // API
    //

    public Course getByCode(String code) {
        return COURSE_STORE.get(code);
    }

    public List<Course> getAll() {
        return new ArrayList<>(COURSE_STORE.values());
    }

    //
    // Testing stuff
    //

    public static void seedTest(String courseId) {
        COURSE_STORE = new HashMap<>();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 10);
        Date startDate = c.getTime();

        c.add(Calendar.DATE, 100);
        Date endDate = c.getTime();

        COURSE_STORE.put(courseId, new Course(courseId, "Test", "Test Course for Testing", startDate, endDate));
    }

    //
    // Develop stuff
    //

    /**
     * This method exists for Demo purposes, to have a working example with a user already registered into a course
     */
    static Course getExistingCourse() {
        return COURSE_STORE.get("JAVA101");
    }

    public static void seedDev() {
        Calendar c = Calendar.getInstance();

        COURSE_STORE = new HashMap<>();

        // Register available

        final String JAVA_101_CODE = "JAVA101";
        c.set(2018, 8, 1);
        Date startDate = c.getTime();
        c.add(Calendar.DATE, 365);
        Date endDate = c.getTime();

        COURSE_STORE.put(JAVA_101_CODE, new Course(JAVA_101_CODE, JAVA_101_CODE, "Java Introduction Course", startDate, endDate));

        final String MONGO_101_CODE = "MONGO101";
        COURSE_STORE.put(MONGO_101_CODE, new Course(MONGO_101_CODE, MONGO_101_CODE, "Mongo Introduction Course", startDate, endDate));

        final String GRAPHQL_101_CODE = "GRAPHQL101";
        COURSE_STORE.put(GRAPHQL_101_CODE, new Course(GRAPHQL_101_CODE, GRAPHQL_101_CODE, "GraphQL Introduction Course", startDate, endDate));

        // Register not available

        c.setTime(new Date());
        c.add(Calendar.DATE, 2);
        startDate = c.getTime();
        c.add(Calendar.DATE, 365);
        endDate = c.getTime();

        final String JAVA_201_CODE = "JAVA201";
        COURSE_STORE.put(JAVA_201_CODE, new Course(JAVA_201_CODE, JAVA_201_CODE, "Java Intermediate Course", startDate, endDate));

        final String MONGO_201_CODE = "MONGO201";
        COURSE_STORE.put(MONGO_201_CODE, new Course(MONGO_201_CODE, MONGO_201_CODE, "Mongo Intermediate Course", startDate, endDate));

        final String GRAPH_201_CODE = "GRAPHQL201";
        COURSE_STORE.put(GRAPH_201_CODE, new Course(GRAPH_201_CODE, "GraphQL 101", "GraphQL IntermediateF Course", startDate, endDate));
    }


}
