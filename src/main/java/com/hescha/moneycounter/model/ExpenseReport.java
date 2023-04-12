package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ExpenseReport extends AbstractEntity {
    private String name;
    private String description;
    private LocalDate createDate;
    private String status;

    @OneToMany
    private List<ExpenseItem> expenseItems = new ArrayList<>();
}
