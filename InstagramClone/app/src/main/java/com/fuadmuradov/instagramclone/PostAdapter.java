package com.fuadmuradov.instagramclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuadmuradov.instagramclone.databinding.RecycleRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {



    private ArrayList<Post> postArrayList;
    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }



    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycleRowBinding recycleRowBinding = RecycleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostHolder(recycleRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
    holder.recycleRowBinding.RecycleViewUserEmailText.setText(postArrayList.get(position).email);
    holder.recycleRowBinding.RecycleCommentText.setText(postArrayList.get(position).comment);
        Picasso.get().load(postArrayList.get(position).downloadurl).into(holder.recycleRowBinding.RecycleImageView);
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

     class PostHolder extends RecyclerView.ViewHolder{
        RecycleRowBinding recycleRowBinding;
        public PostHolder(RecycleRowBinding recycleRowBinding) {
            super(recycleRowBinding.getRoot());
            this.recycleRowBinding = recycleRowBinding;
        }
    }
}
