package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.security.UserSecurityDetails;

@Service
public class UserDetailService implements UserDetailsService {

    private final AdminServices adminServices;
    private final RoleService roleService;

    public UserDetailService(AdminServices adminServices, RoleService roleService) {
        this.adminServices = adminServices;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserSecurityDetails(adminServices.findByName(username).get());
    }
}
