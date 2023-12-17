package ua.com.alevel.web.dto.requests;

import org.modelmapper.ModelMapper;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.courses.CourseDescription;
import ua.com.alevel.persistence.entity.courses.LessonDescription;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.web.dto.Block;

import java.util.Map;

public class CourseRequestDto extends RequestDto{

    private String teacherEmail;
    private CourseDescriptionDto mainVersion;
    private CourseDescriptionDto englishVersion;
    private Languages courseInLanguage;

    public Languages getCourseInLanguage() {
        return courseInLanguage;
    }

    public void setCourseInLanguage(Languages courseInLanguage) {
        this.courseInLanguage = courseInLanguage;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public CourseDescriptionDto getMainVersion() {
        mainVersion.setLanguages(courseInLanguage);
        return mainVersion;
    }

    public void setMainVersion(CourseDescriptionDto mainVersion) {
        this.mainVersion = mainVersion;
    }

    public CourseDescriptionDto getEnglishVersion() {
        englishVersion.setLanguages(Languages.ENGLISH);
        return englishVersion;
    }

    public void setEnglishVersion(CourseDescriptionDto englishVersion) {
        this.englishVersion = englishVersion;
    }
}
