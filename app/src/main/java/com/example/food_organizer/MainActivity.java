package com.example.food_organizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button signIn,signUp;

    ProgressBar bar;

//////////////////////////////////////////////////////////////////////////////

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterNew adapter;



////////////////////////////////////////////////////////////////////////////////

    private FirebaseAuth mAuth;
    databaseHelper db = new databaseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code for button
        mAuth = FirebaseAuth.getInstance();


        //////////////////////////////////////////////////////////////

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager);
        FragmentManager fm=getSupportFragmentManager();
        adapter=new FragmentAdapterNew(fm,getLifecycle());
        try {
            pager2.setAdapter(adapter);
        }
          catch(Exception e)  {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



        ///////////////////////////////////////////////////////////////

       // signUp = findViewById(R.id.buttonSignUplogin);
        signIn = findViewById(R.id.bt_SignInLogin);
//        email = findViewById(R.id.etMaillogin);
//        password = findViewById(R.id.etPasswordlogin);
//        bar = findViewById(R.id.progressBar2);
//        bar.setVisibility(View.INVISIBLE);
//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gotoProfilePage = new Intent(MainActivity.this,ProfilePage.class);
//                startActivity(gotoProfilePage);
//                Toast.makeText(MainActivity.this, "Entering profile page", Toast.LENGTH_SHORT).show();
//            }
//        });
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // if(db.checkEmail(email.getText().toString())){
////                    if(db.checkPassword(email.getText().toString(),password.getText().toString())) {
////                        Toast.makeText(MainActivity.this, "Welcome Back!!!", Toast.LENGTH_SHORT).show();
////                        Intent gotoHomePage = new Intent(MainActivity.this,HomePage.class);
////                        startActivity(gotoHomePage);
////                    }
////                    else
////                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                String Email = email.getText().toString().trim();
//                String Password = password.getText().toString().trim();
////                if(TextUtils.isEmpty(Email)){
////                    email.setError("Email is required");
////                    return;
////                }
////                if(TextUtils.isEmpty(Password)){
////                    password.setError("Password is required");
////                    return;
////                }
////                if(Password.length() < 6){
////                    password.setError("Password must be at least 8 characters long");
////                    return;
////                }
//                if(!validateEmail() | !validatePass()){
//                    Toast.makeText(MainActivity.this,"check your details",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else {
//                    bar.setVisibility(View.VISIBLE);
//                    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(MainActivity.this, HomePage.class));
//                                finish();
//                            } else {
//                                Toast.makeText(MainActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                            bar.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                }
//                if(Email.equals("1") && Password.equals("1")){
//                    Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this, HomePage.class));
//                    finish();
//                }
//                else if(!validateEmail() | !validatePass()){
//                    Toast.makeText(MainActivity.this,"check your details",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else {
//                    bar.setVisibility(View.VISIBLE);
//                    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(MainActivity.this, HomePage.class));
//                                finish();
//                            } else {
//                                Toast.makeText(MainActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                            bar.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                }
//            }
//        });
//       signIn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               loginUser(v);
//           }
//       });
    }

//    private boolean validateEmail(){
//<<<<<<< HEAD
//        String Email = email.getText().toString();
//=======
////        String Email = email.getText().toString();
//>>>>>>> d5c1365075d1f3fb62a9448be8555c748fffc73b
//        if(Email.isEmpty()){
//            email.setError("Email is required");
//            return false;
//        }
//        else{
//            email.setError(null);
//            return true;
//        }
//    }
//    private boolean validatePass(){
//        String Password = password.getText().toString();
//        if(Password.isEmpty()){
//            password.setError("Password is required");
//            return false;
//        }
//        else if(Password.length() < 6){
//            password.setError("Password must be of length 6");
//            return false;
//        }
//        else{
//            password.setError(null);
//            return true;
//        }
//    }
//    public void loginUser(View v){
//        if(!validateEmail() | !validatePass()){
//            return;
//        }
//        else{
//            isUser();
//        }
//    }
//
//    private void isUser(){
//        String enteredEmail = email.getText().toString();
//        String enteredPass = password.getText().toString();
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUser = ref.orderByChild("mail").equalTo(enteredEmail);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
////                    email.setError(null);
//                    String passDB = snapshot.child(enteredEmail).child("password").getValue(String.class);
//                    try {
//
//
//                        if (enteredPass.equals(passDB)) {
//                            Intent gotoHome = new Intent(MainActivity.this, HomePage.class);
//                            bar.setVisibility(View.VISIBLE);
//                            startActivity(gotoHome);
//                        } else {
//                            password.setError("Wrong password");
//                            password.requestFocus();
//                            bar.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                    catch (Exception e){
//                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    email.setError("No such email registered");
//                    email.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}