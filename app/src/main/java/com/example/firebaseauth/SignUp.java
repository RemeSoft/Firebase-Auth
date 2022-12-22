package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUp extends AppCompatActivity {

    TextView textButton;
    EditText email,password;
    Button button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Title Modifying...
        this.setTitle("SignUp Activity");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Gating Touch to the XML
        textButton = findViewById(R.id.text__button);
        email = findViewById(R.id.input__email);
        password = findViewById(R.id.input__password);
        button = findViewById(R.id.button);

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        //If signup button is clicked...
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();
            }
        });


    }
    private void userRegister() {
        String email__text = email.getText().toString().trim();
        String password__text = password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email__text,password__text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Register Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUp.this, "Register Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

// Learned Form : https://youtu.be/E3KbPoSofpw