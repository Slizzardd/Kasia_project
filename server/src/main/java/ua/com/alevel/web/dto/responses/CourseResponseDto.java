package ua.com.alevel.web.dto.responses;

import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.persistence.types.Languages;

import java.util.Map;

public class CourseResponseDto extends ResponseDto{

    private Map<Languages, String> title;

    private String teacherEmail;
    private String teacherFirstName;
    private String teacherLastName;

    public CourseResponseDto(Course course) {
        setId(course.getId());
        setCreated(course.getCreated());
        setUpdated(course.getUpdated());
        this.title = course.getTitle();

        User user = course.getTeacher().getUser();

        this.teacherFirstName = user.getFirstName();
        this.teacherEmail = user.getEmail();
        this.teacherLastName = user.getLastName();
    }

    public Map<Languages, String> getTitle() {
        return title;
    }

    public void setTitle(Map<Languages, String> title) {
        this.title = title;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
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
}
