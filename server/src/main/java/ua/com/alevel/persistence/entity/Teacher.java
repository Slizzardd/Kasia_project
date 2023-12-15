package ua.com.alevel.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.alevel.persistence.types.Ages;
import ua.com.alevel.persistence.types.LanguageLevel;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.persistence.types.Status;

import java.util.List;

@Document
public class Teacher extends BaseEntity{

    private User user;

    private List<Ages> preferredAge;
    private LanguageLevel hisLanguageLevel;
    private List<LanguageLevel> preferredLevels;
    private Languages hisLanguage;
    private Status status;

    public Teacher() {
        this.status = Status.NOT_ACTIVE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "user=" + user +
                ", preferredAge=" + preferredAge +
                ", hisLanguageLevel=" + hisLanguageLevel +
                ", preferredLevels=" + preferredLevels +
                ", hisLanguage=" + hisLanguage +
                ", status=" + status +
                '}';
    }
}
