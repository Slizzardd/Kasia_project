package ua.com.alevel.exceptions;

import org.springframework.security.core.AuthenticationException;

public class    JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }


    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}