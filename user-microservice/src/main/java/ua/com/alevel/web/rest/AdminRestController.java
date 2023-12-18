package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.requests.TeacherRequestDto;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestController {

    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;

    public AdminRestController(UserFacade userFacade, TeacherFacade teacherFacade) {
        this.userFacade = userFacade;
        this.teacherFacade = teacherFacade;
    }

    @PostMapping("/approveTeacher")
    public ResponseEntity<?> approveTeacher (@RequestBody TeacherRequestDto req){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ResponseEntity.ok(teacherFacade.updateTeacher(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
