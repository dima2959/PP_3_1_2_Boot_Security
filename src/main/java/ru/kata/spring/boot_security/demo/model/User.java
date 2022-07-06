package ru.kata.spring.boot_security.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @Column(name = "age")
    @NotNull
    @Min(value = 18, message = "Возраст должен быть больше 18")
    private int age;

    @Column(name = "mail")
    @NotNull
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Введите корректный E-mail")
    private String mail;

    @Column(name = "password")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User(){}

    public User(String name, int age, String mail) {
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public User(String name, String password, List<Role> roles){
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public User(int id, String name, int age, String mail, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.roles = roles;
    }

    public User(String name, int age, String mail, String password, List<Role> roles) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}
