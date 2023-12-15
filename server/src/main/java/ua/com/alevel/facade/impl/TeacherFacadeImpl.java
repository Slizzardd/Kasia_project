package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.Status;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.responses.TeacherResponseDto;

@Service
public class TeacherFacadeImpl implements TeacherFacade {

    private final TeacherService teacherService;

    private final UserService userService;

    public TeacherFacadeImpl(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto req) {
        Teacher teacher = new Teacher();
        teacher.setHisLanguage(req.getLanguage());
        teacher.setHisLanguageLevel(req.getHisLanguageLevel());
        teacher.setPreferredLevels(req.getPreferredLevels());
        teacher.setPreferredAge(req.getPreferredAges());
        User user = userService.findUserByEmail(req.getUserEmail());
        teacher.setId(user.getId());
        teacher.setUser(user);
        return new TeacherResponseDto(teacherService.createTeacher(teacher));
    }

    @Override
    public TeacherResponseDto findTeacherByEmail(String email) {
        Teacher teacher = teacherService.findTeacherById(userService.findUserByEmail(email).getId());
        if (teacher != null) {
            return new TeacherResponseDto(teacher);
        } else {
            return null;
        }
    }

    @Override
    public TeacherResponseDto updateTeacher(TeacherRequestDto req) {
        Teacher teacher = teacherService.findTeacherById(req.getId());
        teacher.setHisLanguage(req.getLanguage());
        teacher.setHisLanguageLevel(req.getHisLanguageLevel());
        teacher.setPreferredLevels(req.getPreferredLevels());
        teacher.setPreferredAge(req.getPreferredAges());
        teacher.setStatus(Status.ACTIVE);
        return new TeacherResponseDto(teacherService.updateTeacher(teacher));
    }
}
