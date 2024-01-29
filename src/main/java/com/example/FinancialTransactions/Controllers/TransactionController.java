package com.example.FinancialTransactions.Controllers;

import com.example.FinancialTransactions.Models.TransactionModel;
import com.example.FinancialTransactions.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionModel>> getAllTransactions() {
        List<TransactionModel> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionModel> getTransactionById(@PathVariable Long id) {
       try{

           TransactionModel transaction = transactionService.getTransactionById(id);
           return ResponseEntity.ok(transaction);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.notFound().build();
       }
    }
    @PostMapping
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionModel transaction) {
       try{
           TransactionModel createdTransaction = transactionService.createTransaction(transaction);
           return ResponseEntity.ok(createdTransaction);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().build();
       }
    }
}

