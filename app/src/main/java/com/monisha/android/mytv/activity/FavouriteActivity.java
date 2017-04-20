package com.monisha.android.mytv.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.monisha.android.mytv.R;
import com.monisha.android.mytv.adapter.ChannelsAdapter;
import com.monisha.android.mytv.databasehelper.FavouriteSQLiteHelper;
import com.monisha.android.mytv.model.Channels;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends BaseActivity {

    private RecyclerView rvFavouriteChannelList;
    private TextView tvViewAll;

    ChannelsAdapter mAdapter;
    List<Channels> channelsList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favourite;
    }

    @Override
    protected void initViews() {

        setTitle(R.string.my_favorites);
        rvFavouriteChannelList = (RecyclerView) findViewById(R.id.rv_favorite_channels);
        tvViewAll = (TextView) findViewById(R.id.tv_view_all);

    }

    @Override
    protected void initData() {

        channelsList = FavouriteSQLiteHelper.getInstance(this).getAllFavorites();

        if(channelsList.size()>0){

            displayAllFavouriteChannels();

        }else{
            viewAllchannels();
            finish();
        }

        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllchannels();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        channelsList = FavouriteSQLiteHelper.getInstance(this).getAllFavorites();
        displayAllFavouriteChannels();
    }

    public void viewAllchannels(){
        Intent intent = new Intent(this, ChannelsHomeActivity.class);
        startActivity(intent);
    }

    public void displayAllFavouriteChannels(){
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvFavouriteChannelList.setLayoutManager(layoutManager);
        mAdapter = new ChannelsAdapter(this, channelsList);
        rvFavouriteChannelList.setAdapter(mAdapter);

    }
}
