package com.example.projecti_20194402;

public class Calculator {
    private State state;
    public Calculator() {
        state = new State();
        state.setCurrentNumber("0");
        state.setPrevNumber("");
        state.setOperator(' ');
        state.setScope(SCOPE.NUMBER);
    }
    public State getState() {
        return state;
    }
    public void appendNumber(String s) {
        if (state.getScope() == SCOPE.OPERATOR) {
            state.setCurrentNumber("0");
        }
        if (state.getScope() == SCOPE.BEFORE_COMPUTE) {
            clear();
        }
        state.setCurrentNumber(state.getCurrentNumber() + s);
        state.setScope(SCOPE.NUMBER);
    }
        // Tra ve prev Expression
    public void compute() {
        Float curNum, prevNum;
        curNum = Float.parseFloat(state.getCurrentNumber());
        try {
            prevNum = Float.parseFloat(state.getPrevNumber());
        } catch (Exception e) {
            prevNum = 0.0f;
        }
//        String prevExpression = state.getPrevNumber() + " " + state.getOperator() + " " + state.getCurrentNumber() + " ";
        Float res = 0.0f;
        switch (state.getOperator()) {
            case ' ' :
                prevNum = curNum;
                res = curNum;
                break;
            case '+' :
                res = curNum + prevNum;
                prevNum = curNum;
                break;
            case '-' :
                res = prevNum - curNum;
                prevNum = curNum;
                break;
            case '*' :
                res = prevNum * curNum;
                prevNum = curNum;
                break;
            case '/' :
                res = prevNum / curNum;
                prevNum = curNum;
                break;
        }
//        prevExpression = prevExpression + res.toString();
//        return prevExpression;
        state.setPrevNumber(prevNum.toString());
        state.setCurrentNumber(String.valueOf(res));
        state.setScope(SCOPE.BEFORE_COMPUTE);
    }


    public void chooseOperator(char c) {
        if (state.getScope() == SCOPE.OPERATOR) {
            if (state.getOperator() == ' ') {
                state.setPrevNumber(state.getCurrentNumber());
            }
        } else if (state.getScope() == SCOPE.NUMBER) {
            compute();
            state.setPrevNumber(state.getCurrentNumber());
        } else if (state.getScope() == SCOPE.BEFORE_COMPUTE) {

            state.setPrevNumber(state.getCurrentNumber());
        }
        state.setOperator(c);
        state.setScope(SCOPE.OPERATOR);
    }
    public void clear() {
        state.setCurrentNumber("0");
        state.setPrevNumber("");
        state.setOperator(' ');
        state.setScope(SCOPE.NUMBER);
    }
    public void sp() {
        state.setCurrentNumber(String.valueOf(- Float.parseFloat(state.getCurrentNumber())));
    }
    public void sqrt() {
        if (Float.parseFloat(state.getCurrentNumber()) < 0) return;
        state.setCurrentNumber(String.valueOf((float)Math.sqrt(Float.parseFloat(state.getCurrentNumber()))));
    }
    public void root() {
        state.setCurrentNumber(String.valueOf(Float.parseFloat(state.getCurrentNumber()) * Float.parseFloat(state.getCurrentNumber())));
    }
    public void btnCEclick() {
        state.setCurrentNumber("0");
    }
    public void Inverse() {
        if (Float.parseFloat(state.getCurrentNumber()) == 0) return;
        state.setCurrentNumber(String.valueOf((float)(float)1/Float.parseFloat(state.getCurrentNumber())));
    }
    public void percent() {
        state.setCurrentNumber(String.valueOf(Float.parseFloat(state.getCurrentNumber())/100));
    }
    public void buttonDelete() {
        if (state.getCurrentNumber().length() == 1) {
            state.setCurrentNumber("0");
            return;
        }
        state.setCurrentNumber(state.getCurrentNumber().substring(0, state.getCurrentNumber().length() - 1));
    }
}