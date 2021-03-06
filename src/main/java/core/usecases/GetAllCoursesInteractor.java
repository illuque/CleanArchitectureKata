package core.usecases;

import core.contracts.dto.IResponseHandler;
import core.contracts.gateways.repository.ICourseRepository;
import core.entities.Course;
import core.usecases.dto.GetAllCoursesResponseDTO;

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

