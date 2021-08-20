package com.example.myselamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SOP_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    SopAdapter sopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sop);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SopModel> options =
                new FirebaseRecyclerOptions.Builder<SopModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("states"), SopModel.class)
                .build();

        sopAdapter = new SopAdapter(options);
        recyclerView.setAdapter(sopAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sopAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sopAdapter.stopListening();
    }
}