package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code for button
        signUp = findViewById(R.id.buttonSignUplogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfilePage = new Intent(MainActivity.this,ProfilePage.class);
                startActivity(gotoProfilePage);
                Toast.makeText(MainActivity.this, "Entering profile page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}