package dev.al.PWork.service;

import dev.al.PWork.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);
    Optional<User> findByUsername(String username);
}
