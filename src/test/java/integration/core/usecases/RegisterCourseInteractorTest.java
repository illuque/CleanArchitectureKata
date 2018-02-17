package integration.core.usecases;

import unit.core.usecases.RegisterCourseInteractor;
import unit.core.usecases.dto.RegisterCourseRequestDTO;
import unit.core.usecases.dto.RegisterCourseResponseDTO;
import gateways.auth.AuthServiceMemory;
import gateways.repository.CourseRepositoryMemory;
import gateways.repository.StudentRepositoryMemory;
import org.junit.Assert;
import org.junit.Test;

/*
    KATA

    This tests are very fast, since they use in memory gateways
    This test could also use a mocked gateways dedicated solely to tests, but in this case I reuse memory gateways
    This way we can unit test the UseCases without going towards Database, but an integration test using Database is
    advisable
 */
public class RegisterCourseInteractorTest {

    // TODO:1

}
