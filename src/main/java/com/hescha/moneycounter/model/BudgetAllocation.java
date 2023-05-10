package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class BudgetAllocation extends AbstractEntity {
    private BigDecimal allocatedAmount;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @Override
    public String toString() {
        return "BudgetAllocation{" +
                "allocatedAmount=" + allocatedAmount +
                ", budget=" + budget.getName() +
                ", category=" + category.getName() +
                '}';
    }
}
