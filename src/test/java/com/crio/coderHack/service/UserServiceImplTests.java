package com.crio.coderHack.service;

import com.crio.coderHack.exchanges.RegisterUserRequest;
import com.crio.coderHack.exchanges.UpdateUserRequest;
import com.crio.coderHack.model.Badge;
import com.crio.coderHack.model.User;
import com.crio.coderHack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerUserTest() throws Exception {
        when(userRepository.existsByUserId("a@123"))
                .thenReturn(false);

        User user = new User("a@123", "ashi", 0, List.of());
        when(userRepository.save(user))
                .thenReturn(user);

        User user1 = userService.registerUser(new RegisterUserRequest("a@123", "ashi"));
        assertEquals(user, user1);
    }

    @Test
    public void updateUserTest() throws Exception {

        User user = new User("a@123", "ashi", 100, List.of());

        when(userRepository.findById("a@123"))
                .thenReturn(Optional.of(user));

        when(userRepository.save(user))
                .thenReturn(user);

        User user1 = userService.updateUser(new UpdateUserRequest("a@123", 100));
        assertEquals(user1, new User("a@123", "ashi", 100, List.of(Badge.CODE_NINJA, Badge.CODE_CHAMP, Badge.CODE_MASTER)));
    }

    @Test
    public void deleteUserTest() throws Exception {


        User user = new User("a@123", "ashi", 100, List.of(Badge.CODE_NINJA, Badge.CODE_CHAMP, Badge.CODE_MASTER));

        when(userRepository.findById("a@123"))
                .thenReturn(Optional.of(user));

        when(userRepository.deleteByUserId("a@123"))
                .thenReturn(user);

        User user1 = userService.deleteUserById("a@123");

        assertEquals(user1, new User("a@123", "ashi", 100, List.of(Badge.CODE_NINJA, Badge.CODE_CHAMP, Badge.CODE_MASTER)));
    }

}
