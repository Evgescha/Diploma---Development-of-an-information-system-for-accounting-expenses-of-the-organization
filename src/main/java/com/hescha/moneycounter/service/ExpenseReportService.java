package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.ExpenseReport;
import com.hescha.moneycounter.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ExpenseReportService extends CrudService<ExpenseReport> {

    private final ExpenseReportRepository repository;

    public ExpenseReportService(ExpenseReportRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<ExpenseReport> findByName(String name) {
        return repository.findByName(name);
    }

    public List<ExpenseReport> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<ExpenseReport> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<ExpenseReport> findByDescriptionContains(String description) {
        return repository.findByDescriptionContains(description);
    }

    public ExpenseReport findByCreateDate(LocalDate createDate) {
        return repository.findByCreateDate(createDate);
    }

    public List<ExpenseReport> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<ExpenseReport> findByStatusContains(String status) {
        return repository.findByStatusContains(status);
    }

    public List<ExpenseReport> findByExpenseItemsContains(com.hescha.moneycounter.model.ExpenseItem expenseItems) {
        return repository.findByExpenseItemsContains(expenseItems);
    }


    public ExpenseReport update(Long id, ExpenseReport entity) {
        ExpenseReport read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity ExpenseReport not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(ExpenseReport entity, ExpenseReport read) {
        read.setName(entity.getName());
        read.setDescription(entity.getDescription());
        read.setCreateDate(entity.getCreateDate());
        read.setStatus(entity.getStatus());
        read.setExpenseItems(entity.getExpenseItems());
    }
}
