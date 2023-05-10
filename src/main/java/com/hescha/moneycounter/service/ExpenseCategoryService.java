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

    public List<ExpenseCategory> findByName(String name) {
        return repository.findByName(name);
    }

    public List<ExpenseCategory> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<ExpenseCategory> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<ExpenseCategory> findByDescriptionContains(String description) {
        return repository.findByDescriptionContains(description);
    }

    public ExpenseCategory findByParentCategory(ExpenseCategory parentCategory) {
        return repository.findByParentCategory(parentCategory);
    }

    public List<ExpenseCategory> findByChildCategoriesContains(com.hescha.moneycounter.model.ExpenseCategory childCategories) {
        return repository.findByChildCategoriesContains(childCategories);
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
        read.setChildCategories(entity.getChildCategories());
    }
}
