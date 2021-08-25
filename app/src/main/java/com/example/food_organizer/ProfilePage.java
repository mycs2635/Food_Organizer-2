package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ProfilePage extends AppCompatActivity {

    Button crtProfile;
    EditText name,gender,phone,mail,userName,password,cPassword;
    CheckBox tick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        //signup button opens profile page
        Intent receive = getIntent();
        //
        crtProfile=findViewById(R.id.bt_createprofile);
        name=findViewById(R.id.etNamePrfl);
        gender=findViewById(R.id.etGenderPrfl);
        phone=findViewById(R.id.etPhonePrfl);
        mail=findViewById(R.id.etMailPrfl);
        userName=findViewById(R.id.etSetUsernamePrfl);
        password=findViewById(R.id.etSetPassPrfl);
        cPassword=findViewById(R.id.etConfrmPassPrfl);
        tick=findViewById(R.id.checkBoxPrfl);

        //button listener for create profile
        crtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper obj=new databaseHelper(ProfilePage.this);
                if(checkAllTextFields()){

                    Customer newProfile=new Customer(name.getText().toString(),gender.getText().toString(),phone.getText().toString(),mail.getText().toString(),userName.getText().toString(),password.getText().toString(),cPassword.getText().toString(),tick.isChecked());
                    // Toast.makeText(ProfilePage.this, newProfile.toString(), Toast.LENGTH_SHORT).show();
                    obj.addUser(newProfile);
                    Toast.makeText(ProfilePage.this,"creating profile", Toast.LENGTH_SHORT).show();

                }

//                else{
//                    Toast.makeText(ProfilePage.this, "Please check all the details", Toast.LENGTH_SHORT).show();
//                }


            }
        });

    }
    private boolean checkAllTextFields(){
        if(name.length() == 0){
            name.setError("This field is required");
            return false;
        }
        if(gender.length() == 0){
            gender.setError("This field is required");
            return false;
        }
        if(phone.length() == 0){
            phone.setError("This field is required");
            return false;
        }
        if(mail.length() == 0){
            mail.setError("This field is required");
            return false;
        }
        if(userName.length() == 0){
            userName.setError("This field is required");
            return false;
        }
        if(password.length() == 0){
            password.setError("This field is required");
            return false;
        }
        else if(password.length() <= 8){
            password.setError("password length should be at least 8");
            return false;
        }
        if(cPassword.length() == 0){
            cPassword.setError("This field is required");
            return false;
        }
        else if (!cPassword.getText().toString().equals(password.getText().toString())){
            cPassword.setError("This field should be matched with password");
            return false;
        }

//        if(!tick.isActivated()){
//            tick.setError("This field is required");
//            return false;
//        }

        return true;
    }
}