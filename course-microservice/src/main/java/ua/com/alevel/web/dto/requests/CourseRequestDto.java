package ua.com.alevel.web.dto.requests;

import ua.com.alevel.web.dto.CourseDescriptionDto;

import java.math.BigDecimal;

public class CourseRequestDto extends RequestDto {

    private CourseDescriptionDto mainVersion;
    private CourseDescriptionDto englishVersion;
    private BigDecimal price;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherId;

    public CourseDescriptionDto getMainVersion() {
        return mainVersion;
    }

    public void setMainVersion(CourseDescriptionDto mainVersion) {
        this.mainVersion = mainVersion;
    }

    public CourseDescriptionDto getEnglishVersion() {
        return englishVersion;
    }

    public void setEnglishVersion(CourseDescriptionDto englishVersion) {
        this.englishVersion = englishVersion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
