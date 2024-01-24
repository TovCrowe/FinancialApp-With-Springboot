package com.example.FinancialTransactions.Services;

import com.example.FinancialTransactions.Exceptions.AccountNotFoundException;
import com.example.FinancialTransactions.Models.AccountModel;
import com.example.FinancialTransactions.Repositorys.AccountRepository;
import com.example.FinancialTransactions.Repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountModel> getAllAccounts(){
        return accountRepository.findAll();
    }
    public Optional<AccountModel> getAccountById(Long id){
        if(id == null) {
            throw new IllegalArgumentException("Account id cannot be null");
        }
        Optional<AccountModel> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account;
        } else {
            throw new AccountNotFoundException("Account cannot be found with ID: " + id);
        }
    }
    @Transactional
    public AccountModel createAccount(AccountModel account){
        if(account == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if(account.getUser() == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        if(account.getAccountNumber() == null){
            throw new IllegalArgumentException("Account Number cannot be null");
        }
        if(account.getBalance() == null){
            throw new IllegalArgumentException("Balance cannot be null");
        }
        if(account.getAccountType().isEmpty()){
            account.setAccountType("Normal");
        }
        if(account.getCurrencyCode().isEmpty()){
            account.setCurrencyCode("USD");
        }
        return accountRepository.save(account);
    }

    @Transactional
    public AccountModel editAccount(Long id, AccountModel updatedAccount) {
        Objects.requireNonNull(id, "ID cannot be null");

        AccountModel existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));

        // Update properties based on non-null values from updatedAccount
        if (updatedAccount.getBalance() != null) {
            existingAccount.setBalance(updatedAccount.getBalance());
        }
        if (updatedAccount.getAccountType() != null) {
            existingAccount.setAccountType(updatedAccount.getAccountType());
        }
        if (updatedAccount.getCurrencyCode() != null) {
            existingAccount.setCurrencyCode(updatedAccount.getCurrencyCode());
        }

        return accountRepository.save(existingAccount);
    }
    @Transactional
    public void deleteAccount(Long id){
        if(id == null){
            throw new IllegalArgumentException("Account id cannot be null");
        }
        Optional<AccountModel> account = accountRepository.findById(id);
        if(!account.isPresent()){
            throw new AccountNotFoundException("Account cannot be found with id: " + id);
        }
        accountRepository.deleteById(id);
    }
}



