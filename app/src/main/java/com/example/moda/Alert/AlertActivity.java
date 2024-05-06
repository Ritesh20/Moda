package com.example.moda.Alert;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moda.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.example.moda.Utils.BottomNavigationViewHelper;

public class AlertActivity extends AppCompatActivity {

    private static final String TAG = "AlertActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mContext = AlertActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        setupBottomNavigationView();

    }

    public void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: starting bottomnavsetup");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableBottomNav(mContext, this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
