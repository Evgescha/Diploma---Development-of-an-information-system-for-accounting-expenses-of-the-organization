package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.Budget;
import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.model.ExpenseCategory;
import com.hescha.moneycounter.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class BudgetAllocationService extends CrudService<BudgetAllocation> {

    private final BudgetAllocationRepository repository;

    public BudgetAllocationService(BudgetAllocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public BudgetAllocation findByAllocatedAmount(BigDecimal allocatedAmount) {
        return repository.findByAllocatedAmount(allocatedAmount);
    }

    public BudgetAllocation findByBudget(Budget budget) {
        return repository.findByBudget(budget);
    }

    public BudgetAllocation findByCategory(ExpenseCategory category) {
        return repository.findByCategory(category);
    }


    public BudgetAllocation update(Long id, BudgetAllocation entity) {
        BudgetAllocation read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity BudgetAllocation not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(BudgetAllocation entity, BudgetAllocation read) {
        read.setAllocatedAmount(entity.getAllocatedAmount());
        read.setBudget(entity.getBudget());
        read.setCategory(entity.getCategory());
    }
}
