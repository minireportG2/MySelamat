package com.example.myselamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView sop, hotspot, history, vaccination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sop = (CardView) findViewById(R.id.sop);
        hotspot = (CardView) findViewById(R.id.hotspot);
        history = (CardView) findViewById(R.id.history);
        vaccination = (CardView) findViewById(R.id.vaccination);

        sop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("SOP Module Clicked");
                Intent intent = new Intent(MainActivity.this, SOP_Activity.class);
                startActivity(intent);
            }
        });

        hotspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("Hotspot Module Clicked");
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("Travel History Module Clicked");
            }
        });

        vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("Covid-19 Vaccination Module Clicked");
            }
        });

    }

    private void showToast(String message) {

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}