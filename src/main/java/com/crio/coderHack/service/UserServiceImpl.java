package com.crio.coderHack.service;

import com.crio.coderHack.exchanges.RegisterUserRequest;
import com.crio.coderHack.exchanges.UpdateUserRequest;
import com.crio.coderHack.model.Badge;
import com.crio.coderHack.model.User;
import com.crio.coderHack.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    @Override
    public User getUserByUserId(String id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist with id:"+ id)
                );
    }

    @Override
    public User registerUser(RegisterUserRequest registerUserRequest) throws Exception {
        String userId = registerUserRequest.getUserId();
        if(userRepository.existsByUserId(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User already exists with userId:" + userId + ". Please choose another userId");
        }
        User user = modelMapper.map(registerUserRequest, User.class);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) {
        String userId = updateUserRequest.getUserId();
        User user = getUserByUserId(userId);

        Integer score = updateUserRequest.getScore();
        List<Badge> badges = new ArrayList<>();

        if(score<30){
            badges = List.of(Badge.CODE_NINJA);
        }else if(score<60){
            badges = List.of(
                    Badge.CODE_NINJA,
                    Badge.CODE_CHAMP);
        }else if(score<=100){
            badges = List.of(
                    Badge.CODE_NINJA,
                    Badge.CODE_CHAMP,
                    Badge.CODE_MASTER
            );
        }

        user.setScore(score);
        user.setBadges(badges);
        return userRepository.save(user);
    }

    @Override
    public User deleteUserById(String userId) {
        getUserByUserId(userId);
        return userRepository.deleteByUserId(userId);
    }
}
