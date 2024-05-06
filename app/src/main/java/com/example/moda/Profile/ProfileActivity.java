package com.example.moda.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.moda.Login.LoginActivity;
import com.example.moda.Login.RegisterActivity;
import com.example.moda.R;
import com.example.moda.Utils.BottomNavigationViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 3;
    private Context mContext = ProfileActivity.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started");


//        setupFirebaseAuth();
//        setupBottomNavigationView();
//        setupToolBar();
        init();
    }

    private void init(){
        Log.d(TAG, "init: inflating profile fragment");
        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }


    //bottom nav setup
//    public void setupBottomNavigationView() {
//
//        Log.d(TAG, "setupBottomNavigationView: starting bottomnavsetup");
//        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
//        BottomNavigationViewHelper.enableBottomNav(mContext, this, bottomNavigationViewEx);
//        Menu menu = bottomNavigationViewEx.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
//        menuItem.setChecked(true);
//    }
//
//    private void setupToolBar() {
//        Toolbar toolbar = findViewById(R.id.profiletoolbar);
//        setSupportActionBar(toolbar);
//
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                Log.d(TAG, "onMenuItemClick: clicked menu item" + item);
//                switch (item.getItemId()) {
//                    case R.id.signout:
//                        Log.d(TAG, "onOptionsItemSelected: attempting to sign out");
//                        mAuth.signOut();
//                        finish();
//                }
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == R.id.signout) {
//            Toast.makeText(mContext, "Sign out clicked", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "onOptionsItemSelected: attempting to sign out");
//            mAuth.signOut();
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.profile_menu, menu);
//        return true;
//    }
//
//     /*
//    -------------------------------------------firebase---------------------------------------------
//     */
//
//    private void setupFirebaseAuth() {
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                if (user != null) {
//                    //user is signed in
//                    Log.d(TAG, "onAuthStateChanged: signed in" + user.getUid());
//                } else {
//
//                    Log.d(TAG, "CHECKING ELSE: inside else");
//                    //user is signed out
//                    Log.d(TAG, "onAuthStateChanged: signed out");
//
//                    Log.d(TAG, "onAuthStateChanged: navigating to loginactivity");
//                    Intent intent = new Intent(mContext, RegisterActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
//            }
//        };
//    }
}
