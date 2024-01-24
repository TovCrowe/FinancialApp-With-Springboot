package com.example.FinancialTransactions.Repositorys;

import com.example.FinancialTransactions.Models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}

