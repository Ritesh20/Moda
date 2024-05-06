package com.example.moda.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.moda.Add.AddActivity;
import com.example.moda.Alert.AlertActivity;
import com.example.moda.Home.HomeActivity;
import com.example.moda.Profile.ProfileActivity;
import com.example.moda.R;
import com.example.moda.Search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {

        Log.d(TAG, "setupBottomNavigationView: setting up bottomnavview");
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableBottomNav(final Context context, final Activity callingActivity, BottomNavigationViewEx view) {

        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:
                        Intent intent1 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;

                    case R.id.alert:
                        Intent intent2 = new Intent(context, AlertActivity.class);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        context.startActivity(intent2);
                        break;

                    case R.id.add:
                        Intent intent3 = new Intent(context, AddActivity.class);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        context.startActivity(intent3);
                        break;

                    case R.id.profile:
                        Intent intent4 = new Intent(context, ProfileActivity.class);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        context.startActivity(intent4);
                        break;

                    case R.id.search:
                        Intent intent5 = new Intent(context, SearchActivity.class);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        context.startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }
}
