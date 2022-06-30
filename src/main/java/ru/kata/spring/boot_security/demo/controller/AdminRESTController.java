package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdminServices;
import ru.kata.spring.boot_security.demo.util.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final AdminServices adminServices;

    public AdminRESTController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return adminServices.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return adminServices.getUser(id);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user,
                                                 BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new UserNotCreatedException(createErrorMessage(bindingResult));
        }

        adminServices.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/user")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User user,
                                                 BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new UserErrorUpdateException(createErrorMessage(bindingResult));
        }

        adminServices.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody User user){

        adminServices.deleteUser(user.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private String createErrorMessage(BindingResult bindingResult){

        StringBuilder errors = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError error: fieldErrors){
            errors.append(error.getDefaultMessage()).append(". ");
        }

        return errors.toString().trim();
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e){
        return new ResponseEntity<>(new UserErrorResponse("User not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e){
        return new ResponseEntity<>(new UserErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handeException(UserErrorUpdateException e){
        return new ResponseEntity<>(new UserErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handeException(UserErrorDeleteException e){
        return new ResponseEntity<>(new UserErrorResponse("User delete error"), HttpStatus.BAD_REQUEST);
    }
}
