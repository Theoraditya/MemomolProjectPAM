package com.example.memomolproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.memomolproject.R;
import com.example.memomolproject.activities.ViewAllActivity;
import com.example.memomolproject.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, description, rating, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            discount = itemView.findViewById(R.id.pop_discount);
            rating = itemView.findViewById(R.id.pop_rating);
        }
    }

    @NonNull
    @Override
    public PopularAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapters.ViewHolder holder, int position) {
        PopularModel popularModel = popularModelList.get(position);

        Glide.with(context).load(popularModel.getImg_url()).into(holder.popImg);
        holder.name.setText(popularModel.getName());
        holder.rating.setText(popularModel.getRating());
        holder.description.setText(popularModel.getDescription());
        holder.discount.setText(popularModel.getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    PopularModel currentModel = popularModelList.get(currentPosition);
                    Intent intent = new Intent(context, ViewAllActivity.class);
                    intent.putExtra("type", currentModel.getType());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }
}
