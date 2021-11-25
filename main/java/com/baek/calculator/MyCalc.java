package com.baek.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyCalc {
    private List<String> operators;
    private List<Double> numbers;
    private String expression;

    public MyCalc(String expression) {
        this.expression = expression.trim();
    }

    public double calculate() {
        double result;
        if (expression.isEmpty())
            result = 0;
        else if (isNotContainOperator())
            result = Integer.parseInt(expression);
        else
            result = runCalculation();

        return result;
    }

    private double runCalculation() {
        DivideNumbersAndOperatorsIntoLists();
        for(;hasMultipleOrDivideOperator();calculateMultipleAndDivide());
        for(;operatorListNotEmpty();calculatePlusAndMinus());

        return getResultValue();
    }

    private void calculatePlusAndMinus() {
        if (getFirstOperator("+", "-").equals("+"))
            applyResultToLists(getIndexOfFindOperator("+"), getPlusValue());
        else
            applyResultToLists(getIndexOfFindOperator("-"), getMinusValue());
    }

    private void calculateMultipleAndDivide() {
        if (getFirstOperator("*", "/").equals("*"))
            applyResultToLists(getIndexOfFindOperator("*"), getMultipleValue());
        else
            applyResultToLists(getIndexOfFindOperator("/"), getDivideValue());
    }

    private boolean operatorListNotEmpty() {
        return !operators.isEmpty();
    }

    private void DivideNumbersAndOperatorsIntoLists() {
        operators = getOperatorList();
        numbers = getNumberList();
    }

    private int getIndexOfFindOperator(String o) {
        return operators.indexOf(o);
    }

    private Double getResultValue() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return Double.valueOf(formatter.format(numbers.get(0)));
    }

    private boolean isNotContainOperator() {
        return !expression.contains("+") && !expression.contains("-") && !expression.contains("*") && !expression.contains("/");
    }

    private List<String> getOperatorList() {
        String operatorString = expression.replaceAll("[.\\d]", "");
        List<String> operators = new ArrayList<>();
        for (int i = 0; i < operatorString.length(); i++)
            operators.add(String.valueOf(operatorString.charAt(i)));
        return operators;
    }

    private List<Double> getNumberList() {
        convertNegativeNumberAtTheFront();
        return Arrays.stream(expression.split("[^.\\d]")).map(Double::parseDouble).collect(Collectors.toList());
    }

    private void convertNegativeNumberAtTheFront() {
        if (expression.charAt(0)=='-')
            expression = expression.replaceFirst("[-]", "0-");
    }

    private boolean hasMultipleOrDivideOperator() {
        return operators.contains("*") || operators.contains("/");
    }

    private String getFirstOperator(String first, String second) {
        return operators.stream().filter(o -> o.equals(first) || o.equals(second)).findFirst().orElse(null);
    }

    private double getMultipleValue() {
        int index = getIndexOfFindOperator("*");
        return numbers.get(index) * numbers.get(index + 1);
    }

    private void applyResultToLists(int index, double resultValue) {
        numbers.add(index, resultValue);
        numbers.remove(index + 1);
        numbers.remove(index + 1);
        operators.remove(index);
    }

    private double getDivideValue() {
        int index = getIndexOfFindOperator("/");
        divideByZero(index);
        return numbers.get(index) / numbers.get(index + 1);
    }

    private void divideByZero(int index) {
        if (numbers.get(index + 1)==0 )
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
    }

    private double getPlusValue() {
        return numbers.get(getIndexOfFindOperator("+")) + numbers.get(getIndexOfFindOperator("+") + 1);
    }

    private double getMinusValue() {
        return numbers.get(getIndexOfFindOperator("-")) - numbers.get(getIndexOfFindOperator("-") + 1);
    }
}
