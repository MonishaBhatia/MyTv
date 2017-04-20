package com.monisha.android.mytv.model;

/**
 * Created by monisha on 19/04/17.
 */

public class EventsModel {

    private String channelNum;
    private String channelTitle;
    private String currentShow;

    public EventsModel(String channelNum, String channelTitle, String currentShow) {
        this.channelNum = channelNum;
        this.channelTitle = channelTitle;
        this.currentShow = currentShow;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getCurrentShow() {
        return currentShow;
    }

    public void setCurrentShow(String currentShow) {
        this.currentShow = currentShow;
    }
}
