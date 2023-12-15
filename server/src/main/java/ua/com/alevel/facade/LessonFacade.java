package ua.com.alevel.facade;

import ua.com.alevel.web.dto.requests.LessonRequestDto;
import ua.com.alevel.web.dto.responses.LessonResponseDto;

public interface LessonFacade extends BaseFacade<LessonRequestDto, LessonResponseDto> {

    LessonResponseDto createLesson(LessonRequestDto req);
}
