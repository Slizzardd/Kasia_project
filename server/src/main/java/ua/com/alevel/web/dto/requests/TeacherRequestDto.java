package ua.com.alevel.web.dto.requests;

import ua.com.alevel.persistence.types.Ages;
import ua.com.alevel.persistence.types.LanguageLevel;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.persistence.types.Status;

import java.util.ArrayList;
import java.util.List;

public class TeacherRequestDto extends RequestDto{

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private List<Ages> preferredAges;
    private List<LanguageLevel> preferredLevels;
    private Languages language;
    private LanguageLevel hisLanguageLevel;
    private Status status;
    private String userEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LanguageLevel getHisLanguageLevel() {
        return hisLanguageLevel;
    }

    public void setHisLanguageLevel(LanguageLevel hisLanguageLevel) {
        this.hisLanguageLevel = hisLanguageLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
