package com.jiahaoliuliu.mathcalculator.result;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jiahaoliuliu.mathcalculator.R;
import com.jiahaoliuliu.mathcalculator.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding activityResultBinding;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_result);

        activityResultBinding.resultList.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        activityResultBinding.resultList.setLayoutManager(layoutManager);
    }

}
