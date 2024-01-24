package com.example.FinancialTransactions.Repositorys;

import com.example.FinancialTransactions.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
