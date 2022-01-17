package com.example.projecti_20194402;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
        // Add event listener
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
        getView().findViewById(R.id.dotBtn).setOnClickListener(this);
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
        getView().findViewById(R.id.exponentBtn).setOnClickListener(this);
        getView().findViewById(R.id.absBtn).setOnClickListener(this);
        getView().findViewById(R.id.logBtn).setOnClickListener(this);
        getView().findViewById(R.id.ln2Btn).setOnClickListener(this);
        getView().findViewById(R.id.squareBtn).setOnClickListener(this);
        getView().findViewById(R.id.rootBtn).setOnClickListener(this);
        getView().findViewById(R.id.inverseBtn).setOnClickListener(this);
        // Đã xong + - * / đóng mở ngoặc với cả số nguyên và số thực
        getView().findViewById(R.id.absBtn).setOnClickListener(this);
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
            return;
        }
        else if (v.getId() == R.id.deleteBtn) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE || calculator.getScope() == SCOPE.PHEPTOANDON || calculator.getScope() == SCOPE.OPERATOR || calculator.getScope() == SCOPE.NGOAC) return;
            calculator.buttonDel();
            updateDisplay();
            return;
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
            if (calculator.getScope() == SCOPE.NGOAC && calculator.getExpression().charAt(calculator.getExpression().length() - 1) == ')' ) return;
            if (calculator.getCurrentNumber().contains(".") && v.getId() == R.id.dotBtn) return;
            if (calculator.getCurrentNumber().contains(".") == false && v.getId() == R.id.num0 && Float.parseFloat(calculator.getCurrentNumber()) == 0.0f) return;
            if (calculator.getScope() == SCOPE.PHEPTOANDON) return;
            calculator.appendNumber(v.getContentDescription().toString());
            updateDisplay();
            return;
        }
        // Các phép toán 2
        if (   v.getId() == R.id.addBtn
            || v.getId() == R.id.subBtn
            || v.getId() == R.id.mulBtn
            || v.getId() == R.id.divBtn
            || v.getId() == R.id.exponentBtn) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                calculator.setExpression(calculator.getCurrentNumber());
                calculator.setOperator(v.getContentDescription().charAt(0));
                calculator.setCurrentNumber("0");
            } else if (calculator.getScope() == SCOPE.OPERATOR) {
                calculator.setOperator(v.getContentDescription().charAt(0));
            } else if (calculator.getScope() == SCOPE.NUMBER ) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                calculator.setOperator(v.getContentDescription().charAt(0));
                if (v.getId() == R.id.addBtn || v.getId() == R.id.subBtn) {
                    expressionTextView.setText(calculator.getS_Expression() + " " + calculator.getOperator());
                    if (ngoac == 0) {
                        currentNumberTextView.setText(beautifyNumber(String.valueOf(calculator.compute())));
                    }
                    calculator.setScope(SCOPE.OPERATOR);
                    return;
                }
            } else if (calculator.getScope() == SCOPE.NGOAC) {
                if (calculator.getExpression().charAt(calculator.getExpression().length() - 1) == '(') {
                    calculator.setExpression(calculator.getExpression() + calculator.getCurrentNumber());
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getCurrentNumber());
                    calculator.setOperator(v.getContentDescription().charAt(0));
                } else {
                    try {
                        calculator.setCurrentNumber(beautifyNumber(String.valueOf(calculator.cal(calculator.getExpression()))));
                    } catch (Exception e) {
                        calculator.setCurrentNumber(currentNumberTextView.getText().toString());
                    }
                    calculator.setOperator(v.getContentDescription().charAt(0));
                }
            } else if (calculator.getScope() == SCOPE.PHEPTOANDON) {
                calculator.setOperator(v.getContentDescription().charAt(0));
            }
            calculator.setScope(SCOPE.OPERATOR);
            updateDisplay();
            return;
        }
        // Ấn nút =
        if (v.getId() == R.id.computeBtn) {
            while (ngoac > 0) {
                calculator.setExpression(calculator.getExpression() + ")");
                calculator.setS_Expression(calculator.getS_Expression() + ")");
            }
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                calculator.setExpression(beautifyNumber(calculator.getCurrentNumber()));
                calculator.setS_Expression(calculator.getS_Expression());
                expressionTextView.setText(calculator.getS_Expression() + "=");
                calculator.setScope(SCOPE.BEFORE_COMPUTE);
                return;
            } else if (calculator.getScope() == SCOPE.OPERATOR) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                calculator.setOperator(' ');
                expressionTextView.setText(calculator.getS_Expression() + "=");
                calculator.setCurrentNumber(String.valueOf(calculator.cal(calculator.getExpression())));
                currentNumberTextView.setText(beautifyNumber(String.valueOf(calculator.cal(calculator.getExpression()))));
                calculator.setScope(SCOPE.BEFORE_COMPUTE);
                return;

            } else if (calculator.getScope() == SCOPE.NUMBER) {
                calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber()));
                float res = calculator.compute();
                calculator.setOperator(' ');
                calculator.setCurrentNumber(beautifyNumber(String.valueOf(res)));
                expressionTextView.setText(calculator.getS_Expression() + "=");
                calculator.setCurrentNumber(String.valueOf(calculator.cal(calculator.getExpression())));
                currentNumberTextView.setText(beautifyNumber(calculator.getCurrentNumber()));
                calculator.setScope(SCOPE.BEFORE_COMPUTE);

                return;

            } else if (calculator.getScope() == SCOPE.NGOAC) {
                float res = calculator.compute();
                calculator.setOperator(' ');
                calculator.setCurrentNumber(beautifyNumber(String.valueOf(res)));
                expressionTextView.setText(calculator.getS_Expression() + "=");
                currentNumberTextView.setText(beautifyNumber(calculator.getCurrentNumber()));
                calculator.setScope(SCOPE.BEFORE_COMPUTE);

                return;
            } else if (calculator.getScope() == SCOPE.PHEPTOANDON) {
                calculator.setCurrentNumber(beautifyNumber(String.valueOf(calculator.cal(calculator.getExpression()))));
                expressionTextView.setText(calculator.getS_Expression() + " =");
                calculator.setScope(SCOPE.BEFORE_COMPUTE);
                return;
            }
            calculator.setScope(SCOPE.BEFORE_COMPUTE);
            updateDisplay();
            return;
        }
        // Đóng mở ngoặc
        if (v.getId() == R.id.moNgoac || v.getId() == R.id.dongNgoac) {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                String cur = calculator.getCurrentNumber();
                calculator.buttonCclick();
                calculator.setCurrentNumber(cur);
            }
            if (v.getId() == R.id.moNgoac) {
                if (calculator.getScope() == SCOPE.NUMBER && calculator.getExpression().equals("") == false) return;
                if (calculator.getScope() == SCOPE.NGOAC && ngoac == 0) {
                    calculator.buttonCclick();
                }
                if (calculator.getScope() == SCOPE.OPERATOR) {
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator());
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator());
                    calculator.setCurrentNumber("0");
                    calculator.setOperator(' ');
                }
                calculator.setExpression(calculator.getExpression() + "(");
                calculator.setS_Expression(calculator.getS_Expression() + "(" );
                System.out.println(calculator.getExpression());
                ngoac++;
                calculator.setScope(SCOPE.NGOAC);
            }
            if (v.getId() == R.id.dongNgoac) {
                if (ngoac == 0) return;
                if (calculator.getScope() == SCOPE.NGOAC && calculator.getExpression().charAt(calculator.getExpression().length() - 1) == ')') {
                    calculator.setExpression(calculator.getExpression() + ")");
                    calculator.setS_Expression(calculator.getS_Expression() + ")");
                } else {
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber() )+ ")");
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator() + beautifyNumber(calculator.getCurrentNumber() )+ ")");
                }
                calculator.setOperator(' ');
                expressionTextView.setText(calculator.getS_Expression());
                String s = calculator.getExpression();
                System.out.println(s);
                int vitri = calculator.findLastSubExpression(s);
                System.out.println(calculator.getExpression().substring(vitri));
                currentNumberTextView.setText(beautifyNumber(String.valueOf(calculator.cal(calculator.getExpression().substring(vitri)))));
                ngoac--;
                calculator.setScope(SCOPE.NGOAC);
                return;
            }
            updateDisplay();
            return;
        }
        // Các phép toán một ngôi

        /*
            SCOPE = NUMBER => Áp dụng lên current number
            SCOPE = Operator =>  Áp dụng lên current number
            SCOPE = Ngoặc => mở ngoặc: current number
                            Đóng ngoặc: biểu thức ngoặc cuối cùng
         */
        else {
            if (calculator.getScope() == SCOPE.BEFORE_COMPUTE) {
                calculator.setS_Expression("");
                calculator.setExpression("");
            }
            if (calculator.getCurrentNumber().equals("NaN") || calculator.getCurrentNumber().contains("Infinity")) {
                return;
            }
            if (calculator.getScope() == SCOPE.NGOAC) {
                if (calculator.getExpression().charAt(calculator.getExpression().length() - 1) == '(') return;
                else if (calculator.getExpression().charAt(calculator.getExpression().length() - 1) == ')') {
                    System.out.println(calculator.getExpression().substring(calculator.findLastSubExpression(calculator.getExpression())));
                    System.out.println(calculator.getS_Expression().substring(0, calculator.findLastSubExpression(calculator.getS_Expression())) + "ln" + calculator.getS_Expression().substring(calculator.findLastSubExpression(calculator.getS_Expression())));
                }
            }
            switch (v.getId()) {
                case R.id.absBtn:
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator()+ "abs(" + beautifyNumber(calculator.getCurrentNumber()) + ")");
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator()+ calculator.unaryOperationCal(beautifyNumber(calculator.getCurrentNumber()), "abs"));
                    expressionTextView.setText(calculator.getS_Expression());
                    break;
                case R.id.logBtn:
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator()+ "log(" + beautifyNumber(calculator.getCurrentNumber()) + ")");
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator()+ calculator.unaryOperationCal(beautifyNumber(calculator.getCurrentNumber()), "log"));
                    expressionTextView.setText(calculator.getS_Expression());
                    break;
                case R.id.ln2Btn:
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator()+ "ln(" + beautifyNumber(calculator.getCurrentNumber()) + ")");
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator()+ calculator.unaryOperationCal(beautifyNumber(calculator.getCurrentNumber()), "ln"));
                    expressionTextView.setText(calculator.getS_Expression());
                    break;
                case R.id.inverseBtn:
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator()+ "1/(" + beautifyNumber(calculator.getCurrentNumber()) + ")");
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator()+ calculator.unaryOperationCal(beautifyNumber(calculator.getCurrentNumber()), "inverse"));
                    expressionTextView.setText(calculator.getS_Expression());
                    break;
                case R.id.rootBtn:
                    calculator.setS_Expression(calculator.getS_Expression() + calculator.getOperator()+ "sqrt(" + beautifyNumber(calculator.getCurrentNumber()) + ")");
                    calculator.setExpression(calculator.getExpression() + calculator.getOperator()+ calculator.unaryOperationCal(beautifyNumber(calculator.getCurrentNumber()), "sqrt"));
                    expressionTextView.setText(calculator.getS_Expression());
                    break;
            }
            calculator.setCurrentNumber(beautifyNumber(String.valueOf(calculator.unaryOperationCal(calculator.getCurrentNumber(), v.getContentDescription().toString()))));
            currentNumberTextView.setText(calculator.getCurrentNumber());
            calculator.setOperator(' ');
            calculator.setScope(SCOPE.PHEPTOANDON);
        }


    }
    public void updateDisplay() {
        expressionTextView.setText(calculator.getS_Expression() + " " + calculator.getOperator());
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