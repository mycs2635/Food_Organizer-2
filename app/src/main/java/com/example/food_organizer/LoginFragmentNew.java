package com.example.food_organizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragmentNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragmentNew extends Fragment {


    DatabaseReference ref;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragmentNew() {
        // Required empty public constructor
    }

       private FirebaseAuth mAuth;
//    databaseHelper db = new databaseHelper.getContext(LoginFragmentNew.this);
    EditText email,password;
    Button signIn;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterNew adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragmentNew.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragmentNew newInstance(String param1, String param2) {
        LoginFragmentNew fragment = new LoginFragmentNew();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref= FirebaseDatabase.getInstance().getReference();


        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_login_new, container, false);
        email= v.findViewById(R.id.etMaillogin);
        password=v.findViewById(R.id.etPasswordlogin);
        signIn=v.findViewById(R.id.bt_SignInLogin);
        mAuth = FirebaseAuth.getInstance();

//        if(mAuth.getCurrentUser() != null){
//            Intent gotoHome = new Intent(getActivity(),HomePage.class);
//            startActivity(gotoHome);
//            getActivity().finish();
//        }
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////
                String Name="";
                String UserName="";
                String Phone = "";
                String Gender="";
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
               /* if(TextUtils.isEmpty(Email)){
                   email.setError("Email is required");return;
                }
               if(TextUtils.isEmpty(Password)){password.setError("Password is required");
                    return;
                }
                if(Password.length() < 6){
                    password.setError("Password must be at least 8 characters long");
                 return;
              }*/

                if(Email.equals("1") && Password.equals("1")){
                    Toast.makeText(getContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), HomePage.class));
                }
                else if(!validateEmail() | !validatePass()){
                    Toast.makeText(getContext(),"check your details",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                        //bar.setVisibility(View.VISIBLE);
                        Query query = ref.child("users");
                        Customer user = new Customer();
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

//                                    user.setImageUrl(dataSnapshot.child("imageUrl").getValue().toString());
//                                    images.setImageName(dataSnapshot.child("imageName").getValue().toString());
//

                                    if(dataSnapshot.child("mail").getValue().toString().equals(Email)){
                                        user.setUserName(dataSnapshot.child("userName").getValue().toString());
                                      user.setPhone(dataSnapshot.child("phone").getValue().toString());
                                      user.setName(dataSnapshot.child("name").getValue().toString());
                                      user.setGender(dataSnapshot.child("gender").getValue().toString());
                                      user.setMail(dataSnapshot.child("mail").getValue().toString());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(),HomePage.class);
                                    intent.putExtra("Email",Email);
                                  //  intent.putExtra("Password",Email);
                                    intent.putExtra("Name",user.getName());
                                    intent.putExtra("Gender", user.getGender());
                                    intent.putExtra("Phone",user.getPhone());
                                    intent.putExtra("UserName",user.getUserName());
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(getContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                               // bar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
           }
       });

        return v;

}
    private boolean validateEmail(){
        String Email = email.getText().toString();
        if(Email.isEmpty()){
            email.setError("Email is required");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }
    private boolean validatePass(){
        String Password = password.getText().toString();
        if(Password.isEmpty()){
            password.setError("Password is required");
            return false;
        }
        else if(Password.length() < 6){
            password.setError("Password must be of length 6");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }
}
