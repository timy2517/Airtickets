package com.gmail.timy2517.airtickets.network;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Novik on 18.6.17.
 */

/**
 * The class creates a list of requests
 */


public class RequestMaker {

    public static final String API_KAY = "?apikey=ee0908192fdde19d08f75f009a1fc4f8";
    public static final String URL = "http://android-dev-tests.ru/sapi/v1/flight/tickets/";

    private RequestParams mRequestParams;
    private List<URL> mRequestList;
    DateFormat mRequestDateFormat;
    StringBuilder mStringBuilder;

    public RequestMaker(RequestParams requestParams) {
        mRequestParams = requestParams;
    }

    public List<URL> getRequestList() { //возвращает список запросов для списка дат
        mRequestList = new ArrayList<>();

        mRequestDateFormat = new SimpleDateFormat("yyyyMMdd");

        for (int i = 0; i < mRequestParams.getDateList().size(); i++) {
            mStringBuilder = new StringBuilder();
            mStringBuilder
                    .append(URL)
                    .append(mRequestParams.getCityFrom())
                    .append(mRequestParams.getCityTo())
                    .append(mRequestDateFormat.format(mRequestParams.getDateList().get(i)))
                    .append(API_KAY);
            try {
                mRequestList.add(new URL(mStringBuilder.toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        for (java.net.URL url : mRequestList) {
            Log.d("requestMaker", "requests: " + url.toString());
        }


        return mRequestList;

    }

}
