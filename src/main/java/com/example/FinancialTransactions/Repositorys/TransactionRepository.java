package com.example.FinancialTransactions.Repositorys;

import com.example.FinancialTransactions.Models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
}
