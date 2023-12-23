package ua.com.alevel.persistence.entity.courses;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.types.LessonStatus;

import java.math.BigDecimal;
import java.util.List;

@Document
public class Course extends BaseEntity {

    private CourseDescription mainVersion;
    private CourseDescription englishVersion;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherId;
    private LessonStatus status;
    private BigDecimal price;

    public Course() {
        super();
        this.status = LessonStatus.NOT_APPROVE;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }
}
