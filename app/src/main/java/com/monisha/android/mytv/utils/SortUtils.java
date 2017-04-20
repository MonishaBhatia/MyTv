package com.monisha.android.mytv.utils;

import com.monisha.android.mytv.model.Channels;
import com.monisha.android.mytv.model.EventsModel;

import java.util.Comparator;

/**
 * Created by monisha on 18/04/17.
 */

public class SortUtils {

    public static class SortChannelNumber implements Comparator<Channels> {

        @Override
        public int compare(Channels channel, Channels channel1) {
            return channel.getChannelStbNumber() - channel1.getChannelStbNumber();
        }
    }

    public static class SortChannelName implements Comparator<Channels> {

        @Override
        public int compare(Channels channel, Channels channel1) {
            int i = channel.getChannelTitle().compareToIgnoreCase(channel1.getChannelTitle());

            if (i < 0) {
                return -1;
            } else if (i == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }


    public static class SortChannelNumberTvGuide implements Comparator<EventsModel> {

        @Override
        public int compare(EventsModel eventsModel, EventsModel eventsModel1) {
            int i = eventsModel.getChannelNum().compareToIgnoreCase(eventsModel1.getChannelNum());

            if (i < 0) {
                return -1;
            } else if (i == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }


    public static class SortChannelNameTvGuide implements Comparator<EventsModel> {

        @Override
        public int compare(EventsModel eventsModel, EventsModel eventsModel1) {
            int i = eventsModel.getChannelTitle().compareToIgnoreCase(eventsModel1.getChannelTitle());

            if (i < 0) {
                return -1;
            } else if (i == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }

}
