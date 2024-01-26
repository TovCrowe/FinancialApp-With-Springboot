package com.example.FinancialTransactions.Controllers;

import com.example.FinancialTransactions.DTO.ApiResponseDTO;
import com.example.FinancialTransactions.Exceptions.AccountNotFoundException;
import com.example.FinancialTransactions.Models.AccountModel;
import com.example.FinancialTransactions.Services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountModel>> getAllAccounts(){
        List<AccountModel> list = accountService.getAllAccounts();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountModel> getAccountById(@PathVariable Long id){
        try {
            AccountModel account = accountService.getAccountById(id);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountModel account){
        try {
            AccountModel newAccount = accountService.createAccount(account);
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);

        } catch (IllegalArgumentException e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> editAccount(@PathVariable Long id, @RequestBody AccountModel updatedAccount) {
        try {
            AccountModel editedAccount = accountService.editAccount(id, updatedAccount);
            ApiResponseDTO response = new ApiResponseDTO(true, editedAccount, "Account updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponseDTO response = new ApiResponseDTO(false, null, "Bad Request: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (AccountNotFoundException e) {
            ApiResponseDTO response = new ApiResponseDTO(false, null, "Account not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteAccount(@PathVariable Long id) {
        try {

            accountService.deleteAccount(id);
            ApiResponseDTO response = new ApiResponseDTO(true, null, "Account deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponseDTO response = new ApiResponseDTO(false, null, "Bad Request: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (AccountNotFoundException e) {
            ApiResponseDTO response = new ApiResponseDTO(false, null, "Account not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}

