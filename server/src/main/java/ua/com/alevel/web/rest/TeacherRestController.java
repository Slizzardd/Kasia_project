package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exceptions.EntityExistException;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.facade.LessonFacade;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.web.dto.requests.CourseRequestDto;
import ua.com.alevel.web.dto.requests.LessonRequestDto;
import ua.com.alevel.web.dto.requests.TeacherRequestDto;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherRestController {

    private final TeacherFacade teacherFacade;

    private final CourseFacade courseFacade;

    private final LessonFacade lessonFacade;
    public TeacherRestController(TeacherFacade teacherFacade, CourseFacade courseFacade, LessonFacade lessonFacade) {
        this.teacherFacade = teacherFacade;
        this.courseFacade = courseFacade;
        this.lessonFacade = lessonFacade;
    }

    @PostMapping("/registrationTeacher")
    public ResponseEntity<?> registrationTeacher(@RequestBody TeacherRequestDto req) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                req.setUserEmail(authentication.getName());
                return ResponseEntity.ok(teacherFacade.createTeacher(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch (EntityExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDto req) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                req.setTeacherEmail(authentication.getName());
                return ResponseEntity.ok(courseFacade.createCourse(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch (EntityExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/createLesson")
    public ResponseEntity<?> createLesson(@RequestBody LessonRequestDto req) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                return ResponseEntity.ok(lessonFacade.createLesson(req));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch (EntityExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
