package core.contracts.gateways.repository;

import core.entities.Student;

public interface IStudentRepository {

    Student getById(String id);

    void save(Student student);

}
