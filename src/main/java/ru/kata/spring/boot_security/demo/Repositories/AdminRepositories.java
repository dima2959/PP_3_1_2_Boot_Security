package ru.kata.spring.boot_security.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface AdminRepositories extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    //@Query("SELECT u FROM User u") ???
    List<User> findAll();

}
