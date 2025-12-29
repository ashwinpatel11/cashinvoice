package com.user.controller;

import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.entity.User;
import com.user.jwt.JwtUtil;
import com.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/addUser")
    public UserResponseDto addUser(@Valid @RequestBody UserRequestDto userDto){
        return userServiceImpl.addUser(userDto);
    }


    @GetMapping("/getSingleUser/{id}")
    public User getSingleUser(@PathVariable Long id){
        return userServiceImpl.getSingleUser(id);
    }


    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUser();
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        return userServiceImpl.deleteUser(id);
    }


}
