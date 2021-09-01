package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class covidstatusActivity extends AppCompatActivity {

    RadioGroup cs_que1, cs_que2, cs_que3, cs_que4, cs_que5, cs_que6;
    RadioButton cs_que1_no, cs_que1_yes, cs_que2_no, cs_que2_yes, cs_que3_no, cs_que3_yes, cs_que4_no, cs_que4_yes, cs_que5_no, cs_que5_yes, cs_que6_no, cs_que6_yes;
    Button cs_submit;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    int i = 0;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    Date currentTime = Calendar.getInstance().getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidstatus);

        i=0;


        cs_que1 = (RadioGroup) findViewById(R.id.cs_que1);
        cs_que2 = (RadioGroup) findViewById(R.id.cs_que2);
        cs_que3 = (RadioGroup) findViewById(R.id.cs_que3);
        cs_que4 = (RadioGroup) findViewById(R.id.cs_que4);
        cs_que5 = (RadioGroup) findViewById(R.id.cs_que5);
        cs_que6 = (RadioGroup) findViewById(R.id.cs_que6);

        cs_que1_no = (RadioButton) findViewById(R.id.cs_que1_opt1);
        cs_que1_yes = (RadioButton) findViewById(R.id.cs_que1_opt2);
        cs_que2_no = (RadioButton) findViewById(R.id.cs_que2_opt1);
        cs_que2_yes = (RadioButton) findViewById(R.id.cs_que2_opt2);
        cs_que3_no = (RadioButton) findViewById(R.id.cs_que3_opt1);
        cs_que3_yes = (RadioButton) findViewById(R.id.cs_que3_opt2);
        cs_que4_no = (RadioButton) findViewById(R.id.cs_que4_opt1);
        cs_que4_yes = (RadioButton) findViewById(R.id.cs_que4_opt2);
        cs_que5_no = (RadioButton) findViewById(R.id.cs_que5_opt1);
        cs_que5_yes = (RadioButton) findViewById(R.id.cs_que5_opt2);
        cs_que6_no = (RadioButton) findViewById(R.id.cs_que6_opt1);
        cs_que6_yes = (RadioButton) findViewById(R.id.cs_que6_opt2);

        cs_submit = (Button) findViewById(R.id.cs_submit);

        cs_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();


            }
        });
    }

    private void calculate() {


        if (cs_que1.getCheckedRadioButtonId() == -1) {
            Toast.makeText(covidstatusActivity.this, "Please answer Question 1", Toast.LENGTH_SHORT).show();

        } else if (cs_que2.getCheckedRadioButtonId() == -1) {
            Toast.makeText(covidstatusActivity.this, "Please answer Question 2", Toast.LENGTH_SHORT).show();

        } else if (cs_que3.getCheckedRadioButtonId() == -1) {
            Toast.makeText(covidstatusActivity.this, "Please answer Question 3", Toast.LENGTH_SHORT).show();

        } else if (cs_que4.getCheckedRadioButtonId() == -1) {
            Toast.makeText(covidstatusActivity.this, "Please answer Question 4", Toast.LENGTH_SHORT).show();

        } else if (cs_que5.getCheckedRadioButtonId() == -1) {
            Toast.makeText(covidstatusActivity.this, "Please answer Question 5", Toast.LENGTH_SHORT).show();

        } else if (cs_que6.getCheckedRadioButtonId() == -1) {
            Toast.makeText(covidstatusActivity.this, "Please answer Question 6", Toast.LENGTH_SHORT).show();
        } else {

            if (cs_que1_no.isChecked()) {
                i++;
            }
                if (cs_que2_no.isChecked()) {
                    i++;
                }
                if (cs_que3_no.isChecked()) {
                    i++;
                }
                if (cs_que4_no.isChecked()) {
                    i++;
                }
                if (cs_que5_no.isChecked()) {
                    i++;
                }
                if (cs_que6_no.isChecked()) {
                    i++;
                }
                if (i <= 2) {
                    reference.child(currentuser).child("covidstatus").child("status").setValue("High risk COVID");
                    reference.child(currentuser).child("covidstatus").child("time").setValue(currentTime.toString().trim());
                    Intent intent = new Intent(covidstatusActivity.this, covidstatusResultActivity.class);
                    startActivity(intent);
                } else if (i <= 4) {
                    reference.child(currentuser).child("covidstatus").child("status").setValue("Medium risk COVID");
                    reference.child(currentuser).child("covidstatus").child("time").setValue(currentTime.toString().trim());
                    Intent intent = new Intent(covidstatusActivity.this, covidstatusResultActivity.class);
                    startActivity(intent);
                } else {
                    reference.child(currentuser).child("covidstatus").child("status").setValue("Low risk COVID");
                    reference.child(currentuser).child("covidstatus").child("time").setValue(currentTime.toString().trim());
                    Intent intent = new Intent(covidstatusActivity.this, covidstatusResultActivity.class);
                    startActivity(intent);
                }
            }
        }
    }