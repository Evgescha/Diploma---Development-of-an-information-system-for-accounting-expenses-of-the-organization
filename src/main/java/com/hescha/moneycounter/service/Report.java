package com.hescha.moneycounter.service;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Report {
    BigDecimal totalAmount;
    int expenseItemsCount;
    Map<String, BigDecimal> amountByCategory;
    int uniqueUserCount;
    Map<String, BigDecimal> amountByUser;
}
