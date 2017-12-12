package com.gmail.timy2517.airtickets.network;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artem Novik on 18.6.17.
 */

public class RequestParams {
    //public static final String SORTING_BY_TIME = "com.gmail.timy2517.airtickets.presenter.time";
    public static final String SORTING_BY_DURATION = "com.gmail.timy2517.airtickets.presenter.transferTime";
    public static final String SORTING_BY_PRICE = "com.gmail.timy2517.airtickets.presenter.price";
    public static final String CHOICE_OF_THE_BEST = "com.gmail.timy2517.airtickets.presenter.best";

    private static final long MILLISECONDS_IN_DAY = 86400000;

    private String mCityFrom;
    private String mCityTo;
    private boolean mWithTransfer;
    private Date mDateFrom;
    private Date mDateTo;
    private List<Date> mDateList;
    private String mSort;

    public RequestParams(){
        mDateFrom = new Date();
        mDateTo = new Date();
    }

    public String getCityFrom() {
        return mCityFrom;
    }

    public void setCityFrom(String cityFrom) {
        mCityFrom = cityFrom;
    }

    public String getCityTo() {
        return mCityTo;
    }

    public void setCityTo(String cityTo) {
        mCityTo = cityTo;
    }

    public boolean isWithTransfer() {
        return mWithTransfer;
    }

    public void setWithTransfer(boolean withTransfer) {
        mWithTransfer = withTransfer;
    }

    public Date getDateFrom() {
        return mDateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        mDateFrom = dateFrom;
    }

    public Date getDateTo() {
        return mDateTo;
    }

    public void setDateTo(Date dateTo) {
        mDateTo = dateTo;
    }

    public String getSort() {
        return mSort;
    }

    public void setSort(String sort) {
        mSort = sort;
    }

    public List<Date> getDateList() {
        mDateList = new ArrayList<>();

        for (int i = 0; i <= (mDateTo.getTime()-mDateFrom.getTime()) / MILLISECONDS_IN_DAY; i++){
            mDateList.add(new Date(mDateFrom.getTime() + i*MILLISECONDS_IN_DAY));
        }
        return mDateList;
    }
}
