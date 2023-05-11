package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private BudgetAllocation budgetAllocation;


}
