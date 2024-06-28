package group6.group6.controller;


import group6.group6.model.LoginData;
import group6.group6.model.RegistrationData;
import group6.group6.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody RegistrationData data){
        return  authenticationService.RegisterUser(data);

    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody LoginData data){

        return authenticationService.LogInUser(data);
    }
}
