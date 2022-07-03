package org.pen.animusfirst.utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.pen.animusfirst.R;

import java.util.ArrayList;

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder> {


    ArrayList<Features> features;

    public FeaturesAdapter(ArrayList<Features> features) {
        this.features = features;
    }

    @NonNull
    @Override
    public FeaturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.pager_layout, parent, false
        );
        return new FeaturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturesViewHolder holder, int position) {
        Features Bfeatures = features.get(position);

        holder.imvFeatureDraw.setImageResource(Bfeatures.imvFeatureDraw);
        holder.tvFeatureHeading.setText(Bfeatures.tvFeatureHeading);
        holder.tvFeatureDescripton.setText(Bfeatures.tvFeatureDescription);
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    static class FeaturesViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFeatureHeading;
        private final TextView tvFeatureDescripton;
        private final ImageView imvFeatureDraw;

        FeaturesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFeatureDescripton = itemView.findViewById(R.id.tvFeatureDescription);
            tvFeatureHeading = itemView.findViewById(R.id.tvFeatureHeading);
            imvFeatureDraw = itemView.findViewById(R.id.imvFeatureDraw);
        }

    }

}
