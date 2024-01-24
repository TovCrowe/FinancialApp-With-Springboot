package com.example.FinancialTransactions.Repositorys;

import com.example.FinancialTransactions.Models.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyModel, Long> {
}
