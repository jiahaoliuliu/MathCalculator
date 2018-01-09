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
import com.jiahaoliuliu.mathcalculator.data.MainViewModel;
import com.jiahaoliuliu.mathcalculator.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    // View
    private ActivityResultBinding activityResultBinding;
    private ResultsListAdapter resultsListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Data
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_result);

        mainViewModel = MainViewModel.getInstance();

        // Set the layout manager
        activityResultBinding.resultList.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        activityResultBinding.resultList.setLayoutManager(layoutManager);

        // Set the adapter
        resultsListAdapter = new ResultsListAdapter(mainViewModel.getResultsList());
        activityResultBinding.resultList.setAdapter(resultsListAdapter);
    }

}
