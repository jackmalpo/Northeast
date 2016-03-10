package com.malpo.northeast.models;

import org.parceler.Parcel;

/**
 * Created by Jack on 3/9/16.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Flight {

    private String departTime;
    private String arriveTime;
    private String flightNumber;
    private String nonStop;
    private String length;
    private String price;

    public Flight(){}

    public Flight(String departTime, String arriveTime, String flightNumber, String nonStop, String length, String price) {
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.flightNumber = flightNumber;
        this.nonStop = nonStop;
        this.length = length;
        this.price = price;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getNonStop() {
        return nonStop;
    }

    public void setNonStop(String nonStop) {
        this.nonStop = nonStop;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
