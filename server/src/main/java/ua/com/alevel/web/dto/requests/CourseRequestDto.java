package ua.com.alevel.web.dto.requests;

import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.types.Languages;

import java.util.Map;

public class CourseRequestDto extends RequestDto{

    private Map<Languages, String> title;
    private String teacherEmail;

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public Map<Languages, String> getTitle() {
        return title;
    }

    public void setTitle(Map<Languages, String> title) {
        this.title = title;
    }
}
