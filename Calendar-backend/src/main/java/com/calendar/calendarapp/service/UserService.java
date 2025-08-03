package com.calendar.calendarapp.service;


import com.calendar.calendarapp.model.User;
import com.calendar.calendarapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);  // في الإنتاج يجب أن تقوم بتشفير كلمة المرور
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
