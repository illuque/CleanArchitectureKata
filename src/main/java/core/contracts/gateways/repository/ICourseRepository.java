package core.contracts.gateways.repository;

import core.entities.Course;

import java.util.List;

public interface ICourseRepository {

    Course getByCode(String code);

    List<Course> getAll();

}
