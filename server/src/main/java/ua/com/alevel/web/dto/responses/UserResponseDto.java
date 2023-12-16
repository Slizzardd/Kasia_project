package ua.com.alevel.web.dto.responses;

import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.Role;

public class UserResponseDto extends ResponseDto {

    private String email;

    private String firstName;

    private String lastName;

    private Role userRole;

    public UserResponseDto(User user) {
        setId(user.getId());
        setUpdated(user.getUpdated());
        setCreated(user.getCreated());
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.userRole = user.getRole();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
