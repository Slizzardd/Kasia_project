package ua.com.alevel.facade;

import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.responses.TeacherResponseDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

public interface TeacherFacade extends BaseFacade<TeacherRequestDto, TeacherResponseDto> {

    UserResponseDto createTeacher(TeacherRequestDto req);

    UserResponseDto findTeacherByEmail(String email);

    UserResponseDto updateTeacher(TeacherRequestDto req);
}
