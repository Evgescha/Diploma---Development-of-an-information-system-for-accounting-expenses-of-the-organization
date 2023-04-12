package com.hescha.moneycounter.repository;

import com.hescha.moneycounter.model.ExpenseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ExpenseReportRepository extends JpaRepository<ExpenseReport, Long> {
    List<ExpenseReport> findByName(String name);

    List<ExpenseReport> findByNameContains(String name);

    List<ExpenseReport> findByDescription(String description);

    List<ExpenseReport> findByDescriptionContains(String description);

    ExpenseReport findByCreateDate(LocalDate createDate);

    List<ExpenseReport> findByStatus(String status);

    List<ExpenseReport> findByStatusContains(String status);

    List<ExpenseReport> findByExpenseItemsContains(com.hescha.moneycounter.model.ExpenseItem expenseItems);
}
