package ru.kata.spring.boot_security.demo.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Security.UserDetailsImpl;

@Service
public class UserDetailService implements UserDetailsService {

    private final AdminServices adminServices;

    public UserDetailService(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user = adminServices.findByName(username);

        return new UserDetailsImpl(user);
    }
}
