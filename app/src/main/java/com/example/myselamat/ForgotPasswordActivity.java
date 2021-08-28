package com.example.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText forgot_email;
    TextView back_login;
    Button forgot_password_button;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String fEmail;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgot_email = (EditText) findViewById(R.id.forgot_email);
        back_login = (TextView) findViewById(R.id.back_login);
        forgot_password_button = (Button) findViewById(R.id.forgot_password_button);
        mAuth = FirebaseAuth.getInstance();

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        forgot_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateData();
            }
        });
    }

    private void validateData(){

        fEmail = forgot_email.getText().toString().trim();

        if(fEmail.isEmpty()){

            forgot_email.setError("Required");
        }else if (!fEmail.matches(emailPattern)){

            forgot_email.setError("Invalid Email");
        }else {

            forgotPassword();
        }
    }

    private void forgotPassword(){
        mAuth.sendPasswordResetEmail(fEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(ForgotPasswordActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(ForgotPasswordActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}