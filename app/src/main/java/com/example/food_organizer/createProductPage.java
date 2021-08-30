package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createProductPage extends AppCompatActivity {

    private ImageView imgCrtPrdt;
    private EditText prdtName;
    //expirydate rayali;
    private Button addprdt;
    DatabaseReference ref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product_page);
        imgCrtPrdt =findViewById(R.id.img_upld_crtprt);
        prdtName =findViewById(R.id.et_name_crtprt);
        addprdt =findViewById(R.id.bt_addprt_Crtprt);
        ref= FirebaseDatabase.getInstance().getReference("userProducts");

        addprdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });



    }


}