package com.example.projecti_20194402;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;
import java.util.function.BinaryOperator;

public class ProgrammerFragment extends Fragment implements View.OnClickListener {
    TextView number, expression;
    String operator;
    ProgrammerCalculator calculator;
    int ngoac;
    TextView hexTextView, binTextView, octTextView, decTextView;
    private enum BASE {
        BIN,
        OCT,
        DEC,
        HEX
    }
    BASE base;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculator = new ProgrammerCalculator();
        base = BASE.DEC;
        ngoac = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programmer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hexTextView = (TextView) getActivity().findViewById(R.id.hexTextView);
        octTextView = (TextView) getActivity().findViewById(R.id.octTextView);
        decTextView = (TextView) getActivity().findViewById(R.id.decTextView);
        binTextView = (TextView) getActivity().findViewById(R.id.binTextView);

        getActivity().findViewById(R.id.bitwiseLayout).setVisibility(View.GONE);
        number = getActivity().findViewById(R.id.programmerNumber);
        expression = getActivity().findViewById(R.id.programmerExpress);
//        number.setText("0");
//        expression.setText("");
        //BASE
        getActivity().findViewById(R.id.programmerHEX).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerDEC).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerOCT).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerBIN).setOnClickListener(this);
        //Number
        getActivity().findViewById(R.id.programmerNum0_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum1_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum2_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum3_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum4_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum5_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum6_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum7_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum8_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNum9_btn).setOnClickListener(this);
