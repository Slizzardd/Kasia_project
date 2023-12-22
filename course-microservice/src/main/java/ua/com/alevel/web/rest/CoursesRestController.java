package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exceptions.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/course")
public class CoursesRestController {

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ResponseEntity.ok(authentication.getName());
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