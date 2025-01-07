package com.crio.coderHack.service;


import com.crio.coderHack.exchanges.RegisterUserRequest;
import com.crio.coderHack.exchanges.UpdateUserRequest;
import com.crio.coderHack.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserByUserId(String id);

    User registerUser(RegisterUserRequest registerUserRequest);

    User updateUser(UpdateUserRequest updateUserRequest);

    public User deleteUserById(String id);
}
