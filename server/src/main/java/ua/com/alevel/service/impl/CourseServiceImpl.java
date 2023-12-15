package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.persistence.repository.CourseRepository;
import ua.com.alevel.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
    }
}
