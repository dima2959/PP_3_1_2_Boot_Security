package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleRepositories extends JpaRepository<Role, Integer> {

    List<Role> findAll();

    List<Role> findRoleById(int id);

}
