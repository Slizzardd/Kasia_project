package ua.com.alevel.facade.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.persistence.entity.courses.CourseDescription;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.web.dto.CourseDescriptionDto;
import ua.com.alevel.web.dto.requests.CourseRequestDto;
import ua.com.alevel.web.dto.responses.CourseResponseDto;

@Service
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;

    public CourseFacadeImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto req) {
        Course course = new Course();

        course.setTeacherId(req.getTeacherId());
        course.setTeacherFirstName(req.getTeacherFirstName());
        course.setTeacherLastName(req.getTeacherLastName());
        course.setEnglishVersion(convertDtoToEntity(req.getEnglishVersion()));
        course.setMainVersion(convertDtoToEntity(req.getMainVersion()));
        course.setPrice(req.getPrice());

        return new CourseResponseDto(courseService.createCourse(course));
    }

    private CourseDescription convertDtoToEntity(CourseDescriptionDto req) {
        if (req == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(req, CourseDescription.class);
    }
}
