package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.model.ExpenseCategory;
import com.hescha.moneycounter.model.ExpenseItem;
import com.hescha.moneycounter.model.User;
import com.hescha.moneycounter.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ExpenseItemService extends CrudService<ExpenseItem> {

    private final ExpenseItemRepository repository;

    public ExpenseItemService(ExpenseItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<ExpenseItem> findByName(String name) {
        return repository.findByName(name);
    }

    public List<ExpenseItem> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public ExpenseItem findByAmount(BigDecimal amount) {
        return repository.findByAmount(amount);
    }

    public ExpenseItem findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public ExpenseItem findByUser(User User) {
        return repository.findByUser(User);
    }

    public ExpenseItem findByBudgetAllocation(BudgetAllocation budgetAllocation) {
        return repository.findByBudgetAllocation(budgetAllocation);
    }

    public ExpenseItem findByCategory(ExpenseCategory category) {
        return repository.findByCategory(category);
    }


    public ExpenseItem update(Long id, ExpenseItem entity) {
        ExpenseItem read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity ExpenseItem not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(ExpenseItem entity, ExpenseItem read) {
        read.setName(entity.getName());
        read.setAmount(entity.getAmount());
        read.setDate(entity.getDate());
        read.setUser(entity.getUser());
        read.setBudgetAllocation(entity.getBudgetAllocation());
        read.setCategory(entity.getCategory());
    }
}
