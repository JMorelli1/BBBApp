package com.example.bbbapp.user;

import java.util.List;

import com.example.bbbapp.exception.BusinessException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Integer userId) throws BusinessException {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new BusinessException("Error finding User with that ID");
        }
        return user;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public void addUser(User user) throws BusinessException {
        userRepository.save(user);
    }

    public void updateUser(Integer userId, User updatedUser) throws BusinessException {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new BusinessException("Error finding user to update at ID: " + userId);
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) throws BusinessException {

        try {
            userRepository.deleteById(userId);
        } catch (IllegalArgumentException ie) {
            throw new BusinessException("Error deleting user at ID: " + userId + "\nError: " + ie);
        }
    }
}