package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.Budget;
import com.hescha.moneycounter.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BudgetService extends CrudService<Budget> {

    private final BudgetRepository repository;

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
}
