package com.example.food_organizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button signUp,signIn;
    EditText email,password;
    ProgressBar bar;
    private FirebaseAuth mAuth;
    databaseHelper db = new databaseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code for button

        mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.buttonSignUplogin);
        signIn = findViewById(R.id.bt_SignInLogin);
        email = findViewById(R.id.etMaillogin);
        password = findViewById(R.id.etPasswordlogin);
        bar = findViewById(R.id.progressBar2);
        bar.setVisibility(View.INVISIBLE);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfilePage = new Intent(MainActivity.this,ProfilePage.class);
                startActivity(gotoProfilePage);
                Toast.makeText(MainActivity.this, "Entering profile page", Toast.LENGTH_SHORT).show();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if(db.checkEmail(email.getText().toString())){
//                    if(db.checkPassword(email.getText().toString(),password.getText().toString())) {
//                        Toast.makeText(MainActivity.this, "Welcome Back!!!", Toast.LENGTH_SHORT).show();
//                        Intent gotoHomePage = new Intent(MainActivity.this,HomePage.class);
//                        startActivity(gotoHomePage);
//                    }
//                    else
//                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }
                if(Password.length() < 6){
                    password.setError("Password must be at least 8 characters long");
                    return;
                }
                bar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Logged in succesfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,HomePage.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}