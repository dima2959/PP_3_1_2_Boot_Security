package ru.kata.spring.boot_security.demo.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepositories;
import ru.kata.spring.boot_security.demo.util.UserErrorDeleteException;
import ru.kata.spring.boot_security.demo.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserDAOImpl implements UserDAO {

    private final UserRepositories userRepositories;

    public UserDAOImpl(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

    @Override
    public void createUser(User user) {
        userRepositories.save(user);
    }

    @Override
    public User getUser(int id) {
        Optional<User> user = userRepositories.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updateUser(User user) {
            userRepositories.save(user);
    }

    @Override
    public void deleteUser(int id) {
        try {
        userRepositories.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new UserErrorDeleteException();
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepositories.findByName(name);
    }


}
