package com.example.moda.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.moda.Login.LoginActivity;
import com.example.moda.Models.Photo;
import com.example.moda.Models.User;
import com.example.moda.Models.UserAccountSettings;
import com.example.moda.Models.UserSettings;
import com.example.moda.R;
import com.example.moda.Utils.BottomNavigationViewHelper;
import com.example.moda.Utils.FirebaseMethods;
import com.example.moda.Utils.Heart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private static final int ACTIVITY_NUM = 4;


    private TextView  mFollowers, mFollowing, mUsername, mFullname;
    private CircleImageView mProfilePhoto;
    private Toolbar toolbar;
    private BottomNavigationViewEx bottomNavigationView;

    private Context mContext;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods mFirebaseMethods;

    //widgets
    private ImageView mHeartRed;
    private ImageView mHeartWhite;

    //vars
    private GestureDetector mGestureDetector;
    private Heart mHeart;
    private Photo mPhoto;
    private Boolean mLikedByCurrentUser;
    private UserAccountSettings mUserAccountSettings;
    private StringBuilder mUsers;
    private String mLikesString = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mFullname = view.findViewById(R.id.profile_name);
        //mProfilePhoto = view.findViewById(R.id.profile_picture);
        mFollowers = view.findViewById(R.id.followers_text);
        mFollowing = view.findViewById(R.id.following_text);
        toolbar = view.findViewById(R.id.profiletoolbar);
        bottomNavigationView = view.findViewById(R.id.bottomnav);
        mContext = getActivity();
        mFirebaseMethods = new FirebaseMethods(mContext);
        mHeartRed = view.findViewById(R.id.image_heart_red);
        mHeartWhite = view.findViewById(R.id.image_heart);

        mHeartRed.setVisibility(View.GONE);
        mHeartWhite.setVisibility(View.VISIBLE);
        mHeart = new Heart(mHeartWhite, mHeartRed);
        mGestureDetector = new GestureDetector(getActivity(), new GestureListener());

        Log.d(TAG, "onCreateView: started.");

        setupBottomNavigationView();
        setupFirebaseAuth();

        testToggle();

        return view;
    }
    private void testToggle(){
        mHeartRed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
        mHeartWhite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            Log.d(TAG, "onDoubleTap: double tap detected.");

            mHeart.toggleLike();

            return true;
        }
    }


    private void setupProfileWidgets(UserSettings userSettings){
        //User user = userSettings.getUser();
        UserAccountSettings settings = userSettings.getSettings();

        mFullname.setText(settings.getFullname());
    }

    //bottom nav setup
    public void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: starting bottomnavsetup");
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavigationViewHelper.enableBottomNav(mContext, getActivity(), bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    /*
    -------------------------------------------firebase---------------------------------------------
     */

    private void setupFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged: signed in" + user.getUid());
                } else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged: signed out");
                }
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //get userinfo from database
                setupProfileWidgets(mFirebaseMethods.getUserSettings(dataSnapshot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
