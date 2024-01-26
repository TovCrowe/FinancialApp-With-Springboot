package com.example.FinancialTransactions.Repositorys;

import com.example.FinancialTransactions.Models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}

