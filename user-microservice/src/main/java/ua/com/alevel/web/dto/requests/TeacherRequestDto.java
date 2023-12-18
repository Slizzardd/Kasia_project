package ua.com.alevel.web.dto.requests;

import ua.com.alevel.persistence.types.Ages;
import ua.com.alevel.persistence.types.LanguageLevel;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.persistence.types.Status;

import java.util.List;

public class TeacherRequestDto extends UserRequestDto {

    private List<Ages> preferredAges;
    private LanguageLevel hisLanguageLevel;
    private List<LanguageLevel> preferredLevels;
    private Languages hisLanguage;
    private Status teacherStatus;

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

    public Languages getHisLanguage() {
        return hisLanguage;
    }

    public void setHisLanguage(Languages hisLanguage) {
        this.hisLanguage = hisLanguage;
    }

    public Status getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Status teacherStatus) {
        this.teacherStatus = teacherStatus;
    }
}
