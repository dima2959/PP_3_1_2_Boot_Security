package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositories;

import java.util.List;

@Service
public class RoleDAOImpl implements RoleDAO {

    private final RoleRepositories roleRepositories;

    public RoleDAOImpl(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepositories.findAll();
    }

    @Override
    public List<Role> getRoleById(int id) {
        return roleRepositories.findRoleById(id);
    }
}
