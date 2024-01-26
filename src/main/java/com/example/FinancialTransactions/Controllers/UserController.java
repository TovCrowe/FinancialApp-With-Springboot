package com.example.FinancialTransactions.Controllers;

import com.example.FinancialTransactions.DTO.ApiResponseDTO;
import com.example.FinancialTransactions.Exceptions.UserNotFoundException;
import com.example.FinancialTransactions.Models.UserModel;
import com.example.FinancialTransactions.Services.UserService;
import org.apache.catalina.User;
import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
        try {
            UserModel user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserModel user){
        try{
            UserModel newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //illegal and usernotfound
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> editUser(@RequestBody UserModel user, @PathVariable Long id) {
        try {
            UserModel updatedUser = userService.editUser(user, id);
            ApiResponseDTO response = new ApiResponseDTO(true, updatedUser, "User updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ApiResponseDTO response = new ApiResponseDTO(false, null, "Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            ApiResponseDTO response = new ApiResponseDTO(false, null, "Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
        // Additional catch blocks for other exceptions if necessary
    }


}
