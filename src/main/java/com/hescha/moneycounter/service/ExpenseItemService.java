package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.model.ExpenseCategory;
import com.hescha.moneycounter.model.ExpenseItem;
import com.hescha.moneycounter.model.User;
import com.hescha.moneycounter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ExpenseItemService extends CrudService<ExpenseItem> {

    private final ExpenseItemRepository repository;
    @Autowired
    private UserRepository userRepository;

    public ExpenseItemService(ExpenseItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<ExpenseItem> findByBudgetAllocation(BudgetAllocation budgetAllocation) {
        return repository.findByBudgetAllocation(budgetAllocation);
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
    }

    public void removeRelated(BudgetAllocation budgetAllocation) {
        List<ExpenseItem> byBudgetAllocation = findByBudgetAllocation(budgetAllocation);

        for (ExpenseItem item : byBudgetAllocation) {
            User user = item.getUser();
            user.getExpenseItems().remove(item);
            userRepository.save(user);
            delete(item.getId());
        }

    }
}
