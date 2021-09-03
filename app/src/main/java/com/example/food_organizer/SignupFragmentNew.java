package com.example.food_organizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragmentNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragmentNew extends Fragment {


    private Button crtProfile;
    EditText name,gender,phone,mail,userName,password,cPassword;
    CheckBox tick;

    FirebaseAuth mAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragmentNew() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragmentNew.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragmentNew newInstance(String param1, String param2) {
        SignupFragmentNew fragment = new SignupFragmentNew();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup_new, container, false);

        crtProfile = v.findViewById(R.id.bt_createprofile);
<<<<<<< HEAD

        name=v.findViewById(R.id.etNamePrfl);
        gender=v.findViewById(R.id.etGenderPrfl);
        phone=v.findViewById(R.id.etPhonePrfl);
        mail=v.findViewById(R.id.etMailPrfl);
        userName=v.findViewById(R.id.etSetUsernamePrfl);
        password=v.findViewById(R.id.etSetPassPrfl);
        cPassword=v.findViewById(R.id.etConfrmPassPrfl);
        tick=v.findViewById(R.id.checkBoxPrfl);
=======
        Spinner spin1=(Spinner) v.findViewById(R.id.spin_gender);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
>>>>>>> 4b9b3309f23e412d205c7a94ae53e8ccb02d240d
        crtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAllTextFields()){

                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    Customer newProfile=new Customer(name.getText().toString(),
                            gender.getText().toString(),
                            phone.getText().toString(),
                            mail.getText().toString(),
                            userName.getText().toString(),
                            password.getText().toString());

                    reference.child(phone.getText().toString()).setValue(newProfile);
                    mAuth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(),"User created successfully",Toast.LENGTH_SHORT).show();
                                Intent gotoHome = new Intent(getContext(),HomePage.class);
                                startActivity(gotoHome);
                                getActivity().finish();
                            }
                        }
                    });
                    // Toast.makeText(ProfilePage.this, newProfile.toString(), Toast.LENGTH_SHORT).show();

//                    obj.addUser(newProfile);
                    Toast.makeText(getContext(),"creating profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
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
        else if(password.length() < 6){
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

        if(!tick.isActivated()){
            tick.setError("This field is required");
            return false;
        }

        return true;
    }
}