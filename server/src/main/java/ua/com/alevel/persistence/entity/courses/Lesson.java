package ua.com.alevel.persistence.entity.courses;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.persistence.types.LessonStatus;

import java.util.HashMap;
import java.util.Map;

@Document
public class Lesson extends BaseEntity {

    private String courseId;

    private LessonStatus status;

    private Map<Languages, LessonDescription> lesson;

    public Lesson() {
        this.lesson = new HashMap<>();
        this.status = LessonStatus.NOT_APPROVE;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }

    public Map<Languages, LessonDescription> getLesson() {
        return lesson;
    }

    public void setLesson(Map<Languages, LessonDescription> lesson) {
        this.lesson = lesson;
    }
}
