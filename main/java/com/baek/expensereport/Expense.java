package com.baek.expensereport;

import static com.baek.expensereport.Expense.Type.BREAKFAST;
import static com.baek.expensereport.Expense.Type.DINNER;

public abstract class Expense {

    abstract boolean isOverage();

    abstract String getName();

    abstract boolean isMeal();

    public enum Type {DINNER, BREAKFAST, CAR_RENTAL};

    public Type type;
    public int amount;

    public Expense(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}