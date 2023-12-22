package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.requests.UserRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPassword(req.getPassword());

        return new UserResponseDto(userService.createUser(user));
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto req) {
        User user = userService.findUserById(req.getId());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());

        return new UserResponseDto(userService.updateUser(user));
    }

    @Override
    public void deleteUserById(String id) {

    }

    @Override
    public UserResponseDto findUserById(String id) {
        return new UserResponseDto(userService.findUserById(id));
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
}
