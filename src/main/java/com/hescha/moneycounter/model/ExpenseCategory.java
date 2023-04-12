package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ExpenseCategory extends AbstractEntity {
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ExpenseCategory parentCategory;

    @OneToMany
    private List<ExpenseCategory> childCategories = new ArrayList<>();

    @OneToMany
    private List<ExpenseItem> expenseItems = new ArrayList<>();
}
