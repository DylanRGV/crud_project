package com.example.crud.controller;

import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/home")
    public String getIndex(Model model) {
        return "index";
    }
    @GetMapping("/home")
    public String getLogin(Model model) {
        return "login";
    }
    @GetMapping("/home")
    public String getSize(Model model) {
        return "size";
    }
    @GetMapping("/home")
    public String getEmail(Model model) {
        return "email";
    }
    @GetMapping("/home")
    public String getReference(Model model) {
        return "reference";
    }

    @GetMapping("/api/v1")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping("/api/v1")
    @ResponseBody
    public void saveOrUpdateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    @DeleteMapping("/api/v1/{userId}")
    @ResponseBody
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(userId);
    }

    // @PatchMapping
}
