package com.monisha.android.mytv.activity;


import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.monisha.android.mytv.R;
import com.monisha.android.mytv.adapter.TvGuideAdapter;
import com.monisha.android.mytv.interfaces.OnLoadMoreListener;
import com.monisha.android.mytv.model.EventsModel;
import com.monisha.android.mytv.model.TvGuideEventsModel;
import com.monisha.android.mytv.rest.ApiClientAsynTask;
import com.monisha.android.mytv.rest.ApiEndPoints;
import com.monisha.android.mytv.rest.ApiInterface;
import com.monisha.android.mytv.utils.Constants;
import com.monisha.android.mytv.utils.DateUtils;
import com.monisha.android.mytv.utils.SortUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TvGuideActivity extends BaseActivity implements ApiInterface, AdapterView.OnItemSelectedListener {

    String url;
    int count ;
    StringBuilder channelIds;
    Gson gson;
    private RecyclerView rvTvGuide;
    private TvGuideEventsModel tvGuideEventsModel;
    private List<TvGuideEventsModel.GeteventBean> getEventBeanList = new ArrayList<>();
    private String mChannelName;
    private String mChannelNum;
    private String mChannelEventName;
    private String mDisplayDateTime;
    private String mDisplayDuration;
    private String mStartOfEvent;
    private String mEndOfEvent;
    List<EventsModel> eventsModelList = new ArrayList<>();
    TvGuideAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;
    private int totalCount;
    private int sort;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tv_guide;
    }

    @Override
    protected void initViews() {

        setTitle(R.string.tv_guide);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        rvTvGuide = (RecyclerView) findViewById(R.id.rv_tvguide);

        totalCount = getIntent().getIntExtra("TotalCount", 0);

    }

    @Override
    protected void initData() {

        count = 1;
        if (count <= totalCount) {

            channelIds = new StringBuilder();
            for (int i = 1; i <= Constants.PAGINATION_COUNT; i++) {
                count++;

                if (i == Constants.PAGINATION_COUNT) {
                    channelIds.append(count);

                } else {

                    channelIds.append(count).append(",");
                }
            }
            url = ApiEndPoints.GET_CHANNELS_EVENTS_URL + "?channelId=" + channelIds + "&periodStart=" + DateUtils.addHourToCurrentTime(-6) + "&periodEnd=" + DateUtils.addMinuteToCurrentTime(30);

            Log.d("URL", url);

            new ApiClientAsynTask(this, this, url, null, mProgressBar, contentLayout, "get", mContentContainer, mFrameHeader, ApiEndPoints.GET_CHANNELS_EVENTS_ID).execute();

        }
        }

    @Override
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException, NullPointerException {

        gson = new Gson();
        if (urlId == ApiEndPoints.GET_CHANNELS_EVENTS_ID) {

            parseJsonFromResponse(strresponse);
            populateRecyclerView(eventsModelList);
        }


        if (urlId == ApiEndPoints.GET_CHANNELS_EVENTS_PAGINATION_ID) {

            parseJsonFromResponse(strresponse);
            againPopulatePagination();
        }
    }


    public void parseJsonFromResponse(String strresponse){

        try {
            tvGuideEventsModel = gson.fromJson(strresponse, TvGuideEventsModel.class);
            if (tvGuideEventsModel.getResponseCode().equals("200")) {

                getEventBeanList = tvGuideEventsModel.getGetevent();

                for(int j = 0; j< getEventBeanList.size(); j++){

                    mChannelNum = getEventBeanList.get(j).getChannelStbNumber();
                    mChannelName = getEventBeanList.get(j).getChannelTitle();
                    mChannelEventName = getEventBeanList.get(j).getProgrammeTitle();
                    mDisplayDateTime = getEventBeanList.get(j).getDisplayDateTime();
                    mDisplayDuration = getEventBeanList.get(j).getDisplayDuration();

                    //To convert the given string in format of Hh:mm:ss
                    mStartOfEvent = mDisplayDateTime;
                    mEndOfEvent = mDisplayDateTime;

                    String[] temp = mDisplayDuration.split(":");
                    if(Integer.parseInt(temp[0]) > 0){
                        mEndOfEvent = DateUtils.addHourToTime(mEndOfEvent, Integer.parseInt(temp[0]));
                    }


                    if(Integer.parseInt(temp[1]) > 0){
                        mEndOfEvent = DateUtils.addMinutesToTime(mEndOfEvent, Integer.parseInt(temp[1]));
                    }


                    if(DateUtils.compareTime(mStartOfEvent, mEndOfEvent)){
                        eventsModelList.add(new EventsModel(mChannelNum, mChannelName, mChannelEventName));
                    }

                }

            }
        }catch (JsonSyntaxException | NullPointerException jse) {
            jse.printStackTrace();
        }

    }

    public void populateRecyclerView(final List<EventsModel> eventsModelList){

        int scrollPosition = 0;

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // If a layout manager has already been set, get current scroll position.
        if (rvTvGuide.getLayoutManager() != null) {
            scrollPosition = linearLayoutManager
                    .findFirstCompletelyVisibleItemPosition();

        }

        rvTvGuide.setLayoutManager(linearLayoutManager);
        rvTvGuide.scrollToPosition(scrollPosition);

        mAdapter = new TvGuideAdapter(this, eventsModelList, rvTvGuide);
        rvTvGuide.setAdapter(mAdapter);


        loadMoreItems();

    }


    //Reload the adapter after pagination
    public void againPopulatePagination(){

        mAdapter.notifyDataSetChanged();
        sortList(sort);
        mAdapter.setLoaded();

        loadMoreItems();


    }

    public void loadMoreItems(){

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {


                if (count <= totalCount) {

                    updateList();

                }

            }
        });
    }

    // Method for repopulating recycler view
    private void updateList() {

        if (count <= totalCount) {

            channelIds = new StringBuilder();
            for(int i = 1; i <= Constants.PAGINATION_COUNT; i++){
                count++;

                if(i == Constants.PAGINATION_COUNT){
                    channelIds.append(count);

                }else{

                    channelIds.append(count).append(",");
                }
            }
            url = ApiEndPoints.GET_CHANNELS_EVENTS_URL +"?channelId=" + channelIds + "&periodStart=" + DateUtils.addHourToCurrentTime(-6) + "&periodEnd=" + DateUtils.addMinuteToCurrentTime(30);

            Log.d("URL", url);

            new ApiClientAsynTask(this, this, url, null, null,contentLayout,"get", mContentContainer, mFrameHeader, ApiEndPoints.GET_CHANNELS_EVENTS_PAGINATION_ID).execute();


        }
    }

    @Override
    public void onError() throws NullPointerException {

    }

    @Override
    public void slowInternetConnection() throws NullPointerException {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);

        MenuItem item = menu.findItem(R.id.action_sort);

        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        switch (item){
            case Constants.SORT_CHANNEL_LH_MENU:
                sort = Constants.SORT_CHANNEL_LH;
                sortList(Constants.SORT_CHANNEL_LH);
                break;

            case Constants.SORT_CHANNEL_HL_MENU:
                sort = Constants.SORT_CHANNEL_HL;
                sortList(Constants.SORT_CHANNEL_HL);
                break;

            case Constants.SORT_CHANNEL_ATOZ_MENU:

                sort = Constants.SORT_CHANNEL_ATOZ;
                sortList(Constants.SORT_CHANNEL_ATOZ);
                break;

            case Constants.SORT_CHANNEL_ZTOA_MENU:
                sort = Constants.SORT_CHANNEL_ZTOA;
                sortList(Constants.SORT_CHANNEL_ZTOA);
                break;

            default:
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /* Method for sorting the list as per user's choice
    SORT_CHANNEL_LH - Sorting Channel number low to high
    SORT_CHANNEL_HL- Sorting Channel number high to low
    SORT_CHANNEL_ATOZ - Sorting Channel title A to Z
    SORT_CHANNEL_ZTOA - Sorting Channel title Z to A */

    private void sortList(int sortBy) {

        switch (sortBy) {
            case Constants.SORT_CHANNEL_LH:
                Collections.sort(eventsModelList, new SortUtils.SortChannelNumberTvGuide());
                mAdapter.notifyDataSetChanged();
                break;

            case Constants.SORT_CHANNEL_HL:
                Collections.sort(eventsModelList, new SortUtils.SortChannelNumberTvGuide());
                Collections.reverse(eventsModelList);
                mAdapter.notifyDataSetChanged();
                break;

            case Constants.SORT_CHANNEL_ATOZ:
                Collections.sort(eventsModelList, new SortUtils.SortChannelNameTvGuide());
                mAdapter.notifyDataSetChanged();
                break;

            case Constants.SORT_CHANNEL_ZTOA:
                Collections.sort(eventsModelList, new SortUtils.SortChannelNameTvGuide());
                Collections.reverse(eventsModelList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

}
