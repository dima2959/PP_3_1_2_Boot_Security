package ru.kata.spring.boot_security.demo.util;

public class UserErrorResponse {

    private String error;

    public UserErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
