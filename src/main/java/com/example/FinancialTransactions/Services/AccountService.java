package com.example.FinancialTransactions.Services;

import com.example.FinancialTransactions.Models.AccountModel;
import com.example.FinancialTransactions.Repositorys.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
