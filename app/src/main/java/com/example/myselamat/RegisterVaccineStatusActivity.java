package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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


public class RegisterVaccineStatusActivity extends AppCompatActivity {
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String name1, ic1, state1, postcode1, illness1, oku1, illness_a, illness_b, illness_c, illness_d, illness_e, illness_f, illness_total, datefirstdose1, dateseconddose1, psw, firstdosestatus1, seconddosestatus1;
    TextView name, ic, state, postcode, illness, oku, datefirstdose, dateseconddose, firstdosestatus, seconddosestatus;
    Button updatebutton, firstdosecomplete, seconddosecomplete, firstdoseconfirm, seconddoseconfirm, screening;
    EditText firstdosepsw, seconddosepsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vaccine_status);

        name = (TextView) findViewById(R.id.rs_name);
        ic = (TextView) findViewById(R.id.rs_ic);
        state = (TextView) findViewById(R.id.rs_state);
        postcode = (TextView) findViewById(R.id.rs_postcode);
        illness = (TextView) findViewById(R.id.rs_illness);
        oku = (TextView) findViewById(R.id.rs_oku);
        updatebutton = (Button) findViewById(R.id.rs_updatebutton);
        datefirstdose = (TextView) findViewById((R.id.rs_firstdose));
        dateseconddose = (TextView) findViewById(R.id.rs_seconddose);
        firstdosecomplete = (Button) findViewById(R.id.rs_firstdose_completebutton);
        seconddosecomplete = (Button) findViewById(R.id.rs_seconddose_completebutton);
        firstdosepsw = (EditText) findViewById(R.id.rs_firstdose_psw);
        seconddosepsw = (EditText) findViewById(R.id.rs_seconddose_psw);
        firstdoseconfirm = (Button) findViewById(R.id.rs_firstdose_confirmbutton);
        seconddoseconfirm = (Button) findViewById(R.id.rs_seconddose_confirmbutton);
        firstdosestatus = (TextView) findViewById(R.id.rs_firstdose_status);
        seconddosestatus = (TextView) findViewById(R.id.rs_seconddose_status);
        screening = (Button) findViewById(R.id.rs_screening);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Admin");
        reference1.child("psw").setValue("123qwe!@#");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name1 = snapshot.child(currentuser).child("name").getValue(String.class);
                ic1 = snapshot.child(currentuser).child("ic").getValue(String.class);
                state1 = snapshot.child(currentuser).child("state").getValue(String.class);
                postcode1 = snapshot.child(currentuser).child("postcode").getValue(String.class);
                oku1 = snapshot.child(currentuser).child("vaccine").child("OKU").getValue(String.class);
                datefirstdose1 = snapshot.child(currentuser).child("vaccine").child("firstdose").getValue(String.class);
                dateseconddose1 = snapshot.child(currentuser).child("vaccine").child("seconddose").getValue(String.class);
                firstdosestatus1 = snapshot.child(currentuser).child("vaccine").child("firstdosestatus").getValue(String.class);
                seconddosestatus1 = snapshot.child(currentuser).child("vaccine").child("seconddosestatus").getValue(String.class);

                illness1 = snapshot.child(currentuser).child("vaccine").child("illness").getValue(String.class);
                if (illness1.equals("No illness or allergies")) {
                    illness.setText(illness1);
                } else {
                    illness_a = snapshot.child(currentuser).child("vaccine").child("Diabetes").getValue(String.class);
                    illness_b = snapshot.child(currentuser).child("vaccine").child("Hypertension").getValue(String.class);
                    illness_c = snapshot.child(currentuser).child("vaccine").child("Heart disease").getValue(String.class);
                    illness_d = snapshot.child(currentuser).child("vaccine").child("Asthma").getValue(String.class);
                    illness_e = snapshot.child(currentuser).child("vaccine").child("Stroke").getValue(String.class);
                    illness_f = snapshot.child(currentuser).child("vaccine").child("Lung Disease").getValue(String.class);

                    if (!illness_a.equals("No")) {
                        illness_total = illness_a;
                    }
                    if (!illness_b.equals("No")) {
                        illness_total = illness_total + '\n' + illness_b;
                    }
                    if (!illness_c.equals("No")) {
                        illness_total = illness_total + '\n' + illness_c;
                    }
                    if (!illness_d.equals("No")) {
                        illness_total = illness_total + '\n' + illness_d;
                    }
                    if (!illness_e.equals("No")) {
                        illness_total = illness_total + '\n' + illness_e;
                    }
                    if (!illness_f.equals("No")) {
                        illness_total = illness_total + '\n' + illness_f;
                    }
                    illness.setText(illness_total);
                }


                name.setText(name1);
                ic.setText(ic1);
                state.setText(state1);
                postcode.setText(postcode1);
                oku.setText(oku1);
                datefirstdose.setText(datefirstdose1);
                dateseconddose.setText(dateseconddose1);
                firstdosestatus.setText(firstdosestatus1);
                seconddosestatus.setText(seconddosestatus1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegisterVaccineStatusActivity.this, "Fail to retrieve information", Toast.LENGTH_SHORT).show();

            }
        });

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                psw = snapshot.child("psw").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegisterVaccineStatusActivity.this, "Fail to retrieve information", Toast.LENGTH_SHORT).show();

            }
        });

        screening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterVaccineStatusActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterVaccineStatusActivity.this, UpdateVaccineActivity.class);
                startActivity(intent);
            }
        });

        firstdosecomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout linear4 = (LinearLayout) findViewById(R.id.linear4);

                if (linear4.getVisibility() == View.GONE) {
                    linear4.setVisibility(View.VISIBLE);
                } else {
                    linear4.setVisibility(View.GONE);
                }
            }
        });

        seconddosecomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout linear6 = (LinearLayout) findViewById(R.id.linear6);

                if (linear6.getVisibility() == View.GONE) {
                    linear6.setVisibility(View.VISIBLE);
                } else {
                    linear6.setVisibility(View.GONE);
                }
            }
        });

        firstdoseconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linear4 = (LinearLayout) findViewById(R.id.linear4);

                if (psw.equals(firstdosepsw.getText().toString())) {
                    Toast.makeText(RegisterVaccineStatusActivity.this, "First dose completed", Toast.LENGTH_SHORT).show();
                    reference.child(currentuser).child("vaccine").child("firstdosestatus").setValue("Complete");
                    firstdosestatus.setText("Complete");
                    linear4.setVisibility(View.GONE);

                } else {
                    Toast.makeText(RegisterVaccineStatusActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        seconddoseconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout linear6 = (LinearLayout) findViewById(R.id.linear6);

                if (psw.equals(seconddosepsw.getText().toString())) {
                    Toast.makeText(RegisterVaccineStatusActivity.this, "Second dose completed", Toast.LENGTH_SHORT).show();
                    reference.child(currentuser).child("vaccine").child("seconddosestatus").setValue("Complete");
                    seconddosestatus.setText("Complete");
                    linear6.setVisibility(View.GONE);

                } else {
                    Toast.makeText(RegisterVaccineStatusActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}