package ua.com.alevel.facade;

import ua.com.alevel.web.dto.requests.CourseRequestDto;
import ua.com.alevel.web.dto.responses.CourseResponseDto;

import java.util.List;

public interface CourseFacade extends BaseFacade<CourseRequestDto, CourseResponseDto> {

    CourseResponseDto createCourse(CourseRequestDto req);

    List<CourseResponseDto> findAllCourses();
}
