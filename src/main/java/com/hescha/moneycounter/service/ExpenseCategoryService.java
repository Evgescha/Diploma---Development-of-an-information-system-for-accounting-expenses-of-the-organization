package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.ExpenseCategory;
import com.hescha.moneycounter.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExpenseCategoryService extends CrudService<ExpenseCategory> {

    private final ExpenseCategoryRepository repository;

    public ExpenseCategoryService(ExpenseCategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ExpenseCategory update(Long id, ExpenseCategory entity) {
        ExpenseCategory read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity ExpenseCategory not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(ExpenseCategory entity, ExpenseCategory read) {
        read.setName(entity.getName());
        read.setDescription(entity.getDescription());
        read.setParentCategory(entity.getParentCategory());
    }
}
