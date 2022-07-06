package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.util.UserNotCreatedException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminServicesImpl implements AdminServices {

    private final UserDAO userDAO;
    private final WebSecurityConfig encoderConfig;

    public AdminServicesImpl(UserDAO userDAO, WebSecurityConfig encoderConfig) {
        this.userDAO = userDAO;
        this.encoderConfig = encoderConfig;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Transactional
    public void createUser(User user){

        try {
            user.setPassword(encoderConfig.getPasswordEncoder().encode(user.getPassword()));
        }catch (IllegalArgumentException e){
            throw new UserNotCreatedException("Passport field is not empty");
        }

        userDAO.createUser(user);
    }

    public User getUser(int id){
       User user = userDAO.getUser(id);
       return user;
    }

    @Transactional
    public void updateUser(User user){

        if(user.getPassword()==null){
            user.setPassword(userDAO.getUser(user.getId()).getPassword());
        }else {
            user.setPassword(encoderConfig.getPasswordEncoder().encode(user.getPassword()));
        }

        userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userDAO.findByName(name);
    }

}
