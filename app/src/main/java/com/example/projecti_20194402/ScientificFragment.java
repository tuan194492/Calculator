package com.example.projecti_20194402;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Stack;


public class ScientificFragment extends Fragment implements View.OnClickListener{
    private ScientificCalculator calculator;
    private TextView expressionTextView;
    private TextView currentNumberTextView;
    private int ngoac;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculator = new ScientificCalculator();
        ngoac = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scientific, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().findViewById(R.id.eBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello world cai cc");
            }
        });
        getView().findViewById(R.id.trinoBtn).setOnClickListener(this);
        getView().findViewById(R.id.functionBtn).setOnClickListener(this);
        getView().findViewById(R.id.num0).setOnClickListener(this);
        getView().findViewById(R.id.num1).setOnClickListener(this);
        getView().findViewById(R.id.num2).setOnClickListener(this);
        getView().findViewById(R.id.num3).setOnClickListener(this);
        getView().findViewById(R.id.num4).setOnClickListener(this);
        getView().findViewById(R.id.num5).setOnClickListener(this);
        getView().findViewById(R.id.num6).setOnClickListener(this);
        getView().findViewById(R.id.num7).setOnClickListener(this);
        getView().findViewById(R.id.num8).setOnClickListener(this);
        getView().findViewById(R.id.num9).setOnClickListener(this);
        getView().findViewById(R.id.cBtn).setOnClickListener(this);
        getView().findViewById(R.id.eBtn).setOnClickListener(this);
        getView().findViewById(R.id.deleteBtn).setOnClickListener(this);
        getView().findViewById(R.id.computeBtn).setOnClickListener(this);
        getView().findViewById(R.id.addBtn).setOnClickListener(this);
        getView().findViewById(R.id.subBtn).setOnClickListener(this);
        getView().findViewById(R.id.mulBtn).setOnClickListener(this);
        getView().findViewById(R.id.divBtn).setOnClickListener(this);
        getView().findViewById(R.id.dongNgoac).setOnClickListener(this);
        getView().findViewById(R.id.moNgoac).setOnClickListener(this);
        getView().setOnClickListener(this);

        expressionTextView = getView().findViewById(R.id.prevExpres);
        currentNumberTextView = getView().findViewById(R.id.curNum);

        updateDisplay();

    }


    @Override
    public void onClick(View v) {
        View trinoView = getView().findViewById(R.id.trinoView);
        View functionView = getView().findViewById(R.id.functionView);
        if (v.getId() == R.id.trinoBtn) {
            functionView.setVisibility(View.GONE);
            if (trinoView.getVisibility() == View.GONE)
                trinoView.setVisibility(View.VISIBLE);
            else
                trinoView.setVisibility(View.GONE);
            return;
        } else {
            trinoView.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.functionBtn) {
            if (functionView.getVisibility() == View.GONE)
                functionView.setVisibility(View.VISIBLE);
            else
                functionView.setVisibility(View.GONE);
            return;
        } else {
            functionView.setVisibility(View.GONE);
        }
        // Del CE C
        if (v.getId() == R.id.cBtn) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE)
                calculator.buttonCEclick();
            if (calculator.getCurrentNumber().equals("0"))
                calculator.buttonCclick();
            else
                calculator.buttonCEclick();
            updateDisplay();
        }
        else if (v.getId() == R.id.deleteBtn) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) return;
            calculator.buttonDel();
            updateDisplay();
        }
        // NUMBER
        if (    v.getId() == R.id.num0
                || v.getId() == R.id.num1
                || v.getId() == R.id.num2
                || v.getId() == R.id.num3
                || v.getId() == R.id.num4
                || v.getId() == R.id.num5
                || v.getId() == R.id.num6
                || v.getId() == R.id.num7
                || v.getId() == R.id.num8
                || v.getId() == R.id.num9
                || v.getId() == R.id.dotBtn) {
            System.out.println("Press number");
            if (calculator.getCurrentNumber().contains(".") && v.getId() == R.id.dotBtn) return;
            if (calculator.getCurrentNumber().contains(".") == false && v.getId() == R.id.num0 && Float.parseFloat(calculator.getCurrentNumber()) == 0.0f) return;
            calculator.appendNumber(v.getContentDescription().toString());
            updateDisplay();
        }

        if (   v.getId() == R.id.addBtn
            || v.getId() == R.id.subBtn
            || v.getId() == R.id.mulBtn
            || v.getId() == R.id.divBtn) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                calculator.setExpression(calculator.getCurrentNumber());
                calculator.setOperator(v.getContentDescription().charAt(0));
                calculator.setCurrentNumber("0");
            } else if (calculator.getScope() == SCOPE.OPERATOR) {
                calculator.setOperator(v.getContentDescription().charAt(0));
            } else if (calculator.getScope() == SCOPE.NUMBER) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                calculator.setOperator(v.getContentDescription().charAt(0));
                if (v.getId() == R.id.addBtn || v.getId() == R.id.subBtn) {
                    expressionTextView.setText(calculator.getExpression() + " " + calculator.getOperator());
                    if (ngoac == 0) {
                        currentNumberTextView.setText(beautifyNumber(String.valueOf(calculator.compute())));
                    }
                    calculator.setScope(SCOPE.OPERATOR);
                    return;
                }
            } else if (calculator.getScope() == SCOPE.NGOAC) {
                calculator.setCurrentNumber(String.valueOf(calculator.cal(calculator.getExpression().substring(calculator.getExpression().lastIndexOf("(")))));
                calculator.setExpression(calculator.getExpression() + v.getContentDescription());
            }
            calculator.setScope(SCOPE.OPERATOR);
            updateDisplay();
        }

        if (v.getId() == R.id.computeBtn) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {

            } else if (calculator.getScope() == SCOPE.OPERATOR) {

            } else if (calculator.getScope() == SCOPE.NUMBER) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                float res = calculator.compute();
                calculator.setOperator(' ');
                calculator.setCurrentNumber(beautifyNumber(String.valueOf(res)));
                expressionTextView.setText(calculator.getExpression() + "=");
                currentNumberTextView.setText(beautifyNumber(calculator.getCurrentNumber()));
                calculator.setScope(SCOPE.BEFORE_COMPUTE);

                return;

            } else if (calculator.getScope() == SCOPE.NGOAC) {
                while (ngoac > 0) {
                    calculator.setExpression(calculator.getExpression() + ")");
                }
                float res = calculator.compute();
                calculator.setOperator(' ');
                calculator.setCurrentNumber(beautifyNumber(String.valueOf(res)));
                expressionTextView.setText(calculator.getExpression() + "=");
                currentNumberTextView.setText(beautifyNumber(calculator.getCurrentNumber()));
                calculator.setScope(SCOPE.BEFORE_COMPUTE);

                return;
            }
            calculator.setScope(SCOPE.BEFORE_COMPUTE);
            updateDisplay();
        }
        // dong ngoac va mo ngoac
        if (v.getId() == R.id.moNgoac || v.getId() == R.id.dongNgoac) {
            if (v.getId() == R.id.moNgoac) {
                calculator.setExpression(calculator.getExpression() + "(");
                ngoac++;
                calculator.setScope(SCOPE.NGOAC);
            }
            if (v.getId() == R.id.dongNgoac) {
                if (ngoac == 0) return;
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber() )+ ")");
                calculator.setOperator(' ');
                expressionTextView.setText(calculator.getExpression());
                currentNumberTextView.setText(String.valueOf(calculator.cal(calculator.getExpression().substring(calculator.getExpression().lastIndexOf("(")))));
                ngoac--;
                calculator.setScope(SCOPE.NGOAC);
                return;
            }
            updateDisplay();
        }

//        System.out.println("Hello word " + v.getContentDescription());


    }

    public void compute() {
        float res = calculator.compute();
        calculator.setCurrentNumber(beautifyNumber(calculator.getCurrentNumber()));
        currentNumberTextView.setText(calculator.getCurrentNumber());
    }

    public void updateDisplay() {
        expressionTextView.setText(calculator.getExpression() + " " + calculator.getOperator());
        currentNumberTextView.setText(beautifyNumber(calculator.getCurrentNumber()));

    }

    public String beautifyNumber(String s) {
        if (s.equals("") || s.equals(" ")) return s;
        if (s.charAt(s.length() - 1) == '.') return beautifyNumber(s.substring(0, s.length() - 1)) + ".";
        if (Float.parseFloat(s) == 0.0f) {
            return "0";
        }
        if (Float.parseFloat(s) != (int)(Float.parseFloat(s)))
            return String.valueOf(Float.parseFloat(s));
        else return String.valueOf((int) Float.parseFloat(s));
    }
}