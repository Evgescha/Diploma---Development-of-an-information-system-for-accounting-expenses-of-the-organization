package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Budget extends AbstractEntity {
    private String name;
    @Column(length = 5000)
    private String description;
    private BigDecimal totalAmount;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    @OneToMany
    private List<BudgetAllocation> budgetAllocations = new ArrayList<>();
}
