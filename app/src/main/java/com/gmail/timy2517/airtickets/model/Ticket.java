package com.gmail.timy2517.airtickets.model;

/**
 * Created by Artem Novik on 17.6.17.
 */

public class Ticket {
    private String cityNameFrom;
    private String cityNameTo;
    private String iataFrom;
    private String iataTo;
    private String airportNameFrom;
    private String airportNameTo;
    private String depDate;
    private String depTime;
    private String arrDate;
    private String arrTime;
    private int duration;
    private String durationStr;
    private String airlineCode;
    private String airlineName;
    private String flightNumber;
    private String aircraft;
    private String airlineCode2;
    private String airlineName2;
    private String flightNumber2;
    private String aircraft2;
    private int price;
    private String priceCurrency;
    private String code;

    public boolean isWithTransfer() {
        if (airlineCode2 == null &&
                flightNumber2 == null &&
                aircraft2 == null) {
            return false;
        } else return true;
    }

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public void setCityNameFrom(String cityNameFrom) {
        this.cityNameFrom = cityNameFrom;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public void setCityNameTo(String cityNameTo) {
        this.cityNameTo = cityNameTo;
    }

    public String getIataFrom() {
        return iataFrom;
    }

    public void setIataFrom(String iataFrom) {
        this.iataFrom = iataFrom;
    }

    public String getIataTo() {
        return iataTo;
    }

    public void setIataTo(String iataTo) {
        this.iataTo = iataTo;
    }

    public String getAirportNameFrom() {
        return airportNameFrom;
    }

    public void setAirportNameFrom(String airportNameFrom) {
        this.airportNameFrom = airportNameFrom;
    }

    public String getAirportNameTo() {
        return airportNameTo;
    }

    public void setAirportNameTo(String airportNameTo) {
        this.airportNameTo = airportNameTo;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getAirlineCode2() {
        return airlineCode2;
    }

    public void setAirlineCode2(String airlineCode2) {
        this.airlineCode2 = airlineCode2;
    }

    public String getAirlineName2() {
        return airlineName2;
    }

    public void setAirlineName2(String airlineName2) {
        this.airlineName2 = airlineName2;
    }

    public String getFlightNumber2() {
        return flightNumber2;
    }

    public void setFlightNumber2(String flightNumber2) {
        this.flightNumber2 = flightNumber2;
    }

    public String getAircraft2() {
        return aircraft2;
    }

    public void setAircraft2(String aircraft2) {
        this.aircraft2 = aircraft2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
