package com.baek.expense;

import static com.baek.expense.Expense.Type.BREAKFAST;
import static com.baek.expense.Expense.Type.DINNER;

public class Expense {
    public Type type;
    public int amount;

    public Expense(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    boolean isOverage(){
        return ((type == DINNER && amount > 5000) ||
                (type == BREAKFAST && amount > 1000));
    }

    boolean isMeal() {
        return type == BREAKFAST || type == DINNER;
    }

    public enum Type{DINNER, BREAKFAST, CAR_RENTAL}
}
