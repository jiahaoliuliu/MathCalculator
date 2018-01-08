package com.jiahaoliuliu.mathcalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiahaoliuliu.mathcalculator.databinding.ActivityCalculateBinding;
import com.jiahaoliuliu.mathcalculator.MathOperationModel.Operation;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCalculateBinding activityCalculateBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_calculate);
        MathOperationModel mathOperationModel =
                new MathOperationModel(1, Operation.ADDITION, 2,
                        3, false);
        activityCalculateBinding.setMathOperation(mathOperationModel);
    }

}
