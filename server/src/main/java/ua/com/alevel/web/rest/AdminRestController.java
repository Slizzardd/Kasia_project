package ua.com.alevel.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.exceptions.EntityNotFoundException;
import ua.com.alevel.facade.TeacherFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.requests.TeacherRequestDto;
import ua.com.alevel.web.dto.requests.UserRequestDto;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestController {

    private final UserFacade userFacade;

    private final TeacherFacade teacherFacade;

    public AdminRestController(UserFacade userFacade, TeacherFacade teacherFacade) {
        this.userFacade = userFacade;
        this.teacherFacade = teacherFacade;
    }

    @PostMapping("/updateTeacher")
    public ResponseEntity<?> updateTeacher(@RequestBody TeacherRequestDto teacherRequestDto){
        try{
            return ResponseEntity.ok(teacherFacade.updateTeacher(teacherRequestDto));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto userRequestDto){
        try{
            return ResponseEntity.ok(userFacade.updateUser(userRequestDto));
        }catch (UsernameNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
