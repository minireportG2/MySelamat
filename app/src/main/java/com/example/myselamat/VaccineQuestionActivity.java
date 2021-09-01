package com.example.myselamat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VaccineQuestionActivity extends AppCompatActivity {


    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    String date;
    SimpleDateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();

    RadioGroup vaccine_que1,vaccine_que2,vaccine_que3,vaccine_que4;
    RadioButton vaccine_que1_opt1, vaccine_que1_opt2,vaccine_que2_opt1, vaccine_que2_opt2,vaccine_que3_opt2, vaccine_que3_opt1,vaccine_que4_johor,vaccine_que4_kedah,vaccine_que4_kelantan,vaccine_que4_melaka,vaccine_que4_negerisembilan,vaccine_que4_pahang,vaccine_que4_perak,vaccine_que4_perlis,vaccine_que4_pulaupinang,vaccine_que4_sarawak,vaccine_que4_sabah,vaccine_que4_selangor,vaccine_que4_terengganu,vaccine_que4_putrajaya,vaccine_que4_kl,vaccine_que4_labuan;
    CheckBox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6;
    EditText vaccine_que5;
    Button vaccine_submitbutton;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_question_activity);

        vaccine_que1 = (RadioGroup)findViewById(R.id.vaccine_que1);
        vaccine_que2 = (RadioGroup)findViewById(R.id.vaccine_que2);
        vaccine_que3 = (RadioGroup)findViewById(R.id.vaccine_que3);
        vaccine_que4 = (RadioGroup)findViewById(R.id.vaccine_que4);

        vaccine_que4_johor = (RadioButton)findViewById(R.id.vaccine_que4_johor);
        vaccine_que4_kedah = (RadioButton)findViewById(R.id.vaccine_que4_kedah);
        vaccine_que4_kelantan = (RadioButton)findViewById(R.id.vaccine_que4_kelantan);
        vaccine_que4_melaka = (RadioButton)findViewById(R.id.vaccine_que4_melaka);
        vaccine_que4_negerisembilan = (RadioButton)findViewById(R.id.vaccine_que4_negeriSembilan);
        vaccine_que4_pahang = (RadioButton)findViewById(R.id.vaccine_que4_pahang);
        vaccine_que4_perak = (RadioButton)findViewById(R.id.vaccine_que4_perak);
        vaccine_que4_perlis = (RadioButton)findViewById(R.id.vaccine_que4_perlis);
        vaccine_que4_pulaupinang = (RadioButton)findViewById(R.id.vaccine_que4_pulaupinang);
        vaccine_que4_sarawak = (RadioButton)findViewById(R.id.vaccine_que4_sarawak);
        vaccine_que4_sabah = (RadioButton)findViewById(R.id.vaccine_que4_sabah);
        vaccine_que4_selangor = (RadioButton)findViewById(R.id.vaccine_que4_selangor);
        vaccine_que4_terengganu = (RadioButton)findViewById(R.id.vaccine_que4_terengganu);
        vaccine_que4_putrajaya = (RadioButton)findViewById(R.id.vaccine_que4_putrajaya);
        vaccine_que4_kl = (RadioButton)findViewById(R.id.vaccine_que4_kl);
        vaccine_que4_labuan = (RadioButton)findViewById(R.id.vaccine_que4_labuan);

        vaccine_que1_opt1 = (RadioButton) findViewById(R.id.vaccine_que1_opt1);
        vaccine_que1_opt2 = (RadioButton) findViewById(R.id.vaccine_que1_opt2);
        vaccine_que2_opt1 = (RadioButton) findViewById(R.id.vaccine_que2_opt1);
        vaccine_que2_opt2 = (RadioButton) findViewById(R.id.vaccine_que2_opt2);
        vaccine_que3_opt1 = (RadioButton) findViewById(R.id.vaccine_que3_opt1);
        vaccine_que3_opt2 = (RadioButton) findViewById(R.id.vaccine_que3_opt2);

        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkbox6 = (CheckBox) findViewById(R.id.checkbox6);

        vaccine_que5 = (EditText) findViewById(R.id.vaccine_que5);
        vaccine_submitbutton= (Button)findViewById(R.id.vaccine_submitbutton);

        vaccine_submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });


        vaccine_que3_opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout linear4 = (LinearLayout) findViewById(R.id.linear4);

                    linear4.setVisibility(View.VISIBLE);
            }

        });

        vaccine_que3_opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout linear4 = (LinearLayout) findViewById(R.id.linear4);

                if (linear4.getVisibility() == View.VISIBLE)
                    linear4.setVisibility(View.GONE);

                checkbox1.setChecked(false);
                checkbox2.setChecked(false);
                checkbox3.setChecked(false);
                checkbox4.setChecked(false);
                checkbox5.setChecked(false);
                checkbox6.setChecked(false);
            }

        });
    }

    private void calculate() {

        if (vaccine_que1.getCheckedRadioButtonId() == -1) {
            Toast.makeText(VaccineQuestionActivity.this, "Please answer Question 1", Toast.LENGTH_SHORT).show();

        } else if (vaccine_que2.getCheckedRadioButtonId() == -1) {
            Toast.makeText(VaccineQuestionActivity.this, "Please answer Question 2", Toast.LENGTH_SHORT).show();

        } else if (vaccine_que3.getCheckedRadioButtonId() == -1) {
            Toast.makeText(VaccineQuestionActivity.this, "Please answer Question 3", Toast.LENGTH_SHORT).show();

        } else if (vaccine_que4.getCheckedRadioButtonId() == -1) {
            Toast.makeText(VaccineQuestionActivity.this, "Please answer Question 4", Toast.LENGTH_SHORT).show();

        } else if ( vaccine_que5.getText().toString().equals("") ) {
            Toast.makeText(VaccineQuestionActivity.this, "Please answer Question 5", Toast.LENGTH_SHORT).show();
        }else{

            if (vaccine_que2_opt1.isChecked()) {
                reference.child(currentuser).child("vaccine").child("OKU").setValue("Not OKU");
            }else{
                reference.child(currentuser).child("vaccine").child("OKU").setValue("OKU");
            }

            if (vaccine_que3_opt1.isChecked()) {
                reference.child(currentuser).child("vaccine").child("illness").setValue("No illness or allergies");
            }else {

                reference.child(currentuser).child("vaccine").child("illness").setValue("Yes");

                if (checkbox1.isChecked()) {
                    reference.child(currentuser).child("vaccine").child("Diabetes").setValue("Diabetes Mellitus");
                } else {
                    reference.child(currentuser).child("vaccine").child("Diabetes").setValue("No");
                }
                if (checkbox2.isChecked()) {
                    reference.child(currentuser).child("vaccine").child("Hypertension").setValue("Hypertension");
                } else {
                    reference.child(currentuser).child("vaccine").child("Hypertension").setValue("No");
                }
                if (checkbox3.isChecked()) {
                    reference.child(currentuser).child("vaccine").child("Heart disease").setValue("Heart disease");
                } else {
                    reference.child(currentuser).child("vaccine").child("Heart disease").setValue("No");
                }
                if (checkbox4.isChecked()) {
                    reference.child(currentuser).child("vaccine").child("Asthma").setValue("Asthma");
                } else {
                    reference.child(currentuser).child("vaccine").child("Asthma").setValue("No");
                }
                if (checkbox5.isChecked()) {
                    reference.child(currentuser).child("vaccine").child("Stroke").setValue("Stroke");
                } else {
                    reference.child(currentuser).child("vaccine").child("Stroke").setValue("No");
                }
                if (checkbox6.isChecked()) {
                    reference.child(currentuser).child("vaccine").child("Lung Disease").setValue("Chronic lung disease");
                } else {
                    reference.child(currentuser).child("vaccine").child("Lung Disease").setValue("No");
                }
            }

                if(vaccine_que4_johor.isChecked()){
                    reference.child(currentuser).child("state").setValue("Johor");
                }else if(vaccine_que4_kedah.isChecked()){
                    reference.child(currentuser).child("state").setValue("Kedah");
                }else if(vaccine_que4_kelantan.isChecked()){
                    reference.child(currentuser).child("state").setValue("Kelantan");
                }else if(vaccine_que4_melaka.isChecked()){
                    reference.child(currentuser).child("state").setValue("Melaka");
                }else if(vaccine_que4_negerisembilan.isChecked()){
                    reference.child(currentuser).child("state").setValue("Negeri Sembilan");
                }else if(vaccine_que4_pahang.isChecked()){
                    reference.child(currentuser).child("state").setValue("Pahang");
                }else if(vaccine_que4_perak.isChecked()){
                    reference.child(currentuser).child("state").setValue("Perak");
                }else if(vaccine_que4_perlis.isChecked()){
                    reference.child(currentuser).child("state").setValue("Perlis");
                }else if(vaccine_que4_pulaupinang.isChecked()){
                    reference.child(currentuser).child("state").setValue("Pulau Pinang");
                }else if(vaccine_que4_sarawak.isChecked()){
                    reference.child(currentuser).child("state").setValue("Sarawak");
                }else if(vaccine_que4_sabah.isChecked()){
                    reference.child(currentuser).child("state").setValue("Sabah");
                }else if(vaccine_que4_selangor.isChecked()){
                    reference.child(currentuser).child("state").setValue("Selangor");
                }else if(vaccine_que4_terengganu.isChecked()){
                    reference.child(currentuser).child("state").setValue("Terengganu");
                }else if(vaccine_que4_putrajaya.isChecked()){
                    reference.child(currentuser).child("state").setValue("W.P.Putrajaya");
                }else if(vaccine_que4_kl.isChecked()){
                    reference.child(currentuser).child("state").setValue("W.P.Kuala Lumpur");
                }else if(vaccine_que4_labuan.isChecked()){
                    reference.child(currentuser).child("state").setValue("W.P.Labuan");
                }

                reference.child(currentuser).child("postcode").setValue(vaccine_que5.getText().toString().trim());

                // first dose date
                date = dateformat.format(c.getTime());
                c.add(Calendar.DATE, 2);
                date = dateformat.format(c.getTime());
                reference.child(currentuser).child("vaccine").child("firstdose").setValue(date);

                //second dose date
                c.add(Calendar.DATE, 21);
                date = dateformat.format(c.getTime());
                reference.child(currentuser).child("vaccine").child("seconddose").setValue(date);

                //registered
                reference.child(currentuser).child("vaccine").child("registered").setValue("Yes");
                reference.child(currentuser).child("vaccine").child("firstdosestatus").setValue("Incomplete");
                reference.child(currentuser).child("vaccine").child("seconddosestatus").setValue("Incomplete");


                Intent intent = new Intent(VaccineQuestionActivity.this, vaccineSuccessRegisterActivity.class);
                startActivity(intent);

            }





        }

    }
