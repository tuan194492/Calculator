package com.example.projecti_20194402;

public class ScientificCalculator {
    private SCOPE scope;
    private String expression;
    private String currentNumber;
    private char operator;

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public SCOPE getScope() {
        return scope;
    }

    public void setScope(SCOPE scope) {
        this.scope = scope;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public ScientificCalculator() {
        scope = SCOPE.NUMBER;
        expression = new String("");
        currentNumber = new String("0");
        operator = ' ';
    }

    public float compute() {
        float res = EvaluateString.evaluate(expression);
        return res;
    }
    public float cal(String s) {
        return EvaluateString.evaluate(s);
    }
    public void appendNumber(String s) {
        if (scope == SCOPE.OPERATOR) {
            setCurrentNumber("0");
        }
        if (getScope() == SCOPE.BEFORE_COMPUTE) {
            buttonCclick();// clear screen
        }
        setCurrentNumber(getCurrentNumber() + s);
        setScope(SCOPE.NUMBER);
    }

    public void buttonCclick() {
        scope = SCOPE.NUMBER;
        expression = "";
        operator = ' ';
        currentNumber = "0";
    }
    public void buttonDel() {
        if (currentNumber.length() <= 1) {
            currentNumber = "0";
        } else {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        }
    }
    public void buttonCEclick() {
        currentNumber = "0";
    }

}
