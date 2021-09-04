package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CreateProductPage extends AppCompatActivity {

    private ImageView imgCrtPrdt;
    private EditText prdtName;
    private Button addprdt;
    private TextView tvDate;
    DatePickerDialog.OnDateSetListener setListener;


    DatabaseReference ref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //date picker code

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product_page);

        tvDate=findViewById(R.id.tv_date);
        Calendar calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(CreateProductPage.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=day+"/"+month+"/"+year;
                tvDate.setText(date);

            }
        };







        Intent intent = getIntent();//add button (from homepage to create product page)
        imgCrtPrdt =findViewById(R.id.img_upld_crtprt);
        prdtName =findViewById(R.id.et_name_crtprt);
        addprdt =findViewById(R.id.bt_addprt_Crtprt);
        ref= FirebaseDatabase.getInstance().getReference("userProducts");
        Spinner spin=(Spinner) findViewById(R.id.spin_store);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(CreateProductPage.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.store));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        TextView place= (TextView) spin.getSelectedView();
        addprdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Products p=new Products(prdtName.getText().toString(),tvDate.getText().toString(),place.getText().toString());
               Intent intent=new Intent(CreateProductPage.this,InventoryFragment.class);


            }
        });



    }


}