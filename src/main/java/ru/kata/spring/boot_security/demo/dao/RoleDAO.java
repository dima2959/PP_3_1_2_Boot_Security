package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> getAllRoles();

    List<Role> getRoleById(int id);

}
