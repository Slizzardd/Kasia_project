package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.courses.Course;

public interface CourseService extends BaseService<Course> {

    Course createCourse(Course course);

    Course findCourseById(String id);
}
