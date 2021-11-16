package com.baek.beforeexpense;

public class CarRentalExpense extends Expense{
    public CarRentalExpense(int amount) {
        super(amount);
    }

    @Override
    boolean isMeal() {
        return false;
    }

    @Override
    boolean isOverage() {
        return false;
    }
}
