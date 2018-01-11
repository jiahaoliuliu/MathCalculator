package com.jiahaoliuliu.mathcalculator.result;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiahaoliuliu.mathcalculator.calculate.TotalTimer;
import com.jiahaoliuliu.mathcalculator.data.GeneralResultModel;
import com.jiahaoliuliu.mathcalculator.data.MathOperationModel;
import com.jiahaoliuliu.mathcalculator.databinding.LayoutResultFooterBinding;
import com.jiahaoliuliu.mathcalculator.databinding.LayoutResultHeaderBinding;
import com.jiahaoliuliu.mathcalculator.databinding.LayoutResultItemBinding;

import java.util.List;

public class ResultsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private Activity callerActivity;
    private List<MathOperationModel> mathOperationModelsList;
    private GeneralResultModel generalResultModel;
    private int exerciseTime;

    public ResultsListAdapter(Activity callerActivity, List<MathOperationModel> mathOperationModelsList,
                              GeneralResultModel generalResultModel, int exerciseTime) {
        this.callerActivity = callerActivity;
        this.mathOperationModelsList = mathOperationModelsList;
        this.generalResultModel = generalResultModel;
        this.exerciseTime = exerciseTime;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_HEADER) {
            LayoutResultHeaderBinding layoutResultHeaderBinding =
                    LayoutResultHeaderBinding.inflate(layoutInflater, parent, false);
            return new HeaderViewHolder(layoutResultHeaderBinding);
        } if (viewType == TYPE_FOOTER) {
            LayoutResultFooterBinding layoutResultFooterBinding =
                    LayoutResultFooterBinding.inflate(layoutInflater, parent, false);
            return new FooterViewHolder(layoutResultFooterBinding, callerActivity);
        } else { // If it is any item
            LayoutResultItemBinding layoutResultItemBinding =
                    LayoutResultItemBinding.inflate(layoutInflater, parent, false);
            return new ResultViewHolder(layoutResultItemBinding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeaderViewHolder) {
            // Exercise time
            TotalTimer totalTimer = new TotalTimer(exerciseTime / 60, exerciseTime % 60);

            ((HeaderViewHolder)viewHolder).bind(generalResultModel, totalTimer);
        } else if (viewHolder instanceof ResultViewHolder){ // If it is any item
            MathOperationModel mathOperationModel = mathOperationModelsList.get(position - 1);
            ((ResultViewHolder)viewHolder).bind(mathOperationModel);
        } else {
            // Do nothing. This should't happen
        }
    }

    @Override
    public int getItemCount() {
        return mathOperationModelsList == null? 2: mathOperationModelsList.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return  position == mathOperationModelsList.size() + 1;
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

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private LayoutResultHeaderBinding layoutResultHeaderBinding;

        public HeaderViewHolder(LayoutResultHeaderBinding layoutResultHeaderBinding) {
            super(layoutResultHeaderBinding.getRoot());
            this.layoutResultHeaderBinding = layoutResultHeaderBinding;
        }

        public void bind(GeneralResultModel generalResultModel, TotalTimer totalTimer) {
            layoutResultHeaderBinding.setGeneralResultModel(generalResultModel);
            layoutResultHeaderBinding.setTotalTimer(totalTimer);
            layoutResultHeaderBinding.executePendingBindings();
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(LayoutResultFooterBinding layoutResultFooterBinding, final Activity callerActivity) {
            super(layoutResultFooterBinding.getRoot());

            layoutResultFooterBinding.getRoot().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (callerActivity != null) {
                        callerActivity.finish();
                    }
                }
            });
        }
    }
}
