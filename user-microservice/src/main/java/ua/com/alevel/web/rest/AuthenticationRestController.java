package ua.com.alevel.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.config.security.jwt.JwtTokenProvider;
import ua.com.alevel.exceptions.EntityExistException;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.requests.UserRequestDto;
import ua.com.alevel.web.dto.responses.UserResponseDto;
import ua.com.alevel.web.dto.responses.UserWithJwtResponseDto;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestController {

    private final UserFacade userFacade;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(UserFacade userFacade, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userFacade = userFacade;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto user = userFacade.createUser(userRequestDto);

            if (user != null) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), userRequestDto.getPassword()));
                String token = jwtTokenProvider.createToken(user.getId(), user.getRole());
                return ResponseEntity.ok(new UserWithJwtResponseDto(token, user));
            } else {
                throw new EntityExistException("");
            }
        } catch (EntityExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
//        catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Unknown error, please contact site support: " + e.getMessage());
//        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto req) {
        try {
            UserResponseDto user = userFacade.findUserByEmail(req.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), req.getPassword()));


            String token = jwtTokenProvider.createToken(user.getId(), user.getRole());

            return ResponseEntity.ok(new UserWithJwtResponseDto(token, user));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test");
    }
}
