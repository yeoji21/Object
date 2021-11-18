package com.baek.expensereport;

import static com.baek.expensereport.Expense.Type.BREAKFAST;
import static com.baek.expensereport.Expense.Type.DINNER;

public class CarRentalExpense extends Expense {
    public CarRentalExpense(int amount) {
        super(Type.CAR_RENTAL, amount);
    }

    @Override
    boolean isOverage() {
        return false;
    }

    @Override
    String getName() {
        return "Car Rental";
    }

    @Override
    boolean isMeal() {
        return false;
    }
}
