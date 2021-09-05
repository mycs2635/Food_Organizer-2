package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donation extends AppCompatActivity { // donation page
Button donate;
// description of the item addres and phone number
TextView address,description,phone;
DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);  // viewing the donation page
        // getting the values based on the ids
        donate=findViewById(R.id.bt_donate);
        address=findViewById(R.id.ed_address);
        description=findViewById(R.id.ed_food_details);
        phone=findViewById(R.id.ed_number);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to get the instance
                ref=FirebaseDatabase.getInstance().getReference().child("donation");
                Donationdetails d=new Donationdetails(address.getText().toString(),description.getText().toString(),phone.getText().toString());
                try {
                    Toast.makeText(Donation.this, "Donated", Toast.LENGTH_SHORT).show();
                    ref.child(phone.getText().toString()).setValue(d);
                }
                catch(Exception e)  {
                    Toast.makeText(Donation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    //no properties to serealize found on class donation details
                }

            }
        });
    }
}
class Donationdetails{ // details to be gien for the donation
    // fields required
    private String address;
    private String des;
    private String phone;

    // getters and setters methods for the fields
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Donationdetails(){

    }
    // constructor for the class
    public Donationdetails(String address, String des, String phone) {
        this.address = address;
        this.des=des;
        this.phone=phone;
    }
}