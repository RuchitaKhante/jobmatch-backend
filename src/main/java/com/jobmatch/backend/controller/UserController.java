package com.jobmatch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobmatch.backend.User;
import com.jobmatch.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password){

        return userService.login(email, password);
    }

}
