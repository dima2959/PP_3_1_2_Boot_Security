package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.AdminRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDAOImpl implements AdminDAO {

    private final AdminRepositories adminRepositories;

    public AdminDAOImpl(AdminRepositories adminRepositories) {
        this.adminRepositories = adminRepositories;
    }

    @Override
    public List<User> getAllUsers() {
        return adminRepositories.findAll();
    }

    @Override
    public void createUser(User user) {
        adminRepositories.save(user);
    }

    @Override
    public User getUser(int id) {
        return adminRepositories.getById(id);
    }

    @Override
    public void updateUser(User user) {
        adminRepositories.save(user);
    }

    @Override
    public void deleteUser(int id) {
        adminRepositories.deleteById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return adminRepositories.findByName(name);
    }
}