//        getActivity().findViewById(R.id.programmerDot_btn).setOnClickListener(this);
        //Operator
        getActivity().findViewById(R.id.programmerAdd_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerSubtract_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerMul_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerDiv_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerPercent_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerShiftLeft_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerShiftRight_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerAnd_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNand_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerOr_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerNor_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerXor_btn).setOnClickListener(this);
        // Compute
        getActivity().findViewById(R.id.programmerCompute_btn).setOnClickListener(this);
        //dong mo ngoac
        getActivity().findViewById(R.id.programmerMoNgoac_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerDongNgoac_btn).setOnClickListener(this);
        // Delete and clear
        getActivity().findViewById(R.id.programmerDelete_btn).setOnClickListener(this);
        getActivity().findViewById(R.id.programmerClear_btn).setOnClickListener(this);
        // Bitwise
        getActivity().findViewById(R.id.programmerBitwise_btn).setOnClickListener(this);
        //
        updateDisplay();
    }

    @Override
    public void onClick(View v) {
        if (calculator.getCurNum().equals("NaN") || calculator.getCurNum().contains("Infinity")) {
            calculator.setScope(SCOPE.BEFORE_COMPUTE);
        }
        View layout = getActivity().findViewById(R.id.bitwiseLayout);
        if (v.getId() == R.id.programmerBitwise_btn) {

            if (layout.getVisibility() == View.VISIBLE) {
                layout.setVisibility(View.GONE);
            } else {
                layout.setVisibility(View.VISIBLE);
            }
        } else {
            layout.setVisibility(View.GONE);
        }
        //BASE
        if (   v.getId() == R.id.programmerHEX
           ||  v.getId() == R.id.programmerOCT
           ||  v.getId() == R.id.programmerBIN
           ||  v.getId() == R.id.programmerDEC
        ) {
            if (v.getId() == R.id.programmerHEX) base = BASE.HEX;
            else if (v.getId() == R.id.programmerBIN) base = BASE.BIN;
            else if (v.getId() == R.id.programmerDEC) base = BASE.DEC;
            else if (v.getId() == R.id.programmerOCT) base = BASE.OCT;
            getActivity().findViewById(R.id.programmerHEX).setBackgroundColor(0);
            getActivity().findViewById(R.id.programmerDEC).setBackgroundColor(0);
            getActivity().findViewById(R.id.programmerOCT).setBackgroundColor(0);
            getActivity().findViewById(R.id.programmerBIN).setBackgroundColor(0);
            getActivity().findViewById(v.getId()).setBackgroundColor(Color.parseColor("#C66F6F"));
            return;
        }
        //Delete and clear
        if (v.getId() == R.id.programmerDelete_btn) {
            calculator.buttonDel();
        }
        if (v.getId() == R.id.programmerClear_btn) {
            calculator.reset();
        }
        // Number
        if (   v.getId() == R.id.programmerNum0_btn
            || v.getId() == R.id.programmerNum1_btn
            || v.getId() == R.id.programmerNum2_btn
            || v.getId() == R.id.programmerNum3_btn
            || v.getId() == R.id.programmerNum4_btn
            || v.getId() == R.id.programmerNum5_btn
            || v.getId() == R.id.programmerNum6_btn
            || v.getId() == R.id.programmerNum7_btn
            || v.getId() == R.id.programmerNum8_btn
            || v.getId() == R.id.programmerNum9_btn
        ) {
            if (calculator.getScope() == SCOPE.OPERATOR) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator());
            } else if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                calculator.reset();
            } else if (calculator.getScope() == SCOPE.NGOAC && calculator.getExpression().charAt(calculator.getExpression().length() - 1) == ')') return;
            calculator.appendNumber(v.getContentDescription().toString());
            calculator.setOperator(" ");
            calculator.setScope(SCOPE.NUMBER);
        }
        // Operator
        if (    v.getId() == R.id.programmerAdd_btn
            ||  v.getId() == R.id.programmerSubtract_btn
            ||  v.getId() == R.id.programmerMul_btn
            ||  v.getId() == R.id.programmerDiv_btn
            ||  v.getId() == R.id.programmerPercent_btn
            ||  v.getId() == R.id.programmerShiftLeft_btn
            ||  v.getId() == R.id.programmerShiftRight_btn
            ||  v.getId() == R.id.programmerAnd_btn
            ||  v.getId() == R.id.programmerOr_btn
            ||  v.getId() == R.id.programmerNor_btn
            ||  v.getId() == R.id.programmerXor_btn
            ||  v.getId() == R.id.programmerNand_btn
        ) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                calculator.setCurNum(beautifyNumber(number.getText().toString()));
                calculator.setExpression(calculator.getCurNum());
            } else if (calculator.getScope() == SCOPE.NUMBER) {
                if (calculator.getOperator() != " ")
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurNum()));
                else
                    calculator.setExpression(calculator.getExpression() + beautifyNumber(calculator.getCurNum()));
            } else if (calculator.getScope() == SCOPE.NGOAC && calculator.getExpression().charAt(calculator.getExpression().length() - 1) == '(') {
                return;
            }
            calculator.setOperator(v.getContentDescription().toString());
            calculator.setCurNum("0");
            calculator.setScope(SCOPE.OPERATOR);
            expression.setText(calculator.getExpression() + calculator.getOperator());
            if ((calculator.getOperator() == "+" || calculator.getOperator() == "-") && ngoac == 0)
                number.setText(beautifyNumber(String.valueOf(calculator.cal(calculator.getExpression()))));
            else
                number.setText(beautifyNumber(calculator.getCurNum()));
            updateBaseDisplay();
            return;
        }
        // Compute
        if (v.getId() == R.id.programmerCompute_btn) {
            if (ngoac > 0) {
                if (calculator.getExpression().charAt(calculator.getExpression().length() - 1) != ')') {
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurNum()));
                }
                while (ngoac > 0) {
                    calculator.setExpression(calculator.getExpression() + ")");
                    ngoac--;
                }
                calculator.setScope(SCOPE.NGOAC);
                calculator.setCurNum("0");
                calculator.setOperator(" ");
            }
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                return;
            } else if (calculator.getScope() == SCOPE.NUMBER ) {
                calculator.setExpression(calculator.getExpression() + beautifyNumber(calculator.getCurNum()));
            } else if (calculator.getScope() == SCOPE.OPERATOR) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurNum()));
            }
            calculator.setScope(SCOPE.BEFORE_COMPUTE);
            expression.setText(calculator.getExpression() + "=");
            number.setText(beautifyNumber(String.valueOf(calculator.cal(calculator.getExpression()))));
            updateBaseDisplay();
            return;
        }
        if (v.getId() == R.id.programmerMoNgoac_btn || v.getId() == R.id.programmerDongNgoac_btn) {
            if (v.getId() == R.id.programmerMoNgoac_btn) {
                if (calculator.getScope() == SCOPE.NUMBER) return;
                else if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                    calculator.reset();
                } else if (calculator.getScope() == SCOPE.NGOAC && calculator.getExpression().charAt(calculator.getExpression().length() - 1) == ')') return;
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + "(");
                calculator.setCurNum("0");
                calculator.setOperator(" ");
                calculator.setScope(SCOPE.NGOAC);
                ngoac++;
            } else {
                if (ngoac <= 0) return;
                if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) return;
                else if (calculator.getScope() == SCOPE.OPERATOR) {
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurNum()));
                } else {
                    calculator.setExpression(calculator.getExpression() + beautifyNumber(calculator.getCurNum()));
                }
                calculator.setExpression(calculator.getExpression() + ")");
                calculator.setOperator(" ");
                calculator.setCurNum("0");
                calculator.setScope(SCOPE.NGOAC);
                ngoac--;
                expression.setText(calculator.getExpression());
                number.setText(beautifyNumber(String.valueOf(calculator.cal(
                        calculator.getExpression().substring(calculator.findLastSubExpression(calculator.getExpression())
                )))));
                updateBaseDisplay();
                return;
            }
        }
        updateDisplay();
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
    public void updateBaseDisplay() {
        int num = Integer.parseInt(number.getText().toString());
        binTextView.setText(Integer.toBinaryString(num));
        octTextView.setText(Integer.toOctalString(num));
        decTextView.setText(number.getText().toString());
        hexTextView.setText(Integer.toHexString(num).toUpperCase(Locale.ROOT));
    }
    public void updateDisplay() {
        number.setText(beautifyNumber(calculator.getCurNum()));
        expression.setText(calculator.getExpression() + calculator.getOperator());
        updateBaseDisplay();

    }

}