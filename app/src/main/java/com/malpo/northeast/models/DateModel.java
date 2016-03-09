package com.malpo.northeast.models;

import org.parceler.Parcel;

/**
 * Created by Jack on 3/5/16.
 */

@Parcel(Parcel.Serialization.BEAN)
public class DateModel {
    private int year;
    private int month;
    private int day;

    public DateModel(){}

    public DateModel(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
