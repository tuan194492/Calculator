package com.example.projecti_20194402;

public class ScientificCalculator {
    private SCOPE scope;
    private String expression;
    private String s_Expression;

    public String getS_Expression() {
        return s_Expression;
    }

    public void setS_Expression(String s_Expression) {
        this.s_Expression = s_Expression;
    }

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
        s_Expression = new String("");
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
        s_Expression = "";
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

    // unary operation
    public float unaryOperationCal(String s, String opera) {
        switch (opera) {
            case "abs":
                return Math.abs(Float.parseFloat(s));
            case "sqrt":
                return (float) Math.sqrt(Float.parseFloat(s));
            case "floor":
                return (int) Float.parseFloat(s);
            case "ceil":
                return (int) Float.parseFloat(s) + 1;
            case "sin":
                return (float) Math.sin(Float.parseFloat(s));
            case "cos":
                return (float) Math.cos(Float.parseFloat(s));
            case "tan":
                return (float) Math.tan(Float.parseFloat(s));
            case "log":
                return (float) Math.log10(Float.parseFloat(s));
            case "ln":
                return (float) Math.log(Float.parseFloat(s));
            case "rand":
                return (float) Math.random();
            case "sqr":
                return Float.parseFloat(s) * Float.parseFloat(s);
            case "inverse":
                return 1/Float.parseFloat(s);
        }
        return 0;
    }

    public int findLastSubExpression(String s) {
        int vitri = 0;
        int tmp = 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == ')') tmp++;
            if (s.charAt(i) == '(') tmp--;
            if (tmp == 0) {
                vitri = i;
                break;
            }
        }
        return vitri;
    }

    public int findLastNumber(String s) {
        int length = s.length();
        int i = length - 1;
        while (i >= 0) {
            if (
                    s.charAt(i) == '0' ||
                    s.charAt(i) == '1' ||
                    s.charAt(i) == '2' ||
                    s.charAt(i) == '3' ||
                    s.charAt(i) == '4' ||
                    s.charAt(i) == '5' ||
                    s.charAt(i) == '6' ||
                    s.charAt(i) == '7' ||
                    s.charAt(i) == '8' ||
                    s.charAt(i) == '9' ||
                    s.charAt(i) == '.') {
                i--;
            }
        }
        return i;
    }

}
