package com.baek.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyCalc {
    private List<String> operators;
    private List<Integer> splitNumber;
    private String expression;

    public MyCalc(String expression) {
        this.expression = expression;
    }

    public int calculate() {
        if (expression.isEmpty())
            return 0;
        if (isNotContainOperation())
            return Integer.parseInt(expression);

        splitNumberAndOperatorToList();
        while (operatorListNotEmpty()) {
            if (hasMultipleOrDivideOperator())
                calculateMultipleAndDivide();
            else
                calculatePlusAndMinus();
        }
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

    private void splitNumberAndOperatorToList() {
        operators = getOperatorList();
        splitNumber = getSplitNumberList();
    }

    private int getIndexOfFindOperator(String o) {
        return operators.indexOf(o);
    }

    private Integer getResultValue() {
        return splitNumber.get(0);
    }

    private boolean isNotContainOperation() {
        return !expression.contains("+") && !expression.contains("-") && !expression.contains("*") && !expression.contains("/");
    }

    private List<String> getOperatorList() {
        String operatorString = expression.replaceAll("\\d", "");
        List<String> operators = new ArrayList<>();
        for (int i = 0; i < operatorString.length(); i++)
            operators.add(String.valueOf(operatorString.charAt(i)));
        return operators;
    }

    private List<Integer> getSplitNumberList() {
        return Arrays.stream(expression.split("\\D")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private boolean hasMultipleOrDivideOperator() {
        return operators.contains("*") || operators.contains("/");
    }

    private String getFirstOperator(String first, String second) {
        return operators.stream().filter(o -> o.equals(first) || o.equals(second)).findFirst().orElse(null);
    }

    private int getMultipleValue() {
        int index = getIndexOfFindOperator("*");
        return splitNumber.get(index) * splitNumber.get(index + 1);
    }

    private void applyResultToLists(int index, int resultValue) {
        splitNumber.add(index, resultValue);
        splitNumber.remove(index + 1);
        splitNumber.remove(index + 1);
        operators.remove(index);
    }

    private int getDivideValue() {
        int index = getIndexOfFindOperator("/");
        if (splitNumber.get(index + 1)==0)
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        return splitNumber.get(index) / splitNumber.get(index + 1);
    }

    private int getPlusValue() {
        return splitNumber.get(getIndexOfFindOperator("+")) + splitNumber.get(getIndexOfFindOperator("+") + 1);
    }

    private int getMinusValue() {
        return splitNumber.get(getIndexOfFindOperator("-")) - splitNumber.get(getIndexOfFindOperator("-") + 1);
    }
}
