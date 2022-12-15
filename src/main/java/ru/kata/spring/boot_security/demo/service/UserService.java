package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> allUsers();
    void addUser(User user, List<Role> roles);
    void changeUser(long id, User updatedUser, List<Role> role);
    void deleteUser(long id);
    User getUserById(long id);

    UserDetails loadUserByUsername(String username);
}