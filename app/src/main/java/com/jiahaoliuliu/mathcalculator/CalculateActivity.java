package com.jiahaoliuliu.mathcalculator;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.jiahaoliuliu.mathcalculator.databinding.ActivityCalculateBinding;

public class CalculateActivity extends AppCompatActivity implements CalculationClickListener {

    private static final String TAG = "CalculateActivity";

    private ActivityCalculateBinding activityCalculateBinding;
    private MainViewModel mainViewModel;
    private MathOperationModel currentOperationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCalculateBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_calculate);
        activityCalculateBinding.setCalculationClickListener(this);

        mainViewModel = MainViewModel.getInstance();
        currentOperationModel = mainViewModel.getNextMathOperationModel();
        if (currentOperationModel != null) {
            activityCalculateBinding.setMathOperationModel(currentOperationModel);
        } // Else should never happens
    }

    @Override
    public void onNextClicked() {
        // Get the result
        String givenResultString = activityCalculateBinding.givenResult.getText().toString();
        if (TextUtils.isEmpty(givenResultString)) {
            Log.w(TAG, "The input given result is empty");
            Toast.makeText(this, R.string.error_given_result_empty, Toast.LENGTH_LONG).show();
            return;
        }
        currentOperationModel.setGivenResult(
                Integer.parseInt(givenResultString));
        // Show next result
        Intent showNextCalculateActivityIntent = new Intent(this, CalculateActivity.class);
        startActivity(showNextCalculateActivityIntent);
        finish();
    }

    @Override
    public void onFinishClicked() {
        // TODO: Finish the current activity
        int operation = 1;
    }
}
