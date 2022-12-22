package com.example.firebaseauth;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView textButton;
    EditText email,password;
    Button button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Title Modifying...
        this.setTitle("SignIn Activity");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Gating Touch to the XML
        textButton = findViewById(R.id.text__button);
        email = findViewById(R.id.input__email);
        password = findViewById(R.id.input__password);
        button = findViewById(R.id.button);

        //If signup text is clicked...
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

        //If signup button is clicked...
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    private void loginUser() {
        String entered__email = email.getText().toString();
        String entered__password = password.getText().toString();

        mAuth.signInWithEmailAndPassword(entered__email,entered__password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(getApplicationContext(),ProfilePage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

// Learned Form : https://youtu.be/E3KbPoSofpw