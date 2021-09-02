package com.example.food_organizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Collect extends AppCompatActivity {
    RecyclerView recyclerView;

    private DatabaseReference ref;
    private ArrayList<Donationdetails> foodList;
    private CollectRecyclerAdapter  recyclerAdaptor;



    private void getDataFromFirebase() {

        Query query = ref.child("donation");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //   clearAll();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Donationdetails d=new Donationdetails();
                    d.setAddress(dataSnapshot.child("address").getValue(String.class));
                    d.setPhone(dataSnapshot.child("phone").getValue(String.class));
                    d.setDes(dataSnapshot.child("des").getValue(String.class));
                    foodList.add(d);
                }
                try {

                    recyclerAdaptor = new CollectRecyclerAdapter(Collect.this, foodList);
                    recyclerView.setAdapter(recyclerAdaptor);
                    recyclerAdaptor.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(Collect.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
//                recyclerView.addOnItemTouchListener(new HomeFragment.RcyclrTouchList(getContext(),recyclerView,new HomeFragment.Clicklstnr(){
//
//
//                    @Override
//                    public void onClick(View view, int pos) {
//
//                    }
//
//                    @Override
//                    public void onLongClick(View view, int pos) {
//
//                    }
//                }));

            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void clearAll(){
        if(foodList != null){
            foodList.clear();

            if(recyclerAdaptor != null){
                recyclerAdaptor.notifyDataSetChanged();
            }
        }
        foodList = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        recyclerView = (RecyclerView)findViewById(R.id.rv_collect);
        recyclerView.setLayoutManager(new LinearLayoutManager(Collect.this));
        recyclerView.setHasFixedSize(true);
        ref = FirebaseDatabase.getInstance().getReference();

        foodList = new ArrayList<>();
        clearAll();

        // get data from firebase database
        getDataFromFirebase();


    }
}