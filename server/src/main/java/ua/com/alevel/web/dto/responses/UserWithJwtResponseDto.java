package ua.com.alevel.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserWithJwtResponseDto{

    private String jwtToken;

    private UserResponseDto userResponseDto;

    public UserWithJwtResponseDto(String jwtToken, UserResponseDto userResponseDto) {
        this.jwtToken = jwtToken;
        this.userResponseDto = userResponseDto;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }
}
