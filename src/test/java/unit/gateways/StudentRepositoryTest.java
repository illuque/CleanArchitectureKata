package unit.gateways;

import gateways.repository.StudentRepositoryMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unit.core.entities.Student;
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
        Student student = studentRepository.getById(StudentRepositoryMemory.TEST_STUDENT_ID);
        Assert.assertNotNull(student);
    }

    @Test
    public void save_shouldSaveModifiedStudent() {
        Student studentToModify = StudentRepositoryMemory.getTestStudentFromStore();

        String newLastName = "Goanda";
        studentToModify.setLastName(newLastName);

        studentRepository.save(studentToModify);

        Student studentModified = StudentRepositoryMemory.getTestStudentFromStore();
        Assert.assertEquals(studentModified.getLastName(), studentToModify.getLastName());
    }

}
