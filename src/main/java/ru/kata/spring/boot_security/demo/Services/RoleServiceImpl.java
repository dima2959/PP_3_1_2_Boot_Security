package ru.kata.spring.boot_security.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Repositories.RoleRepositories;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepositories roleRepositories;

    public RoleServiceImpl(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    public List<Role> getAllRoles(){
        return roleRepositories.findAll();
    }

    public List<Role> getRoleById(int id){
        return roleRepositories.findRoleById(id);
    }
}
