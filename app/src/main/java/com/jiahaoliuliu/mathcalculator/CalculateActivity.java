package com.jiahaoliuliu.mathcalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiahaoliuliu.mathcalculator.databinding.ActivityCalculateBinding;

public class CalculateActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCalculateBinding activityCalculateBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_calculate);

        mainViewModel = MainViewModel.getInstance();
        activityCalculateBinding.setMathOperation(mainViewModel.getNextMathOperationModel());
    }

}
