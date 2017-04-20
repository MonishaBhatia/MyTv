package com.monisha.android.mytv.model;

import java.util.List;

/**
 * Created by monisha on 15/04/17.
 */

public class ChannelListModel {


    /**
     * responseMessage : Success
     * responseCode : 200
     * channels : [{"channelId":1,"channelTitle":"HBO","channelStbNumber":411},
     * {"channelId":2,"channelTitle":"Astro Wah Lai Toi "channelStbNumber":311}]
     */

    private String responseMessage;
    private String responseCode;
    private List<ChannelsBean> channels;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<ChannelsBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsBean> channels) {
        this.channels = channels;
    }

    public static class ChannelsBean {
        /**
         * channelId : 1
         * channelTitle : HBO
         * channelStbNumber : 411
         */

        private int channelId;
        private String channelTitle;
        private int channelStbNumber;

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
    }
}
