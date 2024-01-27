package com.example.FinancialTransactions.Controllers;

import com.example.FinancialTransactions.Exceptions.CurrencyNotFoundException;
import com.example.FinancialTransactions.Models.CurrencyModel;
import com.example.FinancialTransactions.Services.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService  currencyService;


    public CurrencyController(CurrencyService currencyService) {

        this.currencyService = currencyService;
    }
    @GetMapping
    public ResponseEntity<List<CurrencyModel>> getAllCurrencies(){
        List<CurrencyModel> currencies = currencyService.getAllCurrencies();
        return ResponseEntity.ok(currencies);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyModel> getCurrencyById(@PathVariable String id) {
        try {

            CurrencyModel currency = currencyService.getCurrencyById(id);
            return ResponseEntity.ok(currency);
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CurrencyModel> createCurrency(@RequestBody CurrencyModel currency) {

        try {
            CurrencyModel newCurrency = currencyService.createCurrency(currency);
            return ResponseEntity.ok(newCurrency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CurrencyModel> updateCurrency(@PathVariable String id, @RequestBody CurrencyModel currency) {
        try{

            CurrencyModel updatedCurrency = currencyService.editCurrency(id, currency);
            return ResponseEntity.ok(updatedCurrency);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().build();
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable String id) {

        try{
            currencyService.deleteCurrency(id);
            return ResponseEntity.noContent().build();
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
