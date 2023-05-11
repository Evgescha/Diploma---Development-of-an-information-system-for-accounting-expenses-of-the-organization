package com.hescha.moneycounter.repository;

import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.model.ExpenseCategory;
import com.hescha.moneycounter.model.ExpenseItem;
import com.hescha.moneycounter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {
    List<ExpenseItem> findByBudgetAllocation(BudgetAllocation budgetAllocation);
}
