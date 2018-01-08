package com.jiahaoliuliu.mathcalculator;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiahaoliuliu.mathcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Link the button
        binding.startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Start the new activity
                Intent startCalculateActivityIntent =
                        new Intent(MainActivity.this, CalculateActivity.class);
                startActivity(startCalculateActivityIntent);
            }
        });
    }
}
