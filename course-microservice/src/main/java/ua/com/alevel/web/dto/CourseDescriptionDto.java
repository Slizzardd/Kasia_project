package ua.com.alevel.web.dto;

import ua.com.alevel.persistence.types.Languages;

import java.math.BigDecimal;

public class CourseDescriptionDto {

    private String shortTitle;
    private String fullTitle;
    private String description;
    private Languages language;

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }
}
