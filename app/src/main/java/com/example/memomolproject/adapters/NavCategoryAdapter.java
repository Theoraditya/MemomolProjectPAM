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
import com.example.memomolproject.activities.DetailedActivity;
import com.example.memomolproject.activities.NavCategoryActivity;
import com.example.memomolproject.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    Context context;
    List<NavCategoryModel> list;

    public NavCategoryAdapter(Context context, List<NavCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NavCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.discount.setText(list.get(position).getDiscount());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, NavCategoryActivity.class);
                    intent.putExtra("type",list.get(adapterPosition).getType());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, description, discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description = itemView.findViewById(R.id.nav_cat_description);
            discount= itemView.findViewById(R.id.nav_cat_discount);
        }
    }
}
