package com.monisha.android.mytv.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.monisha.android.mytv.R;
import com.monisha.android.mytv.adapter.ChannelsAdapter;
import com.monisha.android.mytv.model.ChannelListModel;
import com.monisha.android.mytv.model.Channels;
import com.monisha.android.mytv.rest.ApiClientAsynTask;
import com.monisha.android.mytv.rest.ApiEndPoints;
import com.monisha.android.mytv.rest.ApiInterface;
import com.monisha.android.mytv.utils.Constants;
import com.monisha.android.mytv.utils.SortUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ChannelsHomeActivity extends BaseActivity implements ApiInterface, View.OnClickListener {

    private final static String TAG = "##ChannelsHomeActivity";
    List<Channels> channelsList;

    private RecyclerView rvChannelList;
    ChannelsAdapter mAdapter;
    ChannelListModel channelListModel;
    private String mChannelName;
    private int mChannelId;
    private int mChannelNumber;
    private List<ChannelListModel.ChannelsBean> mChannelBeanList = new ArrayList<>();

    private ViewGroup ll_sort_view;
    LinearLayout ll_lowtohigh,ll_hightolow,ll_aToz,ll_zToa;
    RadioButton rb_lowtohigh,rb_hightolow,rb_aToz,rb_zToa;
    BottomSheetDialog mBottomSheetDialogue;
    View bottomSheetView;
    private ImageView img_close_bottomsheet;
    boolean bool_lowtohigh,bool_hightolow,bool_aToz,bool_zToa;


    Gson gson;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {

        setTitle(R.string.app_name);

        rvChannelList = (RecyclerView) findViewById(R.id.rv_channels);

        ll_sort_view = (ViewGroup) findViewById(R.id.ll_sort_view);

        //***** initializing bottom sheet components ******* ////

        mBottomSheetDialogue = new BottomSheetDialog(this);
        bottomSheetView = getLayoutInflater().inflate(R.layout.sort_view_bottomsheet,null);

        mBottomSheetDialogue.setContentView(bottomSheetView);

        ll_lowtohigh = (LinearLayout) mBottomSheetDialogue.findViewById(R.id.ll_lowtohigh);
        ll_hightolow = (LinearLayout) mBottomSheetDialogue.findViewById(R.id.ll_hightolow);
        ll_aToz = (LinearLayout) mBottomSheetDialogue.findViewById(R.id.ll_sort_a_to_z);
        ll_zToa = (LinearLayout) mBottomSheetDialogue.findViewById(R.id.ll_sort_z_to_a);
        rb_lowtohigh = (RadioButton) mBottomSheetDialogue.findViewById(R.id.rb_lowtohigh);
        rb_hightolow = (RadioButton) mBottomSheetDialogue.findViewById(R.id.rb_hightolow);
        rb_aToz = (RadioButton) mBottomSheetDialogue.findViewById(R.id.rb_sort_a_to_z);
        rb_zToa = (RadioButton) mBottomSheetDialogue.findViewById(R.id.rb_sort_z_to_a);
        img_close_bottomsheet = (ImageView) mBottomSheetDialogue.findViewById(R.id.img_close_bottomsheet);


        //***** initializing bottom sheet components ******* ////

        setOnClickListeners();

    }

    private void setOnClickListeners() {

        ll_sort_view.setOnClickListener(this);

        ll_lowtohigh.setOnClickListener(this);
        ll_hightolow.setOnClickListener(this);
        ll_aToz.setOnClickListener(this);
        ll_zToa.setOnClickListener(this);

        rb_lowtohigh.setOnClickListener(this);
        rb_hightolow.setOnClickListener(this);
        rb_aToz.setOnClickListener(this);
        rb_zToa.setOnClickListener(this);

        img_close_bottomsheet.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        new ApiClientAsynTask(this, this, ApiEndPoints.GET_CHANNELS_URL, null, mProgressBar,contentLayout,"get", mContentContainer, mFrameHeader, ApiEndPoints.GET_CHANNELS_ID).execute();
    }


    @Override
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException, NullPointerException {

        gson = new Gson();
        if (urlId == ApiEndPoints.GET_CHANNELS_ID) {
            channelsList = new ArrayList<>();
            mChannelBeanList = new ArrayList<>();
            try {
                channelListModel = gson.fromJson(strresponse, ChannelListModel.class);
                if(channelListModel.getResponseCode().equals("200")){

                    mChannelBeanList = channelListModel.getChannels();

                    for(int i = 0; i < mChannelBeanList.size(); i++){
                        mChannelId = mChannelBeanList.get(i).getChannelId();
                        mChannelName = mChannelBeanList.get(i).getChannelTitle();
                        mChannelNumber = mChannelBeanList.get(i).getChannelStbNumber();

                        channelsList.add(new Channels(mChannelId, mChannelName, mChannelNumber,false));
                    }

                    GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
                    rvChannelList.setLayoutManager(layoutManager);
                    mAdapter = new ChannelsAdapter(this, channelsList);
                    rvChannelList.setAdapter(mAdapter);

                }

            } catch (JsonSyntaxException | NullPointerException jse) {
                jse.printStackTrace();
                Log.d(TAG, jse.toString());
            }

        }
    }

    @Override
    public void onError() throws NullPointerException {

    }

    @Override
    public void slowInternetConnection() throws NullPointerException {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_sort_view:
                rb_lowtohigh.setChecked(bool_lowtohigh);
                rb_hightolow.setChecked(bool_hightolow);
                rb_aToz.setChecked(bool_aToz);
                rb_zToa.setChecked(bool_zToa);
                mBottomSheetDialogue.show();
                break;

            case R.id.ll_lowtohigh :
                bool_lowtohigh = true;
                bool_aToz=false;
                bool_zToa = false;
                bool_hightolow = false;

                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_LH);

                break;

            case R.id.rb_lowtohigh:

                bool_lowtohigh = true;
                bool_aToz=false;
                bool_zToa = false;
                bool_hightolow = false;

                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_LH);

                break;



            case R.id.ll_hightolow:

                bool_hightolow = true;
                //set others false
                bool_aToz=false;
                bool_zToa = false;
                bool_lowtohigh = false;

                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_HL);


                break;

            case R.id.rb_hightolow:

                bool_hightolow = true;
                //set others false
                bool_aToz=false;
                bool_zToa = false;
                bool_lowtohigh = false;

                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_HL);

                break;

            case R.id.ll_sort_a_to_z:

                bool_aToz=true;

                //set others false
                bool_zToa=false;
                bool_hightolow = false;
                bool_lowtohigh = false;
                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_ATOZ);


                break;



            case R.id.rb_sort_a_to_z:

                //set others false
                bool_aToz=true;
                bool_zToa=false;
                bool_hightolow = false;
                bool_lowtohigh = false;

                mBottomSheetDialogue.dismiss();

                    sortList(Constants.SORT_CHANNEL_ATOZ);


                break;

            case R.id.ll_sort_z_to_a:
                bool_zToa=true;

                //set others false
                bool_aToz=false;
                bool_hightolow = false;
                bool_lowtohigh = false;
                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_ZTOA);
                break;


            case R.id.rb_sort_z_to_a:

                bool_zToa=true;

                //set others false
                bool_aToz = false;
                bool_hightolow = false;
                bool_lowtohigh = false;
                mBottomSheetDialogue.dismiss();

                sortList(Constants.SORT_CHANNEL_ZTOA);
                break;

            case R.id.img_close_bottomsheet:

                mBottomSheetDialogue.dismiss();

                break;


            default:

                break; 
        }
    }


    private void sortList(int sortBy) {

        switch (sortBy) {
            case Constants.SORT_CHANNEL_LH:
                Collections.sort(channelsList, new SortUtils.SortChannelNumber());
                mAdapter.notifyDataSetChanged();
                break;

            case Constants.SORT_CHANNEL_HL:
                Collections.sort(channelsList, new SortUtils.SortChannelNumber());
                Collections.reverse(channelsList);
                mAdapter.notifyDataSetChanged();
                break;

            case Constants.SORT_CHANNEL_ATOZ:
                Collections.sort(channelsList, new SortUtils.SortChannelName());
                mAdapter.notifyDataSetChanged();
                break;

            case Constants.SORT_CHANNEL_ZTOA:
                Collections.sort(channelsList, new SortUtils.SortChannelName());
                Collections.reverse(channelsList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_favorite:

                Intent intent = new Intent(ChannelsHomeActivity.this, FavouriteActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.action_tv:

                Intent intentTv = new Intent(ChannelsHomeActivity.this, TvGuideActivity.class);
                intentTv.putExtra("TotalCount" , channelsList.size());
                startActivity(intentTv);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
