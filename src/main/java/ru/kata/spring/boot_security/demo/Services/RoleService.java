package ru.kata.spring.boot_security.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Repositories.RoleRepositories;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepositories roleRepositories;

    @Autowired
    public RoleService(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }


    public List<Role> getAllRoles(){
        return roleRepositories.findAll();
    }

    public List<Role> getRoleById(int id){
        return roleRepositories.findRoleById(id);
    }
}
