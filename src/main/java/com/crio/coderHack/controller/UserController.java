package com.crio.coderHack.controller;

import com.crio.coderHack.exchanges.RegisterUserRequest;
import com.crio.coderHack.exchanges.UpdateUserRequest;
import com.crio.coderHack.model.User;
import com.crio.coderHack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserByUserId(@PathVariable String userId){
        return userService.getUserByUserId(userId);
    }

    @PostMapping()
    public User registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        return userService.registerUser(registerUserRequest);
    }

    @PutMapping()
    public User updateUser(UpdateUserRequest updateUserRequest){
        return userService.updateUser(updateUserRequest);
    }

    @DeleteMapping("/{userId}")
    public User deleteUserById(@PathVariable String userId){
        return userService.deleteUserById(userId);
    }


}
