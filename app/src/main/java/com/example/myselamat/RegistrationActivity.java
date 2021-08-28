package com.example.myselamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    EditText register_email, register_password, confirm_password;
    TextView back_login;
    Button register_button;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register_email = (EditText) findViewById(R.id.register_email);
        register_password = (EditText) findViewById(R.id.register_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        back_login = (TextView) findViewById(R.id.back_login);
        register_button = (Button) findViewById(R.id.register_button);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performAuth();
            }
        });
    }

    private void performAuth(){

        String rEmail = register_email.getText().toString().trim();
        String rPassword = register_password.getText().toString().trim();
        String rConfirmPassword = confirm_password.getText().toString().trim();

        if (rEmail.isEmpty()){

            register_email.setError("Required");
        }else if (!rEmail.matches(emailPattern)){

            register_email.setError("Invalid Format");
        }else if (rPassword.isEmpty()|| rPassword.length()<6){

            register_password.setError("The Password Should Exceed 5 Characters");
        }else if (!rPassword.matches(rConfirmPassword)){

            confirm_password.setError("Password Does Not Match");
        }else {

            progressDialog.setTitle("Registration");
            progressDialog.setMessage("Registration Processing...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(rEmail, rPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        progressDialog.dismiss();
                        sendUserToMainActivity();
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }else {

                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToMainActivity(){

        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}