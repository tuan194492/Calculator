package com.example.projecti_20194402;

public class State {
    private String currentNumber = new String();
    private String prevNumber = new String();
    private char operator;
    private SCOPE scope ;

    public SCOPE getScope() {
        return scope;
    }

    public void setScope(SCOPE scope) {
        this.scope = scope;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getPrevNumber() {
        return prevNumber;
    }

    public void setPrevNumber(String prevNumber) {
        this.prevNumber = prevNumber;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

}
