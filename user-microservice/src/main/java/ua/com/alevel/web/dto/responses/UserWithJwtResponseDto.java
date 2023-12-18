package ua.com.alevel.web.dto.responses;

public class UserWithJwtResponseDto {

    private String jwtToken;

    private UserResponseDto user;

    public UserWithJwtResponseDto(String jwtToken, UserResponseDto user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
