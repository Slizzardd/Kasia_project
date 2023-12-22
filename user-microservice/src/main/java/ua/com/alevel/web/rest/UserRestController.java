package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.exceptions.EntityExistException;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.requests.UserRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;
    public UserRestController(UserFacade userFacade, TeacherFacade teacherFacade) {
        this.userFacade = userFacade;
        this.teacherFacade = teacherFacade;
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ResponseEntity.ok(userFacade.findUserById(authentication.getName()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrationTeacher")

    public ResponseEntity<?> registrationTeacher(@RequestBody TeacherRequestDto req){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                req.setId(authentication.getName());
                return ResponseEntity.ok(teacherFacade.createTeacher(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (EntityExistException e) {
            return ResponseEntity.status(409).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto req){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                req.setId(authentication.getName());
                return ResponseEntity.ok(userFacade.updateUser(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (EntityExistException e) {
            return ResponseEntity.status(409).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
