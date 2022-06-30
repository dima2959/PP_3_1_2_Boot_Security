package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.AdminDAO;
import ru.kata.spring.boot_security.demo.security.UserSecurityDetails;

@Service
public class UserDetailService implements UserDetailsService {

    private final AdminDAO adminDAO;

    public UserDetailService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserSecurityDetails(adminDAO.findByName(username).get());
    }
}

