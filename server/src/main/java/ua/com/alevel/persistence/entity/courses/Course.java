package ua.com.alevel.persistence.entity.courses;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.persistence.types.LessonStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
public class Course extends BaseEntity {

    private Map<Languages, String> title;
    private Teacher teacher;
    private List<Lesson> lessons;
    private LessonStatus status;
    public Course() {
        super();
        this.lessons = new ArrayList<>();
        this.status = LessonStatus.NOT_APPROVE;
        this.title = new HashMap<>();
    }

    public Map<Languages, String> getTitle() {
        return title;
    }

    public void setTitle(Map<Languages, String> title) {
        this.title = title;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }
}
