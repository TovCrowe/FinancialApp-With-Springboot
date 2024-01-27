package com.example.FinancialTransactions.Repositorys;

import com.example.FinancialTransactions.Models.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyModel, Long> {
    @Query("SELECT c FROM CurrencyModel c WHERE c.currencyCode = :name")
    Optional<CurrencyModel> findByName(@Param("name") String currencyCode);

    @Modifying
    @Query("DELETE FROM CurrencyModel c WHERE c.currencyCode = :name")
    void deleteByName(@Param("name") String currencyCode);
}

