package com.baek.expense;



public class MockReportPrinter implements ReportPrinter{

    private String printedText = "";

    @Override
    public void print(String text) {
        printedText += text;
    }

    public String getText() {
        return printedText;
    }
}