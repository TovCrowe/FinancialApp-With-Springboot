package com.example.FinancialTransactions.Services;

import com.example.FinancialTransactions.Exceptions.CurrencyNotFoundException;
import com.example.FinancialTransactions.Models.CurrencyModel;
import com.example.FinancialTransactions.Repositorys.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;


    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyModel> getAllCurrencies() {

        return currencyRepository.findAll();
    }

    public CurrencyModel getCurrencyById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<CurrencyModel> currency = currencyRepository.findByName(id);

        if (!currency.isPresent()) {
            throw new CurrencyNotFoundException("Currency cannot be found with id: " + id);
        } else {

            return currency.get();
        }
    }

    public CurrencyModel createCurrency(CurrencyModel currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (currency.getCurrencyCode().isEmpty()) {
            throw new IllegalArgumentException("Currency Code cannot be null");
        }
        if (currency.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Currency Description cannot be null");
        }
        if (currency.getExchangeRate() == null) {
            throw new IllegalArgumentException("Exchange Rate cannot be null");
        }


        return currencyRepository.save(currency);
    }


    public CurrencyModel editCurrency(String id, CurrencyModel updatedCurrency) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid Currency ID");
        }

        if (updatedCurrency == null) {
            throw new IllegalArgumentException("Updated currency information cannot be null");
        }

        Optional<CurrencyModel> existingCurrency = currencyRepository.findByName(id);
        if (existingCurrency.isEmpty()) {
            throw new CurrencyNotFoundException("Currency not found with ID: " + id);
        }

        CurrencyModel currencyToUpdate = existingCurrency.get();

        if (updatedCurrency.getDescription() != null) {
            currencyToUpdate.setDescription(updatedCurrency.getDescription());
        }
        if (updatedCurrency.getExchangeRate() != null) {
            currencyToUpdate.setExchangeRate(updatedCurrency.getExchangeRate());
        }

        return currencyRepository.save(currencyToUpdate);
    }


    public void deleteCurrency(String id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<CurrencyModel> currency = currencyRepository.findByName(id);
        if (!currency.isPresent()) {
            throw new CurrencyNotFoundException("Currency cannot be found with id: " + id);
        }
        currencyRepository.deleteByName(id);
    }

}
