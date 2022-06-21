package ru.kata.spring.boot_security.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Repositories.AdminRepositories;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class AdminServices {

    private final AdminRepositories adminRepositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServices(AdminRepositories adminRepositories, PasswordEncoder passwordEncoder) {
        this.adminRepositories = adminRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        return adminRepositories.findAll();
    }

    @Transactional
    public void createUser(User user){
        adminRepositories.save(user);
    }

    public User getUser(int id){
       Optional<User> user = adminRepositories.findById(id);
       return user.orElse(null);
    }

    @Transactional
    public void updateUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        adminRepositories.save(user);
    }

    @Transactional
    public void deleteUser(int id){
        adminRepositories.deleteById(id);
    }

}
