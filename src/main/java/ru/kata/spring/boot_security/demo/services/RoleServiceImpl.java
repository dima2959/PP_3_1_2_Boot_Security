package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public List<Role> getAllRoles(){
        return roleDAO.getAllRoles();
    }

    public List<Role> getRoleById(int id){
        return roleDAO.getRoleById(id);
    }
}
