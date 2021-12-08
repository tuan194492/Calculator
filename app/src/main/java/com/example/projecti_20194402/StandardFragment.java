package com.example.projecti_20194402;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class StandardFragment extends Fragment implements View.OnClickListener {

    private Calculator calculator;
    TextView curNum, prevExpression;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculator = new Calculator();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standard, container, false);
    }
    public void updateDisplay() {
        curNum.setText(beautifyNumber(calculator.getState().getCurrentNumber()));
        prevExpression.setText(beautifyNumber(calculator.getState().getPrevNumber()) + " " + calculator.getState().getOperator());
    }
    public void onStart() {
        super.onStart();
        curNum = (TextView) getView().findViewById(R.id.currentNumber);
        prevExpression = (TextView) getView().findViewById(R.id.prevExpression);
        // number
        getView().findViewById(R.id.btn0).setOnClickListener(this);
        getView().findViewById(R.id.btn1).setOnClickListener(this);
        getView().findViewById(R.id.btn2).setOnClickListener(this);
        getView().findViewById(R.id.btn3).setOnClickListener(this);
        getView().findViewById(R.id.btn4).setOnClickListener(this);
        getView().findViewById(R.id.btn5).setOnClickListener(this);
        getView().findViewById(R.id.btn6).setOnClickListener(this);
        getView().findViewById(R.id.btn7).setOnClickListener(this);
        getView().findViewById(R.id.btn8).setOnClickListener(this);
        getView().findViewById(R.id.btn9).setOnClickListener(this);
        getView().findViewById(R.id.btnDot).setOnClickListener(this);
        // operator
        getView().findViewById(R.id.btnAdd).setOnClickListener(this);
        getView().findViewById(R.id.btnSubtract).setOnClickListener(this);
        getView().findViewById(R.id.btnMul).setOnClickListener(this);
        getView().findViewById(R.id.btnDiv).setOnClickListener(this);
        getView().findViewById(R.id.btnCompute).setOnClickListener(this);
        // C CE delete
        getView().findViewById(R.id.btnDel).setOnClickListener(this);
        getView().findViewById(R.id.btnC).setOnClickListener(this);
        getView().findViewById(R.id.btnCE).setOnClickListener(this);
        // else
        getView().findViewById(R.id.btnPercent).setOnClickListener(this);
        getView().findViewById(R.id.btnSqrt).setOnClickListener(this);
        getView().findViewById(R.id.btnRoot).setOnClickListener(this);
        getView().findViewById(R.id.btnInverse).setOnClickListener(this);
        getView().findViewById(R.id.btnPS).setOnClickListener(this);

        updateDisplay();
    }
    public String beautifyNumber(String s) {
        if (s.equals("") || s.equals(" ")) return s;
        if (Float.parseFloat(s) == 0.0f) {
            return "0";
        }
        if (Float.parseFloat(s) != (int)(Float.parseFloat(s)))
            return String.valueOf(Float.parseFloat(s));
        else return String.valueOf((int) Float.parseFloat(s));

    }

    @Override
    public void onClick(View v) {
        System.out.println(calculator.getState().getCurrentNumber());
        if (v.getId() == R.id.btnC) {
            System.out.println("del");
            calculator.btnCclick();
            updateDisplay();
        }
        else if (v.getId() == R.id.btnCE) {
            calculator.clear();
            updateDisplay();
        }
        else if (v.getId() == R.id.btnDel) {
            if (calculator.getState().getScope() == SCOPE.BEFORE_COMPUTE) return;
            calculator.buttonDelete();
            updateDisplay();
        }
        // Number
        else if (v.getId() == R.id.btn0
        || v.getId() == R.id.btn1
        || v.getId() == R.id.btn2
        || v.getId() == R.id.btn3
        || v.getId() == R.id.btn4
        || v.getId() == R.id.btn5
        || v.getId() == R.id.btn6
        || v.getId() == R.id.btn7
        || v.getId() == R.id.btn8
        || v.getId() == R.id.btn9
        || v.getId() == R.id.btnDot) {
            System.out.println("Press number");
            if (calculator.getState().getCurrentNumber().contains(".") && v.getId() == R.id.btnDot) return;
            if (calculator.getState().getCurrentNumber().contains(".") == false && v.getId() == R.id.btn0 && Float.parseFloat(calculator.getState().getCurrentNumber()) == 0.0f) return;
            calculator.appendNumber(v.getContentDescription().toString());
            updateDisplay();
        }
        // thuc hien phep tinh + - * / =
        else if (v.getId() == R.id.btnCompute
        || v.getId() == R.id.btnAdd
        || v.getId() == R.id.btnSubtract
        || v.getId() == R.id.btnMul
        || v.getId() == R.id.btnDiv) {
            if (v.getId() == R.id.btnCompute) {
                if (calculator.getState().getScope() == SCOPE.BEFORE_COMPUTE && calculator.getState().getOperator() == ' ') return;
                prevExpression.setText(beautifyNumber(calculator.getState().getPrevNumber()) + " " + calculator.getState().getOperator() + " " + beautifyNumber(calculator.getState().getCurrentNumber()) + " = ");
                calculator.compute();
                curNum.setText(beautifyNumber(calculator.getState().getCurrentNumber()));
            } else {
                calculator.chooseOperator(v.getContentDescription().charAt(0));
                updateDisplay();
            }
            System.out.println("Thuc hien phep tinh");
        }
        else {
            System.out.println("Thuc hien phep tinh voi current number");
            switch (v.getId()) {
                case R.id.btnRoot:
                    if (Float.parseFloat(calculator.getState().getCurrentNumber()) < 0) return;
                    prevExpression.setText("sqrt("+beautifyNumber(calculator.getState().getCurrentNumber())+") = ");
                    calculator.sqrt();
                    curNum.setText(beautifyNumber(calculator.getState().getCurrentNumber()));
                    break;
                case R.id.btnSqrt:
                    prevExpression.setText("sqr("+beautifyNumber(calculator.getState().getCurrentNumber())+") = ");
                    calculator.root();
                    curNum.setText(beautifyNumber(calculator.getState().getCurrentNumber()));
                    break;
                case R.id.btnInverse:
                    if (Float.parseFloat(calculator.getState().getCurrentNumber()) == 0) return;
                    prevExpression.setText("Inverse("+beautifyNumber(calculator.getState().getCurrentNumber())+") = ");
                    calculator.Inverse();
                    curNum.setText(beautifyNumber(calculator.getState().getCurrentNumber()));
                    break;
                case R.id.btnPS:
                    calculator.sp();
                    updateDisplay();
                    break;
                case R.id.btnPercent:
                    calculator.percent();
                    updateDisplay();
                    break;
            }
            calculator.getState().setScope(SCOPE.BEFORE_COMPUTE);
        }

    }
}