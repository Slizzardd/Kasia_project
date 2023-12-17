package ua.com.alevel.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.web.dto.requests.CoursesDataTableRequest;

@RestController
@RequestMapping("/api/v1/course")
public class CourseRestController {

    private final CourseFacade courseFacade;

    public CourseRestController(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllCourses(){
        return ResponseEntity.ok(courseFacade.findAllCourses());
    }
}
