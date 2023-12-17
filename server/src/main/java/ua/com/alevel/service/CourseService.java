package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.courses.Course;

import java.util.List;

public interface CourseService extends BaseService<Course> {

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course findCourseById(String id);

    List<Course> findAll();
}
