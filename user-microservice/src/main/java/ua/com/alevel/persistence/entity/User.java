package ua.com.alevel.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.alevel.persistence.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Document
public class User extends BaseEntity {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;
    private List<Ages> preferredAge;
    private LanguageLevel hisLanguageLevel;
    private List<LanguageLevel> preferredLevels;
    private Languages hisLanguage;

    public User() {
        super();
        this.role = Role.USER;
        this.status = Status.ACTIVE;
    }
    public List<Ages> getPreferredAge() {
        return preferredAge;
    }

    public void setPreferredAge(List<Ages> preferredAge) {
        this.preferredAge = preferredAge;
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

    public Languages getHisLanguage() {
        return hisLanguage;
    }

    public void setHisLanguage(Languages hisLanguage) {
        this.hisLanguage = hisLanguage;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
