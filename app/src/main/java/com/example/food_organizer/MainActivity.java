package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button signUp,signIn;
    EditText email,password;

    databaseHelper db = new databaseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code for button
        signUp = findViewById(R.id.buttonSignUplogin);
        signIn = findViewById(R.id.bt_SignInLogin);
        email = findViewById(R.id.etMaillogin);
        password = findViewById(R.id.etPasswordlogin);
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
                    if(db.checkPassword(email.getText().toString(),password.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Welcome Back!!!", Toast.LENGTH_SHORT).show();
                        Intent gotoHomePage = new Intent(MainActivity.this,HomePage.class);
                        startActivity(gotoHomePage);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}