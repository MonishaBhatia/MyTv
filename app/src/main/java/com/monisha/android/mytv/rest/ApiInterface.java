package com.monisha.android.mytv.rest;

import com.google.gson.JsonSyntaxException;
import com.monisha.android.mytv.model.ChannelListModel;


/**
 * Created by monisha on 15/04/17.
 */

public interface ApiInterface {


    /**
     * method to catch response
     *
     * @param strresponse
     *            response get from any web-service
     */
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException,NullPointerException;
    /***
     * If Error or Exception occured
     */
    public void onError() throws NullPointerException;

    public void slowInternetConnection() throws NullPointerException;
}
