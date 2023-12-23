package ua.com.alevel.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.*;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto extends ResponseDto{

    private String email;
    private String firstName;
    private String lastName;
    private List<Ages> preferredAges;
    private List<LanguageLevel> preferredLevels;
    private Languages language;
    private LanguageLevel hisLanguageLevel;
    private Role role;
    private Status accountStatus;
    public UserResponseDto(User user) {
        setId(user.getId());
        setCreated(user.getCreated());
        setUpdated(user.getUpdated());
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.accountStatus = user.getStatus();
        this.preferredAges = user.getPreferredAge();
        this.language = user.getHisLanguage();
        this.preferredLevels = user.getPreferredLevels();
        this.hisLanguageLevel = user.getHisLanguageLevel();
    }

    public UserResponseDto() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Status accountStatus) {
        this.accountStatus = accountStatus;
    }
}
