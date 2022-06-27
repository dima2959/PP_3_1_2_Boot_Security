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
    private final RoleService roleService;

    public UserDetailService(AdminRepositories adminRepositories, RoleService roleService) {
        this.adminRepositories = adminRepositories;
        this.roleService = roleService;
    }

    public User findByUserName(String username){

        Optional<User> user = adminRepositories.findByName(username);

        return user.get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return new UserDetailsImpl(findByUserName(username));
    }
}
