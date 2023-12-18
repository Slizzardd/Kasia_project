package ua.com.alevel.util;

import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.Status;
import ua.com.alevel.web.dto.responses.JwtUser;

public final class InnerConverter {

    private InnerConverter() throws IllegalArgumentException{throw new IllegalArgumentException("Util class");}

    public static JwtUser convertUserToJwtUser(User user){
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getAuthorities(),
                user.getStatus().equals(Status.ACTIVE)
        );
    }
}
