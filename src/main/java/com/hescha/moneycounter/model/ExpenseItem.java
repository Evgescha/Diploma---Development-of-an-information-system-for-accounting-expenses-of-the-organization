package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class ExpenseItem extends AbstractEntity {
    private String name;
    private BigDecimal amount;
    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User User;

    @ManyToOne
    @JoinColumn(name = "budgetAllocation_id")
    private BudgetAllocation budgetAllocation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;
}
