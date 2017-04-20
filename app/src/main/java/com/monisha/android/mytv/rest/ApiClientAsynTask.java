package com.monisha.android.mytv.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by monisha on 15/04/17.
 */

public class ApiClientAsynTask extends AsyncTask<String,String,String> {


    Context mContext;
    /*
     * Listener to catch and parse response
     */
    ApiInterface mListener;
    /*
     * URl of Web service
     */

    ProgressDialog mProgressDialog;
    String url;
    JSONObject mJsonObject;
    boolean isProgressDialog;
    String via;
    protected ProgressBar mProgress;
    protected int url_id;
    protected ViewGroup mViewGroup;
    protected ViewGroup mContentContainer;
    protected ViewGroup mFrameHeader;
    protected ViewGroup mProgressBar;
    protected ViewGroup contentLayout;

    String accessToken="";

    public ApiClientAsynTask(Context mContext,
                             ApiInterface mListenerRoot, String url,
                             JSONObject mJsonObject, ProgressBar mProgress, String via, ViewGroup mViewGroup,
                             int url_id ) {

        this.mContext = mContext;
        this.mListener = mListenerRoot;
        this.url = url;
        this.mJsonObject = mJsonObject;
        this.mProgress = mProgress;
        this.via = via;
        this.mViewGroup = mViewGroup;
        this.url_id = url_id;
    }

    public ApiClientAsynTask(Context mContext,
                             ApiInterface mListenerRoot, String url,
                             JSONObject mJsonObject, ViewGroup mProgressBar, ViewGroup contentLayout, String via, ViewGroup mContentContainer, ViewGroup mFrameHeader, int url_id ) {

        this.mContext = mContext;
        this.mJsonObject = mJsonObject;
        this.mListener = mListenerRoot;
        this.url = url;
        this.via = via;
        this.mProgressBar = mProgressBar;
        this.contentLayout=contentLayout;
        this.mContentContainer = mContentContainer;
        this.mFrameHeader = mFrameHeader;
        this.url_id = url_id;

    }



    @Override
    protected String doInBackground(String... params) {

        Log.d("###URL###",url);
        String response;

        if (via.equalsIgnoreCase("post")) {

            response = SendHttpPost(url, mJsonObject);

        } else if (via.equalsIgnoreCase("")||via.equalsIgnoreCase("get")){
            response = SendHttpGettstring(url);
        } else if (via.equalsIgnoreCase("Patch")){
            response = SendHttpPatch(url, mJsonObject);
        }else {
            response = SendHttpPut(url,mJsonObject);
        }


        return response;
    }


    @Override
    protected void onPreExecute() {
        super .onPreExecute();

        if(mProgressBar!=null)
        {
            mFrameHeader.setVisibility(View.GONE);
            mContentContainer.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.GONE);

        }

    }


    @Override
    protected void onPostExecute(String response) {

        super .onPostExecute(response);
        Log.d("###RES###",response==null?"":response);

        if(response != null)
        {
            if(response == "")
            {
                mListener.onError();
            }

        }
        if (response != null) {

            mListener.responseWithId(response, via,url_id);


        } else {
            mListener.slowInternetConnection();


        }

        if(mProgressBar!=null)
        {
            mProgressBar.setVisibility(View.GONE);
            mFrameHeader.setVisibility(View.VISIBLE);
            mContentContainer.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.VISIBLE);

        }
    }



    /***
     * method to post json to server
     *
     * @param URL
     *            -URL of web service
     * @param jsonObjSend
     *            -JSON Object
     * @return - response from web service
     */
    public String SendHttpPost(String URL, JSONObject jsonObjSend) {

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObjSend));

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.toString());
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            if (isProgressDialog && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mListener.onError();
        }

        return null;
    }


    public String SendHttpPatch(String URL, JSONObject jsonObjSend) {

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObjSend));

        Request request = new Request.Builder()
                .url(URL)
                .patch(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.toString());
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            if (isProgressDialog&&mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mListener.onError();
        }

        return null;
    }

    public String SendHttpPut(String URL, JSONObject jsonObjSend) {

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObjSend));

        Request request = new Request.Builder()
                .url(URL)
                .put(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.toString());
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            if (isProgressDialog&&mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mListener.onError();
        }

        return null;
    }

    public String SendHttpGettstring(String URL) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .get()
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.toString());
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            if (isProgressDialog&&mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mListener.onError();
        }

        return null;
    }

}
