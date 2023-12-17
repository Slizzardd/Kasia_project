package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.requests.UpdatePasswordRequestDto;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserFacade userFacade;

    private final TeacherFacade teacherFacade;


    public UserRestController(UserFacade userFacade, TeacherFacade teacherFacade) {
        this.userFacade = userFacade;
        this.teacherFacade = teacherFacade;
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequestDto req) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                req.setEmail(authentication.getName());
                userFacade.updatePassword(req);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ResponseEntity.ok(teacherFacade.findTeacherByEmail(authentication.getName()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok(userFacade.findUserByEmail(authentication.getName()));
        }
    }
}
