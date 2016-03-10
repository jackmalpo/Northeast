package com.malpo.northeast.utils;

import com.malpo.northeast.models.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 3/9/16.
 */
public class FlightGenerator {

    public static List<Flight> generateFlightList(){
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("6:00 AM", "7:10 AM", "Flight 75847", "Nonstop", "1 hr 10 min", "$250"));
        flights.add(new Flight("8:00 AM", "9:15 AM", "Flight 96747", "Nonstop", "1 hr 15 min", "$200"));
        flights.add(new Flight("9:30 AM", "10:45 AM", "Flight 34769", "Nonstop", "1 hrs 15 min", "$350"));
        flights.add(new Flight("12:00 PM", "1:30 PM", "Flight 85203", "Nonstop", "1 hrs 30 min", "$450"));
        flights.add(new Flight("2:00 PM", "4:45 AM", "Flight 69447", "1 stop", "2 hrs 45 min", "$300"));
        flights.add(new Flight("3:00 PM", "6:30 PM", "Flight 25544", "1 stop", "3 hrs 30 min", "$350"));
        flights.add(new Flight("6:00 PM", "9:00 PM", "Flight 77899", "1 stop", "3 hrs 0 min", "$275"));
        flights.add(new Flight("8:00 PM", "9:20 PM", "Flight 37498", "Nonstop", "1 hr 20 min", "$250"));
        flights.add(new Flight("8:30 PM", "12:30 AM", "Flight 38572", "2 stops", "4 hrs 0 min", "$2800"));
        flights.add(new Flight("10:00 PM", "12:05 AM", "Flight 96903", "Nonstop", "2 hrs 5 min", "$300"));
        return flights;
    }
}
