package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class covidstatusStartActivity extends AppCompatActivity {

    Button cs_startbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidstatus_start);

        cs_startbutton=(Button) findViewById(R.id.cs_startbutton);

        cs_startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(covidstatusStartActivity.this, covidstatusActivity.class);
                startActivity(intent);
            }
        });
    }
}