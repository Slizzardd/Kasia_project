package ua.com.alevel.facade;

import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.responses.TeacherResponseDto;

public interface TeacherFacade extends BaseFacade<TeacherRequestDto, TeacherResponseDto> {

    TeacherResponseDto createTeacher(TeacherRequestDto req);

    TeacherResponseDto findTeacherByEmail(String email);

    TeacherResponseDto updateTeacher(TeacherRequestDto req);
}
