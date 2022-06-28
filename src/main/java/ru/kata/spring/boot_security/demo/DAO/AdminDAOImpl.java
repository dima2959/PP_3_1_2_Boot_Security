package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.empty();
    }
}
