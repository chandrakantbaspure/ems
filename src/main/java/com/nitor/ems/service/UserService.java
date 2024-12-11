package com.nitor.ems.service;


import com.nitor.ems.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User authenticate(User user);

    User signup(User user);
    User getUserByEmail(String email);
}
