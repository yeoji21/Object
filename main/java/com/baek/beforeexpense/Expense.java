package com.baek.beforeexpense;

public abstract class Expense {
    public int amount;

    public Expense(int amount) {
        this.amount = amount;
    }

    abstract boolean isMeal();

    abstract boolean isOverage();

}
