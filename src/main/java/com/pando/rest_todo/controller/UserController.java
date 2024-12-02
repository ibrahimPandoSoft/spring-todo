package com.pando.rest_todo.controller;

import com.pando.rest_todo.model.UserDto;
import com.pando.rest_todo.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/new")
    public UserDetails createUser(@RequestBody UserDto user) {
        System.out.println("Creating new user");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        return userService.createNewUser(user.getUsername(), user.getPassword(), "USER");
    }
}
