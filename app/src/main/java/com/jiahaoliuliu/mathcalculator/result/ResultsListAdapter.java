package com.jiahaoliuliu.mathcalculator.result;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jiahaoliuliu.mathcalculator.data.MathOperationModel;

import java.util.List;

public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ResultViewHolder>{

    private List<MathOperationModel> mathOperationModelsList;

    public ResultsListAdapter(List<MathOperationModel> mathOperationModelsList) {
        this.mathOperationModelsList = mathOperationModelsList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mathOperationModelsList == null? 0: mathOperationModelsList.size();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {

        // TODO: Set the fields
        public ResultViewHolder(View itemView) {
            super(itemView);
        }
    }
}
