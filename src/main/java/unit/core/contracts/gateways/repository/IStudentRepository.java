package unit.core.contracts.gateways.repository;

import unit.core.entities.Student;

public interface IStudentRepository {

    Student getById(String id);

    void save(Student student);

}
