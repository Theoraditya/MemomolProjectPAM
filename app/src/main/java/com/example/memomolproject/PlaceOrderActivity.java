package com.example.memomolproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memomolproject.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    TextView overTotalAmount;
    double totalAmount;
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        totalAmount = getIntent().getDoubleExtra("totalAmount", 0.0);

        overTotalAmount = findViewById(R.id.overTotalAmount);
        overTotalAmount.setText("Total Amount : $" + totalAmount);

        closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceOrderActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if (list != null && list.size() > 0){
            final int totalTasks = list.size();
            final int[] completedTasks = {0};

            for (MyCartModel model : list){
                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("productName", model.getProductName());
                cartMap.put("productPrice", model.getProductPrice());
                cartMap.put("currentDate", model.getCurrentDate());
                cartMap.put("currentTime", model.getCurrentTime());
                cartMap.put("totalQuantity", model.getTotalQuantity());
                cartMap.put("totalPrice", model.getTotalPrice());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                completedTasks[0]++;

                                if (completedTasks[0] == totalTasks) {
                                    Toast.makeText(PlaceOrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}
