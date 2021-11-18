package com.baek.expensereport;

import static com.baek.expensereport.Expense.Type.BREAKFAST;
import static com.baek.expensereport.Expense.Type.DINNER;

public class DinnerExpense extends Expense {
    public DinnerExpense(int amount) {
        super(DINNER, amount);
    }

    @Override
    boolean isOverage() {
        return amount > 5000;
    }

    @Override
    String getName() {
        return "Dinner";
    }

    @Override
    boolean isMeal() {
        return true;
    }
}
