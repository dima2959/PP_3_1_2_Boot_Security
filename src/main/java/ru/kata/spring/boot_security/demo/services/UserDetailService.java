package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.security.UserSecurityDetails;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    public UserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserSecurityDetails(userDAO.findByName(username).get());
    }
}

