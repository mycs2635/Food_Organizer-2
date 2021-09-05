package com.example.food_organizer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    Button signIn,signUp;

    ProgressBar bar;
//
//    FloatingActionButton google;
//   GoogleSignInClient mGoogleSignInClient;
//    private static int RC_SIGN_IN=100;
//////////////////////////////////////////////////////////////////////////////

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterNew adapter;



////////////////////////////////////////////////////////////////////////////////

    private FirebaseAuth mAuth;
    databaseHelper db = new databaseHelper(MainActivity.this);

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user!=null){
//            Intent intent = new Intent(getApplicationContext(),HomePage.class);
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code for button
        mAuth = FirebaseAuth.getInstance();

     //   google=findViewById(R.id.sign_in_button);
        //////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////
      //  createRequest();

        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);

//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });

        /////////////////////////////////////////////////////////

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

//    private void createRequest() {
//        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                //.requestIdToken(getString(R.string.default_web_client_id)) ////////////////
//                .requestIdToken("19123859445-jq01l9j9pd228eb1edo8s1oo2t8u81gc.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
//
//            }
//        });
//    }

//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }


//    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if(result.getResultCode()== Activity.RESULT_OK){
//                Intent intent = result.getData();
//
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
//                // The Task returned from this call is always completed, no need to attach
//                // a listener.
//                try {
//                    GoogleSignInAccount account = task.getResult(ApiException.class);
//                    firebaseAuthWithGoogle(account.getIdToken());
//                }
//                catch (ApiException e){
//                   // Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }
//    });

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account.getIdToken());
//            }
//            catch (ApiException e){
//                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(getApplicationContext(),HomePage.class);
//                            startActivity(intent);
//
//                        } else {
//                            // If sign in fails, display a message to the user .
//                            Toast.makeText(MainActivity.this,"sorry",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }

//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//            if (acct != null) {
//                String personName = acct.getDisplayName();
//                String personGivenName = acct.getGivenName();
//                String personFamilyName = acct.getFamilyName();
//                String personEmail = acct.getEmail();
//                String personId = acct.getId();
//                Uri personPhoto = acct.getPhotoUrl();
//
//                Toast.makeText(this,"User email"+personEmail,Toast.LENGTH_SHORT).show();
//            }
//
//            // Signed in successfully, show authenticated UI.
//            startActivity(new Intent(MainActivity.this,HomePage.class));
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//
//            Log.d("message" , e.toString());
//
//        }
//    }

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