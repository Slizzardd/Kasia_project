package ua.com.alevel.web.dto.requests;

import ua.com.alevel.persistence.types.Languages;

public class CoursesDataTableRequest extends RequestDto {

    private String lang;

    public CoursesDataTableRequest() {
    }

    public Languages getLang() {
        return Languages.valueOf(lang);
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
