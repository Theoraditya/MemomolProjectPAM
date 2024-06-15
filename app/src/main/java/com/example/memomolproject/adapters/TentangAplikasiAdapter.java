package com.example.memomolproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomolproject.R;
import com.example.memomolproject.models.NavCategoryModel;
import com.example.memomolproject.models.TentangAplikasiModel;

import java.util.List;

public class TentangAplikasiAdapter extends RecyclerView.Adapter<TentangAplikasiAdapter.ViewHolder> {


    Context context;
    List<TentangAplikasiModel> list;

    public TentangAplikasiAdapter(Context context, List<TentangAplikasiModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TentangAplikasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TentangAplikasiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tentang_aplikasi_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TentangAplikasiAdapter.ViewHolder holder, int position) {

        holder.judul.setText(list.get(position).getJudul());
        holder.deskripsi.setText(list.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul,deskripsi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tentang_judul);
            deskripsi = itemView.findViewById(R.id.tentang_deskripsi);
        }
    }
}
