package com.fuadmuradov.landmarkbook;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuadmuradov.landmarkbook.databinding.RecycleRowBinding;

import java.util.ArrayList;

public class LandMarkAdapter extends RecyclerView.Adapter<LandMarkAdapter.LandMarkHolder> {
    public LandMarkAdapter(ArrayList<LandMark> landMarkArrayList) {
        this.LandMarkArrayList = landMarkArrayList;
    }

    ArrayList<LandMark> LandMarkArrayList;

    @NonNull
    @Override
    public LandMarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycleRowBinding recycleRowBinding = RecycleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LandMarkHolder(recycleRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LandMarkHolder holder, int position) {
    holder.binding.recycleViewTextView.setText(LandMarkArrayList.get(position).monument);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(holder.itemView.getContext(), DetailsActivity.class);
            intent.putExtra("landmark", LandMarkArrayList.get(position));
            holder.itemView.getContext().startActivity(intent);
        }
    });



    }

    @Override
    public int getItemCount() {
        return LandMarkArrayList.size();
    }

    public class LandMarkHolder extends RecyclerView.ViewHolder{
        private RecycleRowBinding binding;
        public LandMarkHolder(RecycleRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
