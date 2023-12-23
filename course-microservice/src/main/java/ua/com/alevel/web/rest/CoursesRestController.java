package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.web.dto.JwtUser;
import ua.com.alevel.web.dto.requests.CourseRequestDto;

@RestController
@RequestMapping("/api/v1/course")
public class CoursesRestController {

    private final CourseFacade courseFacade;

    public CoursesRestController(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDto req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                fillRequestFromAuthentication(authentication, req);
                return ResponseEntity.ok(courseFacade.createCourse(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private static void fillRequestFromAuthentication(Authentication authentication, CourseRequestDto courseRequestDto){
        Object principal = authentication.getPrincipal();
        if (principal instanceof JwtUser user) {
            courseRequestDto.setTeacherFirstName(user.getFirstName());
            courseRequestDto.setTeacherLastName(user.getLastName());
            courseRequestDto.setTeacherId(authentication.getName());
        }
    }
}