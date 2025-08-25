package com.examly.springapp.service;

import com.examly.springapp.exception.BadRequestException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Create user with password
    public User createUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException("Username is already taken");
        }
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("Email is already in use");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }

    // ✅ Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // ✅ Update user
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getUsername().equals(userDetails.getUsername()) &&
                userRepository.existsByUsername(userDetails.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        if (!user.getEmail().equals(userDetails.getEmail()) &&
                userRepository.existsByEmail(userDetails.getEmail())) {
            throw new BadRequestException("Email is already in use");
        }

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        return userRepository.save(user);
    }

    // ✅ Delete user
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    // ✅ Login by username
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Invalid password");
        }

        return user;
    }

    // ✅ Login by email
    public User loginByEmail(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Invalid password");
        }

        return user;
    }

    // ✅ Flexible login: accept either username or email
    public User loginFlexible(String loginInput, String password) {
        if (userRepository.existsByUsername(loginInput)) {
            return login(loginInput, password);
        } else if (userRepository.existsByEmail(loginInput)) {
            return loginByEmail(loginInput, password);
        } else {
            throw new ResourceNotFoundException("User not found with username/email: " + loginInput);
        }
    }
}
