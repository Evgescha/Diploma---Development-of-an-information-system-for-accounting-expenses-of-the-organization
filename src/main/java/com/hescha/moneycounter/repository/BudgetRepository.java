package com.hescha.moneycounter.repository;

import com.hescha.moneycounter.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByName(String name);

    List<Budget> findByNameContains(String name);

    List<Budget> findByDescription(String description);

    List<Budget> findByDescriptionContains(String description);

    Budget findByTotalAmount(BigDecimal totalAmount);

    Budget findByPeriodStart(LocalDate periodStart);

    Budget findByPeriodEnd(LocalDate periodEnd);

    List<Budget> findByBudgetAllocationsContains(com.hescha.moneycounter.model.BudgetAllocation budgetAllocations);
}
