package ru.kata.spring.boot_security.demo.Services;

import ru.kata.spring.boot_security.demo.Model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    List<Role> getRoleById(int id);
}
