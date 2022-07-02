package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.AdminDAO;
import ru.kata.spring.boot_security.demo.configs.EncoderConfig;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.util.UserNotCreatedException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminServicesImpl implements AdminServices {

    private final AdminDAO adminDAO;
    private final EncoderConfig encoderConfig;

    public AdminServicesImpl(AdminDAO adminDAO, EncoderConfig encoderConfig) {
        this.adminDAO = adminDAO;
        this.encoderConfig = encoderConfig;
    }

    public List<User> getAllUsers(){
        return adminDAO.getAllUsers();
    }

    @Transactional
    public void createUser(User user){

        try {
            user.setPassword(encoderConfig.getPasswordEncoder().encode(user.getPassword()));
        }catch (IllegalArgumentException e){
            throw new UserNotCreatedException("Passport field is not empty");
        }

        adminDAO.createUser(user);
    }

    public User getUser(int id){
       User user = adminDAO.getUser(id);
       return user;
    }

    @Transactional
    public void updateUser(User user){

        if(user.getPassword().equals("")){
            user.setPassword(adminDAO.getUser(user.getId()).getPassword());
        }else {
            user.setPassword(encoderConfig.getPasswordEncoder().encode(user.getPassword()));
        }

        adminDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(int id){
        adminDAO.deleteUser(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return adminDAO.findByName(name);
    }

}
