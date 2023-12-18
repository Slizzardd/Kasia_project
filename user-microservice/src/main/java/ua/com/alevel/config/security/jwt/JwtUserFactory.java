package ua.com.alevel.config.security.jwt;

import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.types.Status;
import ua.com.alevel.web.dto.responses.JwtUser;


public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
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
