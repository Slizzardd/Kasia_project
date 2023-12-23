package ua.com.alevel.web.rest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.InnerConverter;
import ua.com.alevel.web.dto.responses.JwtUser;

@RestController
@RequestMapping("/api/v1/inner")
public class InnerRestController {

    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;
    private final UserService userService;

    public InnerRestController(UserFacade userFacade, TeacherFacade teacherFacade, UserService userService) {
        this.userFacade = userFacade;
        this.teacherFacade = teacherFacade;
        this.userService = userService;
    }

    @GetMapping("/getAuthorization")
    public JwtUser getAuthorization(@RequestHeader("Authorization") String authToken) {
        System.out.println("authToken = " + authToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return InnerConverter.convertUserToJwtUser(userService.findUserById(authentication.getName()));
            } else {
                return null;
            }
        } catch (BadCredentialsException | EntityNotFoundException e) {
            return null;
        }
    }
}