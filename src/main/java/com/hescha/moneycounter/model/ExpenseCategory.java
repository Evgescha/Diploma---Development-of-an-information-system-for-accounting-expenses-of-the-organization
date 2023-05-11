package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Column;
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
    @Column(length = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ExpenseCategory parentCategory;

    @OneToMany
    private List<ExpenseCategory> childCategories = new ArrayList<>();

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
