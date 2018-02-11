package core.usecases;

import core.usecases.contracts.dto.IResponseHandler;
import core.usecases.contracts.gateways.repository.ICourseRepository;
import core.usecases.dto.GetAllCoursesResponseDTO;
import core.entities.Course;

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

