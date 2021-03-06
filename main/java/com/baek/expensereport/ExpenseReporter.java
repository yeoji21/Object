package com.baek.expensereport;


public class ExpenseReporter {
    private final ExpenseReport expenseReport = new ExpenseReport();
    private ReportPrinter printer;

    public void printReport(ReportPrinter printer) {
        this.printer = printer;

        expenseReport.totalsUpExpenses();
        printExpensesAndTotals();
    }

    private void printExpensesAndTotals() {
        printHeader();
        printExpenses();
        printTotals();
    }

    private void printExpenses() {
        for (Expense expense : expenseReport.expenses)
            printer.print(String.format("%s\t%s\t$%.02f\n",
                    expense.isOverage() ? "X" : " ",
                    expense.getName(), penniesToDollars(expense.amount)));
    }

    private void printTotals() {
        printer.print(String.format("\nMeal expenses $%.02f", penniesToDollars(expenseReport.mealExpenses)));
        printer.print(String.format("\nTotal $%.02f", penniesToDollars(expenseReport.total)));
    }

    private double penniesToDollars(int total) {
        return total / 100.0;
    }

    private void printHeader() {
        printer.print("Expenses " + getDate() + "\n");
    }

    public void addExpense(Expense expense) {
        expenseReport.addExpense(expense);
    }

    private String getDate() {
        return "9/12/2002";
    }
}