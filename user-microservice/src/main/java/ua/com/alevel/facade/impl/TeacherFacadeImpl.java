package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.Status;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

@Service
public class TeacherFacadeImpl implements TeacherFacade {

    private final TeacherService teacherService;
    private final UserService userService;
    public TeacherFacadeImpl(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @Override
    public UserResponseDto createTeacher(TeacherRequestDto req) {
        User user = userService.findUserById(req.getId());

        user.setHisLanguage(req.getHisLanguage());
        user.setHisLanguageLevel(req.getHisLanguageLevel());
        user.setPreferredAge(req.getPreferredAges());
        user.setPreferredLevels(req.getPreferredLevels());
        user.setTeacherStatus(Status.NOT_ACTIVE);

        return new UserResponseDto(teacherService.createTeacher(user));
    }

    @Override
    public UserResponseDto updateTeacher(TeacherRequestDto req) {
        User teacher = teacherService.findTeacherById(req.getId());

        teacher.setFirstName(req.getFirstName());
        teacher.setLastName(req.getLastName());
        teacher.setEmail(req.getEmail());
        teacher.setHisLanguage(req.getHisLanguage());
        teacher.setHisLanguageLevel(req.getHisLanguageLevel());
        teacher.setPreferredAge(req.getPreferredAges());
        teacher.setPreferredLevels(req.getPreferredLevels());
        teacher.setTeacherStatus(req.getTeacherStatus());

        return new UserResponseDto(teacherService.updateTeacher(teacher));
    }
}
