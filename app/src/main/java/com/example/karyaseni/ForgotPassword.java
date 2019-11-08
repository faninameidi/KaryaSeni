package com.example.karyaseni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText emailForget;
    Button btnForgetPassword,btnBack;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailForget = findViewById(R.id.emailForget);
        btnForgetPassword = findViewById(R.id.btnForgetPassword);
        btnBack = findViewById(R.id.btnBack);

        mAuth = FirebaseAuth.getInstance();

        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailForget.getText().toString();

                if(TextUtils.isEmpty(email)){
                    emailForget.setError("Email harus diisi");
                    return;
                }
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                emailForget.setText("");
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this,"Email Reset Already Send",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(ForgotPassword.this,"Email Reset Failed to Send",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }}