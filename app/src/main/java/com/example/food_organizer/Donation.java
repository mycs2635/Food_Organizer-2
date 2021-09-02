package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donation extends AppCompatActivity {
Button donate;
TextView address,description,phone;
DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        donate=findViewById(R.id.bt_donate);
        address=findViewById(R.id.ed_address);
        description=findViewById(R.id.ed_food_details);
        phone=findViewById(R.id.ed_number);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref=FirebaseDatabase.getInstance().getReference().child("donation");
                Donationdetails d=new Donationdetails(address.getText().toString(),description.getText().toString(),phone.getText().toString());
                try {
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
class Donationdetails{
    private String address;
    private String des;
    private String phone;

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

    public Donationdetails(String address, String des, String phone) {
        this.address = address;
        this.des=des;
        this.phone=phone;
    }
}