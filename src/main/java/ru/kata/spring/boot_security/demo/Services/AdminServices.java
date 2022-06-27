package ru.kata.spring.boot_security.demo.Services;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface AdminServices {

    List<User> getAllUsers();

    void createUser(User user);

    User getUser(int id);

    void updateUser(User user);

    void deleteUser(int id);

    Optional<User> findByName(String name);



}
