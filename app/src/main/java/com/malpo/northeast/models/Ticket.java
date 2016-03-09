package com.malpo.northeast.models;

import org.parceler.Parcel;

/**
 * Created by Jack on 3/5/16.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Ticket {
    private DateModel fromDate;
    private DateModel toDate;
    private String fromLocation;
    private String toLocation;
    private int adultQuantity;
    private int childQuantity;
    private int seniorQuantity;

    public Ticket() {
    }

    public Ticket(DateModel fromDate, DateModel toDate, String fromLocation, String toLocation,
                  int adultQuantity, int childQuantity, int seniorQuantity) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.adultQuantity = adultQuantity;
        this.childQuantity = childQuantity;
        this.seniorQuantity = seniorQuantity;
    }


    public DateModel getFromDate() {
        return fromDate;
    }

    public void setFromDate(DateModel fromDate) {
        this.fromDate = fromDate;
    }

    public DateModel getToDate() {
        return toDate;
    }

    public void setToDate(DateModel toDate) {
        this.toDate = toDate;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getAdultQuantity() {
        return adultQuantity;
    }

    public void setAdultQuantity(int adultQuantity) {
        this.adultQuantity = adultQuantity;
    }

    public int getChildQuantity() {
        return childQuantity;
    }

    public void setChildQuantity(int childQuantity) {
        this.childQuantity = childQuantity;
    }

    public int getSeniorQuantity() {
        return seniorQuantity;
    }

    public void setSeniorQuantity(int seniorQuantity) {
        this.seniorQuantity = seniorQuantity;
    }
}
