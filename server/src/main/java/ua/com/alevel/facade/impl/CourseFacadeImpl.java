package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.web.dto.requests.CourseRequestDto;
import ua.com.alevel.web.dto.responses.CourseResponseDto;

@Service
public class CourseFacadeImpl implements CourseFacade {
    private final CourseService courseService;

    private final TeacherService teacherService;

    public CourseFacadeImpl(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto req) {
        Course course = new Course();
        Teacher teacher = teacherService.findTeacherByUserEmail(req.getTeacherEmail());
        course.setTeacher(teacher);
        course.setTitle(req.getTitle());
        return new CourseResponseDto(courseService.createCourse(course));
    }
}
