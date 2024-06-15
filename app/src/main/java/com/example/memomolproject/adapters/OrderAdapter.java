package com.example.memomolproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.memomolproject.R;
import com.example.memomolproject.models.OrderModel;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<OrderModel> orderModelList;

    public OrderAdapter(Context context, List<OrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(orderModelList.get(position).getPicUrl())
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic);
        holder.title.setText(orderModelList.get(position).getTitle());
        holder.total.setText(String.valueOf(orderModelList.get(position).getPrice()));
        holder.rating.setText(String.valueOf(orderModelList.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, total, rating;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            total = itemView.findViewById(R.id.total);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
