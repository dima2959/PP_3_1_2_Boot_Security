package ru.kata.spring.boot_security.demo.util;

public class UserErrorUpdateException extends RuntimeException {

    public UserErrorUpdateException(String msg){
        super(msg);
    }
}
