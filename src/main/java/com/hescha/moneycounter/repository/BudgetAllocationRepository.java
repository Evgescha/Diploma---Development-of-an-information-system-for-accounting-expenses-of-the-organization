package com.hescha.moneycounter.repository;

import com.hescha.moneycounter.model.Budget;
import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface BudgetAllocationRepository extends JpaRepository<BudgetAllocation, Long> {
}
