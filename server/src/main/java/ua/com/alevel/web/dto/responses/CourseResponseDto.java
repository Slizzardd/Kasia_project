package ua.com.alevel.web.dto.responses;

import org.modelmapper.ModelMapper;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.entity.courses.Course;
import ua.com.alevel.persistence.entity.courses.CourseDescription;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.web.dto.requests.CourseDescriptionDto;

import java.util.Map;

public class CourseResponseDto extends ResponseDto{

    private Long numOfLessons;
    private String teacherFirstName;
    private String teacherLastName;
    private String pathToImage;
    private CourseDescriptionDto mainVersion;
    private CourseDescriptionDto englishVersion;
    public CourseResponseDto(Course course) {
        setId(course.getId());
        setCreated(course.getCreated());
        setUpdated(course.getUpdated());
        this.mainVersion = new ModelMapper().map(course.getMainVersion(), CourseDescriptionDto.class);
        this.englishVersion = new ModelMapper().map(course.getEnglishVersion(), CourseDescriptionDto.class);
        this.pathToImage = "";
        User user = course.getTeacher().getUser();

        this.teacherFirstName = user.getFirstName();
        this.teacherLastName = user.getLastName();
    }

    public CourseResponseDto(Course course, Long countLessons) {
        setId(course.getId());
        setCreated(course.getCreated());
        setUpdated(course.getUpdated());
        this.mainVersion = new ModelMapper().map(course.getMainVersion(), CourseDescriptionDto.class);
        this.englishVersion = new ModelMapper().map(course.getEnglishVersion(), CourseDescriptionDto.class);
        this.numOfLessons = countLessons;
        this.pathToImage = "";
        User user = course.getTeacher().getUser();

        this.teacherFirstName = user.getFirstName();
        this.teacherLastName = user.getLastName();
    }


    public Long getNumOfLessons() {
        return numOfLessons;
    }

    public void setNumOfLessons(Long numOfLessons) {
        this.numOfLessons = numOfLessons;
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

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
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
}
