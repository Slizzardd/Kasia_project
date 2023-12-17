package ua.com.alevel.web.dto.requests;

import org.modelmapper.ModelMapper;
import ua.com.alevel.persistence.entity.courses.CourseDescription;
import ua.com.alevel.persistence.types.Languages;

public class CourseDescriptionDto{
    private String title;
    private String description;
    private Languages languages;

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

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}