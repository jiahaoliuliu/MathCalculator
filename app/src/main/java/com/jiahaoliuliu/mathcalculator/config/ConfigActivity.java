package com.jiahaoliuliu.mathcalculator.config;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jiahaoliuliu.mathcalculator.R;
import com.jiahaoliuliu.mathcalculator.databinding.ActivityConfigBinding;

public class ConfigActivity extends AppCompatActivity {

    private ActivityConfigBinding activityConfigBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfigBinding = DataBindingUtil.setContentView(this, R.layout.activity_config);
    }

}
