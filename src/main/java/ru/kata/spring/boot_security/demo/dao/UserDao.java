package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> allUsers();
    void addUser(User user);
    void changeUser(long id, User updatedUser, List<Role> roles);
    void deleteUser(long id);
    User getUserById(long id);

    User getUserByUsername(String username);

}