package com.example.plain_jdbc.Plain.JDBC;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController{

    private final UserDAO userDAO = new UserDAO();

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userDAO.createUser(user);
        return "User created successfully!";
    }

    @GetMapping("/get/{userId}")
    public User getUser(@PathVariable int userId) {
        return userDAO.getUser(userId);
    }
}