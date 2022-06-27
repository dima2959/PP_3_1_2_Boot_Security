package ru.kata.spring.boot_security.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Repositories.AdminRepositories;
import ru.kata.spring.boot_security.demo.configs.EncoderConfig;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminServicesImpl implements AdminServices {

    private final AdminRepositories adminRepositories;
    private final EncoderConfig encoderConfig;

    public AdminServicesImpl(AdminRepositories adminRepositories, EncoderConfig encoderConfig) {
        this.adminRepositories = adminRepositories;
        this.encoderConfig = encoderConfig;
    }

    public List<User> getAllUsers(){
        return adminRepositories.findAll();
    }

    @Transactional
    public void createUser(User user){
        user.setPassword(encoderConfig.getPasswordEncoder().encode(user.getPassword()));
        adminRepositories.save(user);
    }

    public User getUser(int id){
       Optional<User> user = adminRepositories.findById(id);
       return user.orElse(null);
    }

    @Transactional
    public void updateUser(User user){
        user.setPassword(encoderConfig.getPasswordEncoder().encode(user.getPassword()));
        adminRepositories.save(user);
    }

    @Transactional
    public void deleteUser(int id){
        adminRepositories.deleteById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return adminRepositories.findByName(name);
    }


}
