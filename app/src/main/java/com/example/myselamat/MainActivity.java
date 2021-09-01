package com.example.myselamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    CardView sop, nearby, history, vaccination,news;
    Button sign_out;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sop = (CardView) findViewById(R.id.sop);
        nearby = (CardView) findViewById(R.id.nearby);
        history = (CardView) findViewById(R.id.history);
        vaccination = (CardView) findViewById(R.id.vaccination);
        news=(CardView)findViewById(R.id.news);
        sign_out = (Button) findViewById(R.id.sign_out);

        sop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("SOP Module Clicked");
                Intent intent = new Intent(MainActivity.this, SOP_Activity.class);
                startActivity(intent);
            }
        });

        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("Nearby Facility Module Clicked");
                Intent intent = new Intent(MainActivity.this, NearbyActivity.class);
                startActivity(intent);
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

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("News Module Clicked");
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Sign Out Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void showToast(String message) {

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}