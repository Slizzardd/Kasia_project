package ua.com.alevel.persistence.entity.courses;

import ua.com.alevel.persistence.types.Languages;

public class CourseDescription {

    private String title;

    private String description;

    private Languages languages;

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
