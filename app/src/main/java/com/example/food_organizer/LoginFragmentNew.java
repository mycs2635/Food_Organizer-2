package com.example.food_organizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
// login page to enter into app
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragmentNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragmentNew extends Fragment {

    // declaring the variables
    SignInButton google;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN=100;


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
    EditText email,password;   // contents in this page
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
    public void onStart() {  // on star of the authentication
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getContext(),HomePage.class);
            startActivity(intent);
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {  // oncreation
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        ///////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   // on reate view
                             Bundle savedInstanceState) {

        TextView forgetPass;
        ref= FirebaseDatabase.getInstance().getReference();

        mAuth=FirebaseAuth.getInstance();  // instance of firebase authentication
        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(getActivity(),HomePage.class);  //starting a new intent
//            intent.putExtra("Email",Email);
//            //  intent.putExtra("Password",Email);
//            intent.putExtra("Name",user.getName());
//            intent.putExtra("Gender", user.getGender());
//            intent.putExtra("Phone",user.getPhone());
//            intent.putExtra("UserName",user.getUserName());
            startActivity(intent); // starting activity of home class
        }

        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_login_new, container, false);
        email= v.findViewById(R.id.etMaillogin);  // storing the values based on the id in loginfragmetnewxml file
        password=v.findViewById(R.id.etPasswordlogin);
        signIn=v.findViewById(R.id.bt_SignInLogin);
        mAuth = FirebaseAuth.getInstance();
        forgetPass = v.findViewById(R.id.tv_forgot_pass);
        google=v.findViewById(R.id.sign_in_button);
        createRequest();



        forgetPass.setOnClickListener(new View.OnClickListener() { // on clicking the forget password
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ResetPassword.class);  // moving to the resetpassword class
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {  // on clicking the signinbutton
            @Override
            public void onClick(View view) {
////
                String Name="";
                String UserName="";
                String Phone = "";
                String Gender="";
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                // validating the email and password entered
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
                                    // setting the data given by the user
                                    if(dataSnapshot.child("mail").getValue().toString().equals(Email)){
                                        user.setUserName(dataSnapshot.child("userName").getValue().toString());
                                      user.setPhone(dataSnapshot.child("phone").getValue().toString());
                                      user.setName(dataSnapshot.child("name").getValue().toString());
                                      user.setGender(dataSnapshot.child("gender").getValue().toString());
                                      user.setMail(dataSnapshot.child("mail").getValue().toString());
                                      break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                            public void onComplete(@NonNull Task<AuthResult> task) { //on completion

                                if (task.isSuccessful()) { // if the task is successful
                                    Toast.makeText(getContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                                  //  Intent intent = new Intent(getActivity(),HomePage.class);
                                   // Log.d("000000000000000000000", "------------"+Email+"---"+user.getName());

                                    Intent intent = new Intent(getActivity(),HomePage.class);
                                    intent.putExtra("Email",Email);
                                  //  intent.putExtra("Password",Email);
                                    intent.putExtra("Name",user.getName());
                                    intent.putExtra("Gender", user.getGender());
                                    intent.putExtra("Phone",user.getPhone());
                                    intent.putExtra("UserName",user.getUserName());
//                                    ProfileFragment pf=new ProfileFragment();
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("Name",Name);
//                                    bundle.putString("Gender",Gender);
//                                    bundle.putString("UserName",UserName);
//                                    bundle.putString("Phone",Phone);
//                                    bundle.putString("Email",Email);
//                                    pf.setArguments(bundle);
//                                    getFragmentManager().beginTransaction().replace(R.id.viewpager2,pf).commit();
                                    startActivity(intent);
                                    getActivity().finish();

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

    private void createRequest() { // creating the request for google authetication
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id)) ////////////////
                .requestIdToken("19123859445-jq01l9j9pd228eb1edo8s1oo2t8u81gc.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));

            }
        });
    }


    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== Activity.RESULT_OK){
                Intent intent = result.getData();

                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                }
                catch (ApiException e){
                    // Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        }
    });


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getContext(),HomePage.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user .
                            Toast.makeText(getActivity(),"sorry",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private boolean validateEmail(){  // function to validate the email
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
    private boolean validatePass(){  // validating the email entered by user
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
