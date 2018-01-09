package com.jiahaoliuliu.mathcalculator.calculate;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.jiahaoliuliu.mathcalculator.data.MainViewModel;
import com.jiahaoliuliu.mathcalculator.data.MathOperationModel;
import com.jiahaoliuliu.mathcalculator.R;
import com.jiahaoliuliu.mathcalculator.result.ResultActivity;
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

        // Set the model
        mainViewModel = MainViewModel.getInstance();
        currentOperationModel = mainViewModel.getNextMathOperationModel();
        if (currentOperationModel != null) {
            activityCalculateBinding.setMathOperationModel(currentOperationModel);
        } // Else should never happens

        // Set the edit text
        setEditText();
    }

    private void setEditText() {
        // Request focus for the edit text
        if(activityCalculateBinding.givenResult.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        // Update the done button on the soft keyboard
        if (currentOperationModel.isLastOperation()) {
            activityCalculateBinding.givenResult.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }

        // Listen to the done actions
        activityCalculateBinding.givenResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    onNextClicked();
                    return true;
                }

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onFinishClicked();
                    return true;
                }

                return false;
            }
        });
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
        Log.v(TAG, "Finish clicked");
        Intent startResultActivityIntent = new Intent(this, ResultActivity.class);
        startActivity(startResultActivityIntent);
        finish();
    }
}
