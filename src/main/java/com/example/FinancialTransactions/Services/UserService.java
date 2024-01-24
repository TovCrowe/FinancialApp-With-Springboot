package com.example.FinancialTransactions.Services;

import com.example.FinancialTransactions.Exceptions.UserNotFoundException;
import com.example.FinancialTransactions.Models.UserModel;
import com.example.FinancialTransactions.Repositorys.UserRepository;
import com.example.FinancialTransactions.Utilities.PasswordHash;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHash passwordHash;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHash passwordHash) {
        this.userRepository = userRepository;
        this.passwordHash = passwordHash;
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
           throw new UserNotFoundException("User not found with ID:" + id);
        }
    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public UserModel createUser(UserModel user){
        if(user == null){
            throw new IllegalArgumentException("User Cannot be null");
        }
        if(user.getUsername() == null || user.getUsername().trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if(user.getEmail() == null || user.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if(user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty()){
            throw new IllegalArgumentException("Password cannot be null");
        } else {
            String hashPassword = passwordHash.hashPassword(user.getPasswordHash());
            user.setPasswordHash(hashPassword);
        }

        return userRepository.save(user);
    }
    @Transactional
    public UserModel editUser(UserModel user,Long id) {
        if (user.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        Optional<UserModel> userEdit = userRepository.findById(id);
        if (userEdit.isPresent()) {
            UserModel userExistent = userEdit.get();
            userExistent.setUsername(user.getUsername());
            userExistent.setEmail(user.getEmail());

            // Hash password only if it's provided (and ideally, only if it's different)
            if (user.getPasswordHash() != null && !user.getPasswordHash().trim().isEmpty()) {
                String hash = passwordHash.hashPassword(user.getPasswordHash());
                userExistent.setPasswordHash(hash);
            }

            return userRepository.save(userExistent);
        } else {
            throw new UserNotFoundException("User cannot be found with ID " + user.getUserId());
        }
    }
    @Transactional
    public void deleteUser(Long id){
        if(id == null){
            throw new IllegalArgumentException("The id cannot be null");
        }
        Optional<UserModel> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found with id:" + id);
        }
        userRepository.deleteById(id);
    }
}
