package com.example.memomolproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.memomolproject.R;
import com.example.memomolproject.adapters.NavCategoryDetailedAdapter;
import com.example.memomolproject.models.NavCategoryDetailedModel;
import com.example.memomolproject.models.PopularModel;
import com.example.memomolproject.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCategoryDetailedAdapter adapter;

    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progressbar);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.nav_cat_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false)); //ubah
        list = new ArrayList<>();
        adapter = new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        // Getting Dekorasi
        if (type != null && type.equalsIgnoreCase("dekorasi")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","dekorasi").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel viewCategoryDetailedModel = documentSnapshot.toObject((NavCategoryDetailedModel.class));
                        list.add(viewCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

        // Getting Pakaian
        if (type != null && type.equalsIgnoreCase("pakaian")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","pakaian").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel viewCategoryDetailedModel = documentSnapshot.toObject((NavCategoryDetailedModel.class));
                        list.add(viewCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }
}