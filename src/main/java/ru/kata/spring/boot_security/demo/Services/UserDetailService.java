package ru.kata.spring.boot_security.demo.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Security.UserDetailsImpl;

@Service
public class UserDetailService implements UserDetailsService {

    private final AdminServices adminServices;
    private final RoleService roleService;

    public UserDetailService(AdminServices adminServices, RoleService roleService) {
        this.adminServices = adminServices;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserDetailsImpl(adminServices.findByName(username).get());
    }
}
