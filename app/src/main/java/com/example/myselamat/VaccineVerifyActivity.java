package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VaccineVerifyActivity extends AppCompatActivity {

    TextView name, ic;
    Button edit, confirm;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_verify);

        name  = (TextView) findViewById(R.id.vaccine_verifyname);
        ic  = (TextView) findViewById(R.id.vaccine_verifyic);
        confirm = (Button) findViewById(R.id.button2);
        edit = (Button) findViewById(R.id.button1);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1 = snapshot.child(currentuser).child("name").getValue(String.class);
                String ic1 = snapshot.child(currentuser).child("ic").getValue(String.class);

                name.setText(name1);
                ic.setText(ic1);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VaccineVerifyActivity.this, "Fail to retrieve information", Toast.LENGTH_SHORT).show();

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VaccineVerifyActivity.this, VaccineQuestionActivity.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VaccineVerifyActivity.this, vaccineEditActivity.class);
                startActivity(intent);
            }
        });








    }


}