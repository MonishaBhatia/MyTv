package com.monisha.android.mytv.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monisha.android.mytv.R;
import com.squareup.picasso.Picasso;

public abstract class BaseActivity extends AppCompatActivity {

    public ViewGroup mContentContainer;
    public ViewGroup mFrameHeader;
    public ViewGroup mProgressBar;
    public ViewGroup contentLayout;
    private ViewGroup view_noInternet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mContentContainer = (ViewGroup) findViewById(R.id.content_frame_layout);
        mFrameHeader = (ViewGroup) findViewById(R.id.header_frame);

        mProgressBar = (ViewGroup) findViewById(R.id.progress_layout);
        contentLayout = (ViewGroup) findViewById(R.id.content_layout);

        if (getLayoutId() != 0 && getLayoutId() != -1) {
            View layoutView = LayoutInflater.from(this).inflate(getLayoutId(), null);
            mContentContainer.addView(layoutView);
        } else {
            if (getLayoutId() == -1) {
                mContentContainer.setVisibility(View.GONE);
            }
        }


        // No Internet View
        view_noInternet = (ViewGroup) findViewById(R.id.view_no_connection);


        initViews();
        initData();

    }

    // to get the XML Layout Id
    protected abstract int getLayoutId();

    // Initialise Views
    protected abstract void initViews();

    //Initialise Data
    protected abstract void initData();


    @Override
    protected void onResume() {
        super.onResume();
        try {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            unregisterReceiver(mNetworkReceiver);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Receiver for Checking Internet Connection
    private final BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


            // connected to the internet
            if (activeNetwork != null) {

                view_noInternet.setVisibility(View.GONE);
                mContentContainer.setVisibility(View.VISIBLE);

                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's dressModelList plan
                }
            } else {

                view_noInternet.setVisibility(View.VISIBLE);

                mContentContainer.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
            }
        }

    };
}
