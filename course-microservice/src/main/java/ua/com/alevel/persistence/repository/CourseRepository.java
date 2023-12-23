package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.courses.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course> {
}
