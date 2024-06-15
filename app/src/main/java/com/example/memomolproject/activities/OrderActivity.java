package com.example.memomolproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomolproject.OrderAccActivity;
import com.example.memomolproject.R;
import com.example.memomolproject.adapters.OrderAdapter;
import com.example.memomolproject.models.MyCartModel;
import com.example.memomolproject.models.OrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OrderActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    List<OrderModel> orderModelList;
    Button order;
    TextView totalFeeTxt, deliveryTxt, taxTxt, totalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.recyclerview);

        orderModelList = new ArrayList<>();
        orderModelList.add(new OrderModel("Baju Barong Etnik", R.drawable.pakaian2, "1", 4.6, 70000));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderAdapter(this, orderModelList));


//        recyclerView.setVisibility(View.GONE);
//        orderModelList = new ArrayList<>();
//        orderAdapter = new OrderAdapter(this, orderModelList);
//        recyclerView.setAdapter(orderAdapter);
//
//        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
//                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
//
//                                String documentId = documentSnapshot.getId();
//
//
//                                OrderModel orderModel = documentSnapshot.toObject(OrderModel.class);
//
//                                orderModel.setDocumentId(documentId);
//
//                                orderModelList.add(orderModel);
//                                orderAdapter.notifyDataSetChanged();
//                                recyclerView.setVisibility(View.VISIBLE);
//                            }
//                        }
//                    }
//                });


        order = findViewById(R.id.order);
        totalFeeTxt = findViewById(R.id.price);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        calculateCart();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, OrderAccActivity.class);
                intent.putExtra("itemList", (Serializable) orderModelList);
                startActivity(intent);
            }
        });
    }

    private void calculateCart() {
        int delivery = 10000;
        int tax = 2000;

        int total = 0;
        int itemTotal = 0;

        if (!orderModelList.isEmpty()) {
            total = Math.round((orderModelList.get(0).getPrice() + delivery + tax));
            itemTotal = Math.round(orderModelList.get(0).getPrice());
        }

        totalFeeTxt.setText("Rp" + itemTotal);
        deliveryTxt.setText("Rp" + delivery);
        taxTxt.setText("Rp" + tax);
        totalTxt.setText("Rp" + total);
    }
}
