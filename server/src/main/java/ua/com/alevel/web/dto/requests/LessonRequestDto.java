package ua.com.alevel.web.dto.requests;

import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.web.dto.Block;

import java.util.List;
import java.util.Map;

public class LessonRequestDto extends RequestDto {

    private String courseId;
    private Map<Languages, LessonDescriptionRequestDto> lessonDescriptionRequestDtoMap;

    public static class LessonDescriptionRequestDto {
        private String title;
        public List<Block> blocks;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Block> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<Block> blocks) {
            this.blocks = blocks;
        }
    }

    public Map<Languages, LessonDescriptionRequestDto> getLessonDescriptionRequestDtoMap() {
        return lessonDescriptionRequestDtoMap;
    }

    public void setLessonDescriptionRequestDtoMap(Map<Languages, LessonDescriptionRequestDto> lessonDescriptionRequestDtoMap) {
        this.lessonDescriptionRequestDtoMap = lessonDescriptionRequestDtoMap;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
