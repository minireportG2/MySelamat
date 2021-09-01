package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class covidstatusResultActivity extends AppCompatActivity {

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    ImageView cs_result;
    TextView cs_resulttext;
    Button cs_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidstatus_result);

        cs_result = (ImageView)findViewById(R.id.cs_result);
        cs_resulttext = (TextView)findViewById(R.id.cs_resulttext) ;
        cs_confirm =(Button)findViewById(R.id.cs_button_confirm);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String covidstatus;
                covidstatus = snapshot.child(currentuser).child("covidstatus").child("status").getValue(String.class);

                if(covidstatus.equals("High risk COVID")){
                    cs_result.setImageResource(R.drawable.redwarning);
                    cs_resulttext.setText(covidstatus);
                }
                if(covidstatus.equals("Medium risk COVID")){
                    cs_result.setImageResource(R.drawable.orangewarning);
                    cs_resulttext.setText(covidstatus);
                }
                if(covidstatus.equals("Low risk COVID")){
                    cs_result.setImageResource(R.drawable.greenok);
                    cs_resulttext.setText(covidstatus);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(covidstatusResultActivity.this, "Fail to retrieve information", Toast.LENGTH_SHORT).show();
            }
        });

        cs_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(covidstatusResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}