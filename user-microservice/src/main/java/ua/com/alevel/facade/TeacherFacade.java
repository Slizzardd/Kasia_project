package ua.com.alevel.facade;

import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

public interface TeacherFacade extends BaseFacade<TeacherRequestDto, UserResponseDto> {

    UserResponseDto createTeacher(TeacherRequestDto req);

    UserResponseDto updateTeacher(TeacherRequestDto req);
}
