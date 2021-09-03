package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProductPage extends AppCompatActivity {

    private ImageView imgCrtPrdt;
    private EditText prdtName;
    //expirydate rayali;
    private Button addprdt;
    DatabaseReference ref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product_page);
        Intent intent = getIntent();
        imgCrtPrdt =findViewById(R.id.img_upld_crtprt);
        prdtName =findViewById(R.id.et_name_crtprt);
        addprdt =findViewById(R.id.bt_addprt_Crtprt);
        ref= FirebaseDatabase.getInstance().getReference("userProducts");
        Spinner spin=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(CreateProductPage.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        addprdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               Products p=new Products(prdtName.getText().toString(),)


            }
        });



    }


}