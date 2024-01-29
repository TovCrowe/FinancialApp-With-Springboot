package com.example.FinancialTransactions.Services;

import com.example.FinancialTransactions.Models.TransactionModel;
import com.example.FinancialTransactions.Repositorys.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository  transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionModel> getAllTransactions(){

        return transactionRepository.findAll();
    }


    public TransactionModel getTransactionById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        return transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction cannot be found with id: " + id));
    }

        @Transactional
        public TransactionModel createTransaction(TransactionModel transaction) {
            if (transaction == null) {
                throw new IllegalArgumentException("Transaction cannot be null");
            }

            if (transaction.getAccount() == null) {
                throw new IllegalArgumentException("Account cannot be null");
            }

            if (transaction.getAmount() == null || transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be a positive value");
            }

            if (transaction.getCurrencyCode() == null) {
                throw new IllegalArgumentException("Currency Code cannot be null");
            }
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setStatus("Pending");
            return transactionRepository.save(transaction);
        }
    }

