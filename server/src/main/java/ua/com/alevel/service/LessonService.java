package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.courses.Lesson;

public interface LessonService extends BaseService<Lesson> {
    Lesson createLesson(Lesson lesson);
}
