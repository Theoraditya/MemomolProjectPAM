package com.example.memomolproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomolproject.adapters.OrderAdapter;
import com.example.memomolproject.models.OrderModel;

import java.util.List;

public class OrderAccActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_acc);

        recyclerView = findViewById(R.id.recyclerviewacc);

        Intent intent = getIntent();
        List<OrderModel> orderModelList = (List<OrderModel>) intent.getSerializableExtra("itemList");

//        List<OrderModel> orderModels = new ArrayList<OrderModel>();
//        orderModels.add(new OrderModel("Baju Barong", R.drawable.pakaian2, "1", 4.6, 75000));
//        orderModels.add(new OrderModel("Perhiasan", R.drawable.perhiasan0, "2", 4.0, 20000));
//
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderAdapter(this, orderModelList));    }
}
