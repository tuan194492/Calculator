package com.example.projecti_20194402;

import java.util.Stack;

public class ProgrammerCalculator {
    private SCOPE scope;
    private String curNum;
    private String expression;
    private String operator;
    public ProgrammerCalculator() {
        scope = SCOPE.NUMBER;
        curNum = "0";
        expression = "";
        operator = " ";
    }
    public void reset() {
        scope = SCOPE.NUMBER;
        curNum = "0";
        expression = "";
        operator = " ";
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
    public SCOPE getScope() {
        return scope;
    }

    public void setScope(SCOPE scope) {
        this.scope = scope;
    }

    public String getCurNum() {
        return curNum;
    }

    public void setCurNum(String curNum) {
        this.curNum = curNum;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int cal(String s) {
        return intEvaluate.evaluate(s);
    }
    public void appendNumber(String s) {
        if (curNum.contains(".")) {
            if (s.equals(".")) return;
        }
        curNum = curNum + s;
    }
    public void buttonDel() {
        if (curNum.length() <= 1) {
            curNum = "0";
        } else {
            curNum = curNum.substring(0, curNum.length() - 1);
        }
    }
    private static class intEvaluate {
        public static int evaluate(String expression)
        {
            char[] tokens = expression.toCharArray(); // danh sach cac ky tu

            // Stack for numbers: 'values'
            Stack<Integer> values = new
                    Stack<Integer>();

            // Stack for Operators: 'ops'
            Stack<String> ops = new
                    Stack<String>();

            for (int i = 0; i < tokens.length; i++)
            {

                // Current token is a
                // whitespace, skip it
                if (tokens[i] == ' ')
                    continue;

                // Current token is a number,
                // push it to stack for numbers
                if ((tokens[i] >= '0' &&
                        tokens[i] <= '9') || tokens[i] == '.')
                {
//                    System.out.println("Catch a number");
                    StringBuffer sbuf = new
                            StringBuffer();

                    // There may be more than one
                    // digits in number
                    while (i < tokens.length &&(
                            (tokens[i] >= '0' &&tokens[i] <= '9')  || (tokens[i] == '.')))
                        sbuf.append(tokens[i++]);
                    values.push(Integer.parseInt(sbuf.
                            toString()));

                    // right now the i points to
                    // the character next to the digit,
                    // since the for loop also increases
                    // the i, we would skip one
                    //  token position; we need to
                    // decrease the value of i by 1 to
                    // correct the offset.
                    i--;
                }

                // Current token is an opening brace,
                // push it to 'ops'
                else if (tokens[i] == '(') {
//                    System.out.println("Catch a (");
                    ops.push(String.valueOf(tokens[i]));
                }

                // Closing brace encountered,
                // solve entire brace
                else if (tokens[i] == ')')
                {
//                    System.out.println("Catch a )");
                    while (!ops.peek().equals("("))
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));
                    ops.pop();
                }

                // Current token is an operator.
                else if ((tokens[i] < '0' ||
                        tokens[i] > '9'))
                {
//                    System.out.println("Catch an op");
                    StringBuffer op = new
                            StringBuffer();

                    // There may be more than one
                    // digits in number
                    while (i < tokens.length &&(
                            tokens[i] < '0' || tokens[i] > '9') && tokens[i] != '(' && tokens[i] != ')')
                        op.append(tokens[i++]);

                    // right now the i points to
                    // the character next to the digit,
                    // since the for loop also increases
                    // the i, we would skip one
                    //  token position; we need to
                    // decrease the value of i by 1 to
                    // correct the offset.
                    i--;
                    // While top of 'ops' has same
                    // or greater precedence to current
                    // token, which is an operator.
                    // Apply operator on top of 'ops'
                    // to top two elements in values stack
                    while (!ops.empty() &&
                            hasPrecedence(op.toString(),
                                    ops.peek()))
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));
//                    System.out.println(op.toString());
                    // Push current token to 'ops'.
                    ops.push(op.toString());
                }
            }

            // Entire expression has been
            // parsed at this point, apply remaining
            // ops to remaining values
            while (!ops.empty())
                values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()));

            // Top of 'values' contains
            // result, return it
            return values.pop();
        }

        // Returns true if 'op2' has higher
        // or same precedence as 'op1',
        // otherwise returns false.
        public static int getPrecedence(String c) {
            switch (c) {
                case "(":
                case ")":
                    return 1;
                case "*":
                case "/":
                case "%":
                    return 2;
                case "+":
                case "-":
                    return 4;
                case "Lsh":
                case "Rsh":
                    return 3;
                case "AND":
                case "NAND":
                    return 5;
                case "XOR":
                    return 6;
                case "OR":
                case "NOR":
                    return 7;
                default:
                    return 4;
            }
        }
        public static boolean hasPrecedence(
                String op1, String op2)
        {
            if (op2.equals("(") || op2.equals(")"))
                return false;
//            if (
//                    ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
//                ||  ((op1 == '*' || op1 == '/') && (op2 == '&' || op2 == '|'))
//                ||  ((op1 == '+' || op1 == '-') && (op2 == '&' || op2 == '|'))
//            )
//                return false;
//            else
//                return true;
            return getPrecedence(op1) >= getPrecedence(op2);
        }
        // AND OR NOT NAND NOR XOR
        // A utility method to apply an
        // operator 'op' on operands 'a'
        // and 'b'. Return the result.
        public static int applyOp(String op,
                                  int b, int a)
        {
            switch (op)
            {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                case "%":
                    return a % b;
                case "/":
                    if (b == 0)
                        throw new
                                UnsupportedOperationException(
                                "Cannot divide by zero");
                    return a / b;
                case "AND":
                    return a&b;
                case "OR":
                    return a|b;
                case "XOR":
                    return a^b;
                case "NOR":
                    return ~(a|b);
                case "NAND":
                    return ~(a&b);
                case "Rsh":
                    return a>>b;
                case "Lsh":
                    return a<<b;
            }
            return 0;
        }
    }
}
