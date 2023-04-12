package com.hescha.moneycounter.model;

import lombok.Data;

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
    private String description;
    private BigDecimal totalAmount;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    @OneToMany
    private List<BudgetAllocation> budgetAllocations = new ArrayList<>();
}
