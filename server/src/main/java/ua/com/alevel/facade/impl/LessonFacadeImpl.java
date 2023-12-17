package ua.com.alevel.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua.com.alevel.facade.LessonFacade;
import ua.com.alevel.persistence.entity.courses.Lesson;
import ua.com.alevel.persistence.entity.courses.LessonDescription;
import ua.com.alevel.persistence.types.Languages;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.LessonService;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.web.dto.Block;
import ua.com.alevel.web.dto.requests.LessonRequestDto;
import ua.com.alevel.web.dto.responses.LessonResponseDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LessonFacadeImpl implements LessonFacade {

    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final CourseService courseService;

    public LessonFacadeImpl(LessonService lessonService, TeacherService teacherService, CourseService courseService) {
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @Override
    public LessonResponseDto createLesson(LessonRequestDto req) {
        Lesson lesson = new Lesson();
        lesson.setCourseId(req.getCourseId());
        lesson.setLesson(initLessonDescriptionMap(req.getLessonDescriptionRequestDtoMap()));
        return new LessonResponseDto(lessonService.createLesson(lesson));
    }

    private Map<Languages, LessonDescription> initLessonDescriptionMap(Map<Languages, LessonRequestDto.LessonDescriptionRequestDto> lessonDescriptions) {
        return lessonDescriptions.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> createLessonDescription(entry.getValue())
                ));
    }

    private LessonDescription createLessonDescription(LessonRequestDto.LessonDescriptionRequestDto lessonDescriptionDto) {
        LessonDescription lessonDescription = new LessonDescription();
        lessonDescription.setTitle(lessonDescriptionDto.getTitle());
        lessonDescription.setBlocks(initBlocks(lessonDescriptionDto.getBlocks()));
        return lessonDescription;
    }

    private List<LessonDescription.Block> initBlocks(List<Block> blocks) {
        return blocks.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    private LessonDescription.Block convertDtoToEntity(Block block) {
        return new ModelMapper().map(block, LessonDescription.Block.class);
    }
}
