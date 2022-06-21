package ru.kata.spring.boot_security.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.Model.Role;

import java.util.List;

public interface RoleRepositories extends JpaRepository<Role, Integer> {

    List<Role> findAll();

    List<Role> findRoleById(int id);
}
