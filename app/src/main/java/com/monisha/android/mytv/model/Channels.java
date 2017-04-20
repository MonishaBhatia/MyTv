package com.monisha.android.mytv.model;

/**
 * Created by monisha on 15/04/17.
 */

public class Channels {

    private int channelId;
    private String channelTitle;
    private int channelStbNumber;
    private boolean channelFavourite;

    public Channels(){}
    public Channels(int channelId, String channelTitle, int channelStbNumber, boolean channelFavourite) {
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.channelStbNumber = channelStbNumber;
        this.channelFavourite = channelFavourite;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public int getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(int channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public boolean isChannelFavourite() {
        return channelFavourite;
    }

    public void setChannelFavourite(boolean channelFavourite) {
        this.channelFavourite = channelFavourite;
    }
}
