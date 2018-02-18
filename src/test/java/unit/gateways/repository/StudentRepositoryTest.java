package unit.gateways.repository;

import gateways.repository.StudentRepositoryMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import core.entities.Student;
import utils.TestUtils;

public class StudentRepositoryTest {

    private StudentRepositoryMemory studentRepository = new StudentRepositoryMemory();

    @Before
    public void seed() {
        TestUtils.seedTest();
    }

    @Test
    public void getById_shouldNotFindAnyStudent() {
        Student student = studentRepository.getById("fake");
        Assert.assertNull(student);
    }

    @Test
    public void getById_shouldFindAStudent() {
        Student student = studentRepository.getById(TestUtils.TEST_STUDENT_ID);
        Assert.assertNotNull(student);
    }

    @Test
    public void save_shouldSaveModifiedStudent() {
        Student studentToModify = StudentRepositoryMemory.getTestStudentFromStore(TestUtils.TEST_STUDENT_ID);

        String newLastName = "Goanda";
        studentToModify.setLastName(newLastName);

        studentRepository.save(studentToModify);

        Student studentModified = StudentRepositoryMemory.getTestStudentFromStore(TestUtils.TEST_STUDENT_ID);
        Assert.assertEquals(studentModified.getLastName(), studentToModify.getLastName());
    }

}
