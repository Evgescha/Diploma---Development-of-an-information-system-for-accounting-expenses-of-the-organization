package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.Budget;
import com.hescha.moneycounter.model.ExpenseItem;
import com.hescha.moneycounter.model.User;
import com.hescha.moneycounter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BudgetService extends CrudService<Budget> {

    private final BudgetRepository repository;
    @Autowired
    private ExpenseItemService expenseItemService;

    public BudgetService(BudgetRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Budget> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Budget> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<Budget> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<Budget> findByDescriptionContains(String description) {
        return repository.findByDescriptionContains(description);
    }

    public Budget findByTotalAmount(BigDecimal totalAmount) {
        return repository.findByTotalAmount(totalAmount);
    }

    public Budget findByPeriodStart(LocalDate periodStart) {
        return repository.findByPeriodStart(periodStart);
    }

    public Budget findByPeriodEnd(LocalDate periodEnd) {
        return repository.findByPeriodEnd(periodEnd);
    }

    public List<Budget> findByBudgetAllocationsContains(com.hescha.moneycounter.model.BudgetAllocation budgetAllocations) {
        return repository.findByBudgetAllocationsContains(budgetAllocations);
    }


    public Budget update(Long id, Budget entity) {
        Budget read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Budget not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Budget entity, Budget read) {
        read.setName(entity.getName());
        read.setDescription(entity.getDescription());
        read.setTotalAmount(entity.getTotalAmount());
        read.setPeriodStart(entity.getPeriodStart());
        read.setPeriodEnd(entity.getPeriodEnd());
    }

    public Report generateReport(Budget budget, LocalDate startDate, Integer days) {
        if (days <= 0) days = 30;
        LocalDate endDate = startDate.plusDays(days);

            List<ExpenseItem> expenseItems = expenseItemService.readAll()
                .stream()
                .filter(expenseItem -> expenseItem.getDate().isAfter(startDate) && expenseItem.getDate().isBefore(endDate))
                .filter(expenseItem -> Objects.equals(expenseItem.getBudgetAllocation().getBudget().getId(), budget.getId()))
                .collect(Collectors.toList());

        int expenseItemsCount = expenseItems.size();
        BigDecimal totalAmount = expenseItems.stream()
                .map(ExpenseItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // existing code...

        Map<String, BigDecimal> amountByCategory = expenseItems.stream()
                .collect(Collectors.groupingBy(expenseItem -> expenseItem.getBudgetAllocation().getCategory().getName(),
                        Collectors.reducing(BigDecimal.ZERO, ExpenseItem::getAmount, BigDecimal::add)));

        Set<User> uniqueUsers = expenseItems.stream()
                .map(ExpenseItem::getUser)
                .collect(Collectors.toSet());

        int uniqueUserCount = uniqueUsers.size();

        Map<String, BigDecimal> amountByUser = expenseItems.stream()
                .collect(Collectors.groupingBy(expenseItem -> expenseItem.getUser().getUsername(),
                        Collectors.reducing(BigDecimal.ZERO, ExpenseItem::getAmount, BigDecimal::add)));

        Report report = new Report();
        report.setTotalAmount(totalAmount);
        report.setExpenseItemsCount(expenseItemsCount);
        report.setAmountByCategory(amountByCategory);
        report.setUniqueUserCount(uniqueUserCount);
        report.setAmountByUser(amountByUser);

        return report;
    }
}
