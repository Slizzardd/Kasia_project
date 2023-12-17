package ua.com.alevel.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.*;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields in serialization
public class UserResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<Ages> preferredAges;
    private LanguageLevel hisLanguageLevel;
    private List<LanguageLevel> preferredLevels;
    private Languages language;
    private Role role;
    private Status teacherStatus;

    public UserResponseDto(User user) {
        setId(user.getId());
        setUpdated(user.getUpdated());
        setCreated(user.getCreated());
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.role = user.getRole();
    }

    public UserResponseDto(Teacher teacher) {
        setId(teacher.getUser().getId());
        setUpdated(teacher.getUser().getUpdated());
        setCreated(teacher.getUser().getCreated());
        this.firstName = teacher.getUser().getFirstName();
        this.lastName = teacher.getUser().getLastName();
        this.email = teacher.getUser().getEmail();
        this.language = teacher.getHisLanguage();
        this.hisLanguageLevel = teacher.getHisLanguageLevel();
        this.preferredLevels = teacher.getPreferredLevels();
        this.preferredAges = teacher.getPreferredAge();
        this.teacherStatus = teacher.getStatus();
        this.role = teacher.getUser().getRole();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Ages> getPreferredAges() {
        return preferredAges;
    }

    public void setPreferredAges(List<Ages> preferredAges) {
        this.preferredAges = preferredAges;
    }

    public LanguageLevel getHisLanguageLevel() {
        return hisLanguageLevel;
    }

    public void setHisLanguageLevel(LanguageLevel hisLanguageLevel) {
        this.hisLanguageLevel = hisLanguageLevel;
    }

    public List<LanguageLevel> getPreferredLevels() {
        return preferredLevels;
    }

    public void setPreferredLevels(List<LanguageLevel> preferredLevels) {
        this.preferredLevels = preferredLevels;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Status teacherStatus) {
        this.teacherStatus = teacherStatus;
    }
}
