package com.jiahaoliuliu.mathcalculator.welcome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiahaoliuliu.mathcalculator.data.MainViewModel;
import com.jiahaoliuliu.mathcalculator.R;
import com.jiahaoliuliu.mathcalculator.calculate.CalculateActivity;
import com.jiahaoliuliu.mathcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int LIST_SIZE = 10;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Get the instance of the main view model
        mainViewModel = MainViewModel.getInstance();

        // Link the button
        binding.startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Generate the model
                mainViewModel.generateNewMathOperationModelsList(LIST_SIZE);

                // Start the new activity
                Intent startCalculateActivityIntent =
                        new Intent(MainActivity.this, CalculateActivity.class);
                startActivity(startCalculateActivityIntent);
            }
        });
    }
}
