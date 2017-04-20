package com.monisha.android.mytv.rest;

/**
 * Created by monisha on 15/04/17.
 */

public class ApiEndPoints {


    public static final String BASE_URL = "http://ams-api.astro.com.my/";


    public static final String GET_CHANNELS_URL =  BASE_URL + "ams/v3/getChannelList";
    public static final int GET_CHANNELS_ID =  101;

    public static final String GET_CHANNELS_EVENTS_URL =  BASE_URL + "ams/v3/getEvents";
    public static final int GET_CHANNELS_EVENTS_ID =  102;
    public static final int GET_CHANNELS_EVENTS_PAGINATION_ID =  103;

}
