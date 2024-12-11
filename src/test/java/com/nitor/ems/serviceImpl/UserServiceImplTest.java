package com.nitor.ems.serviceImpl;

import com.nitor.ems.model.User;
import com.nitor.ems.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    private static UserRepository userRepository;
    @BeforeAll
    static void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("test");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(user);
    }
    @Test
    void login(){
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("test");
        User login = userServiceImpl.authenticate(user);
        assertNotNull(login);
    }
    @Test
    void signup(){
        User user = new User();
        user.setEmail("test1@example.com");
        user.setPassword("test");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User savedUser = userServiceImpl.signup(user);
        assertNotNull(savedUser);
    }
    @Test
    void findUserByEmail(){
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("test");
        user.setId(1L);
        user.setName("test");
        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        User response = userServiceImpl.getUserByEmail("test@example.com");
        assertNotNull(response);
    }
}