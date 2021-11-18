package com.baek.expensereport;

import static com.baek.expensereport.Expense.Type.BREAKFAST;
import static com.baek.expensereport.Expense.Type.DINNER;

public class BreakfastExpense extends Expense {
    public BreakfastExpense(int amount) {
        super(Type.BREAKFAST, amount);
    }

    @Override
    boolean isOverage() {
        return amount > 1000;
    }

    @Override
    String getName() {
        return "Breakfast";
    }

    @Override
    boolean isMeal() {
        return true;
    }
}
