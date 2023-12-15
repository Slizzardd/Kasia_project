package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.Teacher;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.requests.UpdatePasswordRequestDto;
import ua.com.alevel.web.dto.requests.UserRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

import java.util.List;
import java.util.Objects;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    private final TeacherService teacherService;

    public UserFacadeImpl(UserService userService, TeacherService teacherService) {
        this.userService = userService;
        this.teacherService = teacherService;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto req) {
        User user = new User();
        setMainInformationAboutUserFromReq(req, user);

        user.setPassword(req.getPassword());

        return new UserResponseDto(userService.createUser(user));
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto req) {
        User user = userService.findUserById(req.getId());

        setMainInformationAboutUserFromReq(req, user);

        user = userService.updateUser(user);

        if(user != null && Objects.equals(user.getEmail(), req.getEmail())){
            Teacher teacher = teacherService.findTeacherById(user.getId());
            teacher.setUser(user);
            teacherService.updateTeacher(teacher);
        }
        return new UserResponseDto(user);
    }

    @Override
    public void deleteUserById(String id) {
        userService.deleteUserById(id);
    }

    @Override
    public void updatePassword(UpdatePasswordRequestDto req) {
        User user = userService.findUserByEmail(req.getEmail());
        userService.updatePassword(user, req.getOldPassword(), req.getNewPassword());
    }

    @Override
    public UserResponseDto findUserById(String id) {
        User user = userService.findUserById(id);
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return new UserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return null;
    }

    private void setMainInformationAboutUserFromReq(UserRequestDto req, User user){
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
    }
}
