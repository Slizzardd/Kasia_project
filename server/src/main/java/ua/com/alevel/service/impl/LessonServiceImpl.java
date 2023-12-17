package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.courses.Lesson;
import ua.com.alevel.persistence.repository.LessonRepository;
import ua.com.alevel.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Long findAllLessonsByCourseId(String courseId) {
        return lessonRepository.countAllByCourseId(courseId);
    }
}
