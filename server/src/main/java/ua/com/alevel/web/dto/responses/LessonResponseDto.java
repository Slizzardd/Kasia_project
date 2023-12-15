package ua.com.alevel.web.dto.responses;

import org.modelmapper.ModelMapper;
import ua.com.alevel.persistence.entity.courses.Lesson;
import ua.com.alevel.persistence.entity.courses.LessonDescription;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.web.dto.Block;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LessonResponseDto extends ResponseDto {

    private Map<Languages, LessonDescriptionResponseDto> lessons;

    public LessonResponseDto(Lesson lesson) {
        setId(lesson.getId());
        setCreated(lesson.getCreated());
        setUpdated(lesson.getUpdated());

        lessons = lesson.getLesson().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            LessonDescriptionResponseDto lessonDto = new LessonDescriptionResponseDto();
                            lessonDto.setTitle(entry.getValue().getTitle());
                            lessonDto.setBlocks(convertBlocks(entry.getValue().getBlocks()));
                            return lessonDto;
                        }
                ));
    }

    private List<Block> convertBlocks(List<LessonDescription.Block> blocks) {
        return blocks.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private Block convertEntityToDto(LessonDescription.Block block) {
        return new ModelMapper().map(block, Block.class);
    }

    private static class LessonDescriptionResponseDto {
        private String title;
        private List<Block> blocks;

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

    public Map<Languages, LessonDescriptionResponseDto> getLessons() {
        return lessons;
    }

    public void setLessons(Map<Languages, LessonDescriptionResponseDto> lessons) {
        this.lessons = lessons;
    }
}
