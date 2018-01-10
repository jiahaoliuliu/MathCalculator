package com.jiahaoliuliu.mathcalculator.config;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.jiahaoliuliu.mathcalculator.R;
import com.jiahaoliuliu.mathcalculator.data.MainViewModel;
import com.jiahaoliuliu.mathcalculator.databinding.ActivityConfigBinding;

public class ConfigActivity extends AppCompatActivity {

    private static final String TAG = "ConfigActivity";
    private ActivityConfigBinding activityConfigBinding;

    // The current configuration model
    private ConfigurationModel currentConfigurationModel;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfigBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_config);

        // Retrieve the data
        mainViewModel = MainViewModel.getInstance();

        currentConfigurationModel = mainViewModel.getCurrentConfigurationModel();
        activityConfigBinding.setConfigurationModel(currentConfigurationModel);

        // Link the views
        setViews();
    }

    private void setViews() {
        // Set up the action bar
        setSupportActionBar(activityConfigBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the listener for the seekbar
        activityConfigBinding.numberOfExercises.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int numberOfExercises, boolean fromUser) {
                // Set and save the value
                updateNumberOfExercises(numberOfExercises);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });

        activityConfigBinding.maximumAdditionNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int maximumAdditionNumber, boolean b) {
                // Set and save the value
                updateMaximumAdditionNumber(maximumAdditionNumber);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });
    }

    private void updateNumberOfExercises(int numberOfExercises) {
        if (numberOfExercises <= 0) {
            Log.w(TAG, "The number of exercises cannot be lower than 0");
            return;
        }

        // Update the internal data
        currentConfigurationModel.setNumberOfExercises(numberOfExercises);
        mainViewModel.setCurrentConfigurationModel(currentConfigurationModel);

        // Update the view
        activityConfigBinding.setConfigurationModel(currentConfigurationModel);
    }

    private void updateMaximumAdditionNumber(int maximumAdditionNumber) {
        if (maximumAdditionNumber <= 0) {
            Log.w(TAG, "The maximum addition number cannot be lower than 0");
            return;
        }

        // Update the internal data
        currentConfigurationModel.setMaximumAdditionNumber(maximumAdditionNumber);
        mainViewModel.setCurrentConfigurationModel(currentConfigurationModel);

        // Update the view
        activityConfigBinding.setConfigurationModel(currentConfigurationModel);
    }

    // Action bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
