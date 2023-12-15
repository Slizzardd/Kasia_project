package ua.com.alevel.facade;

import ua.com.alevel.web.dto.requests.UpdatePasswordRequestDto;
import ua.com.alevel.web.dto.requests.UserRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

import java.util.List;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {

    UserResponseDto createUser(UserRequestDto req);

    UserResponseDto updateUser(UserRequestDto req);

    void deleteUserById(String id);

    void updatePassword(UpdatePasswordRequestDto req);

    UserResponseDto findUserById(String id);

    UserResponseDto findUserByEmail(String email);

    List<UserResponseDto> findAllUsers();
}
