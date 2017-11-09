package ua.nure.havrysh.lab1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;

public class CalcFragment extends BaseFragment {
    enum Operator {
        PLUS((a, b) -> a + b, "+"), MINUS((a, b) -> a - b, "-"), DIV((a, b) -> a / b, "/"), MUL((a, b) -> a * b, "*");

        private final Operation operation;
        public final String name;

        Operator(Operation operation, String name) {
            this.operation = operation;
            this.name = name;
        }

        public float process(float a, float b) {
            return operation.calc(a, b);
        }

        interface Operation {
            float calc(float a, float b);
        }
    }

    Operator currentOperator = Operator.DIV;

    @BindView(R.id.result_text)
    TextView resultTextView;

    @BindView(R.id.operator_text)
    TextView operatorTextView;

    @BindView(R.id.arg1_edit_text)
    EditText arg1EditText;

    @BindView(R.id.arg2_edit_text)
    EditText arg2EditText;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_calc;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        invalidateOperator();
    }

    @OnClick(R.id.plus_btn)
    void onPlusClick(){
        setCurrentOperator(Operator.PLUS);
    }

    @OnClick(R.id.div_btn)
    void onDivClick(){
        setCurrentOperator(Operator.DIV);
    }

    @OnClick(R.id.minus_btn)
    void onMinusClick(){
        setCurrentOperator(Operator.MINUS);
    }

    @OnClick(R.id.mul_btn)
    void onMulClick(){
        setCurrentOperator(Operator.MUL);
    }

    @OnClick(R.id.res_btn)
    void onResClick(){
        try {
            resultTextView.setText("="+currentOperator.process(Float.parseFloat(arg1EditText.getText().toString()),
                    Float.parseFloat(arg2EditText.getText().toString())));
        }catch (NumberFormatException e){
            resultTextView.setText("");
            //ignore
        }
    }

    private void setCurrentOperator(@NonNull Operator operator){
        this.currentOperator = operator;
        invalidateOperator();
    }

    private void invalidateOperator(){
        operatorTextView.setText(currentOperator.name);
    }
}
