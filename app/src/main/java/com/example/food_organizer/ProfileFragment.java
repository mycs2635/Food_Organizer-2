package com.example.food_organizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;
// shows the personal details of user
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {  // page for the profile section

    private TextView logout;  // logout to get logged out from app
    TextView name,username,phone,gender,mail;
    TextView edit,change_pass;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        // to get the details from the database to display it on the profile page
        name=v.findViewById(R.id.profile_name);
        mail=v.findViewById(R.id.profile_email);
//        gender=v.findViewById(R.id.profile_gender);
        username=v.findViewById(R.id.profile_username);
        phone=v.findViewById(R.id.profile_phone);
        change_pass = v.findViewById(R.id.profile_password);
        edit = v.findViewById(R.id.profile_edit_profile);
        logout=v.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() { // on clicking the logout
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();  // to signout from the app
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() { // to change password
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ResetPassword.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {  // to edit the profile
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfile.class);
                startActivity(intent);
            }
        });

        HomePage hp = (HomePage) getActivity();
        if(hp!=null) {
            Customer user = hp.getDetails();
            name.setText(user.getName());
            username.setText(user.getUserName());
            phone.setText(user.getPhone());
            mail.setText(user.getMail());
        }
        else Toast.makeText(getContext(),"Null pointer exception",Toast.LENGTH_LONG).show();
        return v;


    }
}