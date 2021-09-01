package com.example.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    CardView sop, nearby, history, vaccination,news,riskstatus;
    Button sign_out;
    FirebaseAuth mAuth;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    String registeredVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sop = (CardView) findViewById(R.id.sop);
        nearby = (CardView) findViewById(R.id.nearby);
        history = (CardView) findViewById(R.id.history);
        vaccination = (CardView) findViewById(R.id.vaccination);
        news=(CardView)findViewById(R.id.news);
        riskstatus=(CardView)findViewById(R.id.riskstatus);
        sign_out = (Button) findViewById(R.id.sign_out);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                registeredVaccine = snapshot.child(currentuser).child("vaccine").child("registered").getValue(String.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Fail to retrieve information", Toast.LENGTH_SHORT).show();

            }
        });

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

        riskstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("Covid-19 Status Clicked");
                Intent intent = new Intent(MainActivity.this, covidstatusStartActivity.class);
                startActivity(intent);
            }
        });

        vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Covid-19 Vaccination Module Clicked");
                if (registeredVaccine.equals("Yes")){
                    Intent intent = new Intent(MainActivity.this, RegisterVaccineStatusActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, VaccineVerifyActivity.class);
                    startActivity(intent);
                }
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