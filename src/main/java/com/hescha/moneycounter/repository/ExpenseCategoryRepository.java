package com.hescha.moneycounter.repository;

import com.hescha.moneycounter.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    List<ExpenseCategory> findByName(String name);

    List<ExpenseCategory> findByNameContains(String name);

    List<ExpenseCategory> findByDescription(String description);

    List<ExpenseCategory> findByDescriptionContains(String description);

    ExpenseCategory findByParentCategory(ExpenseCategory parentCategory);

    List<ExpenseCategory> findByChildCategoriesContains(com.hescha.moneycounter.model.ExpenseCategory childCategories);

    List<ExpenseCategory> findByExpenseItemsContains(com.hescha.moneycounter.model.ExpenseItem expenseItems);
}
