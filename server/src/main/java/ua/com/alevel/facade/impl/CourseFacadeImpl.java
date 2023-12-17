package ua.com.alevel.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.persistence.entity.courses.CourseDescription;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.LessonService;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.web.dto.requests.CourseDescriptionDto;
import ua.com.alevel.web.dto.requests.CourseRequestDto;
import ua.com.alevel.web.dto.responses.CourseResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseFacadeImpl implements CourseFacade {
    private final CourseService courseService;

    private final TeacherService teacherService;

    private final LessonService lessonService;
    public CourseFacadeImpl(CourseService courseService, TeacherService teacherService, LessonService lessonService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.lessonService = lessonService;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto req) {
        Course course = new Course();
        Teacher teacher = teacherService.findTeacherByUserEmail(req.getTeacherEmail());
        course.setMainVersion(convertCourseDesToEntity(req.getMainVersion()));
        course.setEnglishVersion(convertCourseDesToEntity(req.getEnglishVersion()));
        course.setTeacher(teacher);
        return new CourseResponseDto(courseService.createCourse(course));
    }

    @Override
    public List<CourseResponseDto> findAllCourses() {
        List<Course> courses = courseService.findAll();
        List<CourseResponseDto> resultList = new ArrayList<>();
        for (Course course : courses){
            resultList.add(new CourseResponseDto(course, lessonService.findAllLessonsByCourseId(course.getId())));
        }
        return resultList;
    }


    private CourseDescription convertCourseDesToEntity(CourseDescriptionDto req){
        return new ModelMapper().map(req, CourseDescription.class);

    }
}
