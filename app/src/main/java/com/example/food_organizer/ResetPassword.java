package com.example.food_organizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ResetPassword extends AppCompatActivity {

    private Button reset;
    private EditText email;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Intent intent = getIntent();
        firebaseAuth = FirebaseAuth.getInstance();
        reset = findViewById(R.id.bt_resetPass);
        email = findViewById(R.id.et_email_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString();
                if(Email.isEmpty()){
                    email.setError("Enter your Email");
                }

                firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetPassword.this,"Reset link is sent succesfully, check your email to reset password",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(ResetPassword.this,"Failed to send Reset link to your Email, Please check your Email",Toast.LENGTH_SHORT).show();
                            email.setError("Check your Email");
                        }
                    }
                });
            }
        });
    }

}