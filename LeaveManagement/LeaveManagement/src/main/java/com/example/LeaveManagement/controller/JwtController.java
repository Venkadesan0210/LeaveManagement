package com.example.leavemanagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.leavemanagement.entity.JwtRequest;
import com.example.leavemanagement.entity.JwtResponse;
import com.example.leavemanagement.service.JwtService;
@RestController
@CrossOrigin
public class JwtController {
    private final JwtService jwtService;
    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @PostMapping({"/authenticate"})

    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws AuthenticationException, JwtService.UserNotFoundException, JwtService.InvalidCredentialsException, JwtService.UserDisabledException {
        return jwtService.createJwtToken(jwtRequest);
    }
    public static class AuthenticationException extends RuntimeException {
        public AuthenticationException(String message) {
            super(message);
        }
        public AuthenticationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
