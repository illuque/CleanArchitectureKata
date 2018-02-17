package unit.core.usecases;

import unit.core.contracts.dto.IResponseHandler;
import unit.core.contracts.gateways.repository.ICourseRepository;
import unit.core.usecases.dto.GetAllCoursesResponseDTO;
import unit.core.entities.Course;

import java.util.List;

public class GetAllCoursesInteractor implements IResponseHandler<GetAllCoursesResponseDTO> {

    private ICourseRepository courseRepository;

    public GetAllCoursesInteractor(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public GetAllCoursesResponseDTO handle() {
        List<Course> allCourses = courseRepository.getAll();
        return new GetAllCoursesResponseDTO(allCourses);
    }

}

