package com.example.FinancialTransactions.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
public class CurrencyModel {
    @Id
    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "description")
    private String description;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;



    public CurrencyModel() {
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    // Getters and setters
}