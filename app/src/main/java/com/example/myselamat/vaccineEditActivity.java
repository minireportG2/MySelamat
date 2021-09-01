package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class vaccineEditActivity extends AppCompatActivity {

    EditText name, ic;
    Button  confirm;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String name1,ic1;
    String ass;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_edit);


        name  = (EditText) findViewById(R.id.vaccine_editname);
        ic  = (EditText) findViewById(R.id.vaccine_editic);
        confirm = (Button)findViewById(R.id.edit_button_confirm);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
       

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name1 = snapshot.child(currentuser).child("name").getValue(String.class);
                ic1 = snapshot.child(currentuser).child("ic").getValue(String.class);

                name.setText(name1);
                ic.setText(ic1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(vaccineEditActivity.this, "Fail to retrieve information", Toast.LENGTH_SHORT).show();

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNamechanged() || isICchanged()) {
                    Toast.makeText(vaccineEditActivity.this, "Date has been updated", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(vaccineEditActivity.this, VaccineQuestionActivity.class);
                startActivity(intent);


            }
        });



        }

    private boolean isNamechanged() {
        if (!name1.equals(name.getText().toString())) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(currentuser).child("name").setValue(name.getText().toString().trim());
            return true;
        } else {
            return false;
        }
    }

    private boolean isICchanged() {
        if (!ic1.equals(ic.getText().toString())) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(currentuser).child("ic").setValue(ic.getText().toString().trim());
            return true;
        } else {
            return false;
        }

    }

}
