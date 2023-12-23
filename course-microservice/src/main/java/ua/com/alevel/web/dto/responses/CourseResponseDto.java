package ua.com.alevel.web.dto.responses;

import org.modelmapper.ModelMapper;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.persistence.entity.courses.CourseDescription;
import ua.com.alevel.web.dto.CourseDescriptionDto;

import java.math.BigDecimal;

public class CourseResponseDto extends ResponseDto {

    private String teacherFirstName;
    private String teacherLastName;
    private CourseDescriptionDto mainVersion;
    private CourseDescriptionDto englishVersion;
    private BigDecimal price;

    public CourseResponseDto(Course course) {
        setId(course.getId());
        setCreated(course.getCreated());
        setUpdated(course.getUpdated());
        this.teacherFirstName = course.getTeacherFirstName();
        this.teacherLastName = course.getTeacherLastName();
        this.mainVersion = convertEntityToDto(course.getMainVersion());
        this.englishVersion = convertEntityToDto(course.getEnglishVersion());
        this.price = course.getPrice();
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

    private CourseDescriptionDto convertEntityToDto(CourseDescription req) {
        if (req == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(req, CourseDescriptionDto.class);
    }
}
