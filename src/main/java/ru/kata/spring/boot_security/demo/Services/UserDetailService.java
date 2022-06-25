package ru.kata.spring.boot_security.demo.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Repositories.AdminRepositories;
import ru.kata.spring.boot_security.demo.Security.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final AdminRepositories adminRepositories;

    public UserDetailService(AdminRepositories adminRepositories) {
        this.adminRepositories = adminRepositories;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = adminRepositories.findByName(username);

        return new UserDetailsImpl(user.get());
    }
}
