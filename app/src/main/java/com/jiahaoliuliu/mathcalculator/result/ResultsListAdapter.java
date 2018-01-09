package com.jiahaoliuliu.mathcalculator.result;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jiahaoliuliu.mathcalculator.data.MathOperationModel;
import com.jiahaoliuliu.mathcalculator.databinding.LayoutResultItemBinding;

import java.util.List;

public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ResultViewHolder>{

    private List<MathOperationModel> mathOperationModelsList;

    public ResultsListAdapter(List<MathOperationModel> mathOperationModelsList) {
        this.mathOperationModelsList = mathOperationModelsList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        LayoutResultItemBinding itemBinding =
                LayoutResultItemBinding.inflate(layoutInflater, parent, false);
        return new ResultViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        MathOperationModel mathOperationModel = mathOperationModelsList.get(position);
        holder.bind(mathOperationModel);
    }

    @Override
    public int getItemCount() {
        return mathOperationModelsList == null? 0: mathOperationModelsList.size();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {

        private LayoutResultItemBinding layoutResultItemBinding;

        public ResultViewHolder(LayoutResultItemBinding layoutResultItemBinding) {
            super(layoutResultItemBinding.getRoot());
            this.layoutResultItemBinding = layoutResultItemBinding;
        }

        public void bind(MathOperationModel mathOperationModel) {
            layoutResultItemBinding.setMathOperationModel(mathOperationModel);
            layoutResultItemBinding.executePendingBindings();
        }
    }
}
