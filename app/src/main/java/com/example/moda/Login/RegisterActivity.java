package com.example.moda.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moda.R;
import com.example.moda.Utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;

    private Context mContext;
    private String email, fullname, username, password, userid;
    private EditText mEmail, mFullname, mUsername, mPassword;
    private Button btnRegister;
    private ProgressBar mProgressbar;

    private TextView textLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
        firebaseMethods = new FirebaseMethods(mContext);

        Log.d(TAG, "onCreate: started");

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void init() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();
                fullname = mFullname.getText().toString();

                if(checkInputs(email, password, username, fullname)){
                    //mProgressbar.setVisibility(View.VISIBLE);

                    firebaseMethods.registerNewEmail(email, password, username, fullname);
                }
            }

        });
    }

    private void initWidgets() {

        btnRegister = findViewById(R.id.signupbtn);
        mEmail = findViewById(R.id.emailReg);
        mPassword = findViewById(R.id.passwordreg);
        mFullname = findViewById(R.id.fullnameReg);
        mUsername = findViewById(R.id.usernamereg);
        mProgressbar = findViewById(R.id.progressbarReg);
        mContext = RegisterActivity.this;
        mProgressbar.setVisibility(View.GONE);
    }

    private boolean checkInputs(String email, String password, String username, String fullname){
        Log.d(TAG, "checkInputs: checking inputs for null values.");
        if(email.equals("") || password.equals("") || username.equals("") || fullname.equals("")){
            Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    private boolean isStringNull(String string) {
//        if (string.equals("")){
//            return true;
//        } else {
//            return false;
//        }
//    }


    /*
    -------------------------------------------firebase---------------------------------------------
     */

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: in setupauth");
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        final Query uniqueUsernameQuery = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("username").equalTo(username);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged: signed in" + user.getUid());

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            uniqueUsernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Log.d(TAG, "onDataChange: count is" + dataSnapshot.getChildrenCount());
                                    if (dataSnapshot.getChildrenCount() > 0){
                                        Toast.makeText(mContext, "Username already exists", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        userid = mAuth.getCurrentUser().getUid();
                                        firebaseMethods.addNewUser("", fullname, email, username, userid, 0, "");
                                        Toast.makeText(mContext, "Signup success", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged: signed out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
