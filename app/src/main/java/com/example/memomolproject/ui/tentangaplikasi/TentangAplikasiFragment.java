package com.example.memomolproject.ui.tentangaplikasi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.memomolproject.R;
import com.example.memomolproject.adapters.TentangAplikasiAdapter;
import com.example.memomolproject.models.NavCategoryModel;
import com.example.memomolproject.models.TentangAplikasiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TentangAplikasiFragment extends Fragment {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<TentangAplikasiModel> tentangAplikasiList;
    TentangAplikasiAdapter tentangAplikasiAdapter;
    ProgressBar progressBar;

    public static TentangAplikasiFragment newInstance() {
        return new TentangAplikasiFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tentang_aplikasi,container,false);

        db = FirebaseFirestore.getInstance();

        recyclerView = root.findViewById(R.id.tentang_rec);
        progressBar = root.findViewById(R.id.tentang_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        // Category Nav
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        tentangAplikasiList = new ArrayList<>();
        tentangAplikasiAdapter = new TentangAplikasiAdapter(getActivity(),tentangAplikasiList);
        recyclerView.setAdapter(tentangAplikasiAdapter);

        db.collection("TentangAplikasi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                TentangAplikasiModel navCategoryModel = document.toObject(TentangAplikasiModel.class);
                                tentangAplikasiList.add(navCategoryModel);
                                tentangAplikasiAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            Toast.makeText(getActivity(), "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(TentangAplikasiModel.class);
//        // TODO: Use the ViewModel
//    }

}