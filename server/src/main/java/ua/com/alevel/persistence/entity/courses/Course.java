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

    private CourseDescription mainVersion;
    private CourseDescription englishVersion;
    private Teacher teacher;
    private List<Lesson> lessons;
    private LessonStatus status;
    public Course() {
        super();
        this.lessons = new ArrayList<>();
        this.status = LessonStatus.NOT_APPROVE;
    }

    public List<Lesson> addLesson(Lesson lesson){
        this.lessons.add(lesson);
        return lessons;
    }
    public CourseDescription getMainVersion() {
        return mainVersion;
    }

    public void setMainVersion(CourseDescription mainVersion) {
        this.mainVersion = mainVersion;
    }

    public CourseDescription getEnglishVersion() {
        return englishVersion;
    }

    public void setEnglishVersion(CourseDescription englishVersion) {
        this.englishVersion = englishVersion;
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
