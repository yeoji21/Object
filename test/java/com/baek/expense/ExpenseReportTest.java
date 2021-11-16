package com.baek.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.baek.expense.Expense.*;
import static com.baek.expense.Expense.Type.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpenseReportTest {
    private ExpenseReporter report = new ExpenseReporter();
    private MockReportPrinter printer = new MockReportPrinter();

    @BeforeEach
    public void setUp() {
        report = new ExpenseReporter();
        printer = new MockReportPrinter();
    }

    @Test
    public void printEmpty(){
        //given
        report.printReport(printer);

        assertThat("Expenses 9/12/2002\n" +
                "\n" +
                "Meal expenses $0.00\n" +
                "Total $0.00").isEqualTo(printer.getText());
    }

    @Test
    public void printOneDinner(){
        report.addExpense(new Expense(DINNER, 1678));
        report.printReport(printer);

        assertThat("Expenses 9/12/2002\n" +
                " \tDinner\t$16.78\n" +
                "\n" +
                "Meal expenses $16.78\n" +
                "Total $16.78").isEqualTo(printer.getText());
    }

    @Test
    public void twoMeals() throws Exception{
        report.addExpense(new Expense(DINNER, 1000));
        report.addExpense(new Expense(BREAKFAST, 500));
        report.printReport(printer);

        assertThat("Expenses 9/12/2002\n" +
                " \tDinner\t$10.00\n" +
                " \tBreakfast\t$5.00\n" +

                "\n" +
                "Meal expenses $15.00\n" +
                "Total $15.00").isEqualTo(printer.getText());
    }

    @Test
    public void twoMealsAndCarRental() throws Exception{
        report.addExpense(new Expense(DINNER, 1000));
        report.addExpense(new Expense(BREAKFAST, 500));
        report.addExpense(new Expense(CAR_RENTAL, 50000));
        report.printReport(printer);

        assertThat("Expenses 9/12/2002\n" +
                " \tDinner\t$10.00\n" +
                " \tBreakfast\t$5.00\n" +
                " \tCar Rental\t$500.00\n" +
                "\n" +
                "Meal expenses $15.00\n" +
                "Total $515.00").isEqualTo(printer.getText());
    }

    @Test
    public void overages(){
        report.addExpense(new Expense(BREAKFAST, 1000));
        report.addExpense(new Expense(BREAKFAST, 1001));
        report.addExpense(new Expense(DINNER, 5000));
        report.addExpense(new Expense(DINNER, 5001));
        report.printReport(printer);

        assertThat("Expenses 9/12/2002\n" +
                " \tBreakfast\t$10.00\n" +
                "X\tBreakfast\t$10.01\n" +
                " \tDinner\t$50.00\n" +
                "X\tDinner\t$50.01\n" +
                "\n" +
                "Meal expenses $120.02\n" +
                "Total $120.02").isEqualTo(printer.getText());
    }
}
