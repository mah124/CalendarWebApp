package com.calendar.calendarapp.controller;

import com.calendar.calendarapp.model.User;
import com.calendar.calendarapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getEmail(), user.getPassword());
    }
}
