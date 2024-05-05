package com.example.crud.controller;

import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/userList")
    public String showUserList(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "userList";
    }
    @GetMapping("/home")
    public String getIndex(Model model) {
        return "index";
    }
    @GetMapping("/registration")
    public String getLogin(Model model) {
        return "signup";
    }

    @GetMapping("/size")
    public String getSize(Model model) {
        return "size";
    }

    @GetMapping("/email")
    public String getEmail(Model model) {
        return "email";
    }

    @GetMapping("/reference")
    public String getReference(Model model) {
        return "reference";
    }

    @GetMapping("/api/v1")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getUsers();
    }


    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            boolean registered = userService.registerNewUser(user);
            if (!registered) {
                model.addAttribute("error", "El usuario ya existe");
                return "signup";
            } else {
                return "redirect:/users/home";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@ModelAttribute User user) {
        Map<String, Object> response = new HashMap<>();
        if (user.getUsersId() == null) {
            response.put("success", false);
            response.put("message", "User ID is missing");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        userService.saveOrUpdate(user);
        response.put("success", true);
        response.put("message", "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }


}


