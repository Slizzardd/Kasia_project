package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.courses.Lesson;

@Repository
public interface LessonRepository extends BaseRepository<Lesson> {

    Long countAllByCourseId(String courseId);
}
