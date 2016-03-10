package com.malpo.northeast.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.malpo.northeast.R;
import com.malpo.northeast.models.DateModel;
import com.malpo.northeast.models.Flight;
import com.malpo.northeast.models.Ticket;
import com.metova.slim.annotation.Layout;

import org.parceler.Parcels;

import butterknife.Bind;

/**
 * Created by Jack on 3/9/16.
 */
@Layout(R.layout.activity_checkout)
public class CheckOutActivity extends BaseActivity{

    private Ticket ticket;
    private Flight flight1;
    private Flight flight2;

    @Bind(R.id.from_depart_time)
    TextView fromdepartTime;

    @Bind(R.id.from_landing_time)
    TextView fromlandingTime;

    @Bind(R.id.from_flight_number_text)
    TextView fromflightNumber;

    @Bind(R.id.from_nonstop_text)
    TextView fromnonstoptext;

    @Bind(R.id.from_flight_length_text)
    TextView fromflightLength;

    @Bind(R.id.from_flight_price_text)
    TextView fromflightPrice;


    @Bind(R.id.to_depart_time)
    TextView todepartTime;

    @Bind(R.id.to_landing_time)
    TextView tolandingTime;

    @Bind(R.id.to_flight_number_text)
    TextView toflightNumber;

    @Bind(R.id.to_nonstop_text)
    TextView tononstoptext;

    @Bind(R.id.to_flight_length_text)
    TextView toflightLength;

    @Bind(R.id.to_flight_price_text)
    TextView toflightPrice;

    @Bind(R.id.departing_text)
    TextView departingText;

    @Bind(R.id.returning_text)
    TextView returningText;

    @Bind(R.id.totalPriceText)
    TextView totalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticket = Parcels.unwrap(getIntent().getParcelableExtra("ticket"));
        flight1 = Parcels.unwrap(getIntent().getParcelableExtra("flight1"));
        flight2 = Parcels.unwrap(getIntent().getParcelableExtra("flight2"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.checkout_toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Checkout");
        }

        setFlightTexts();
        setupDates();
        calculateTotalPrice();
    }

    private void setupDates(){
        String departing = departingText.getText().toString();
        DateModel fromDate = ticket.getFromDate();
        departing += " (" + fromDate.getMonth() + "/" + fromDate.getDay() + "/" + fromDate.getYear() + ")";
        departingText.setText(departing);

        String arriving = returningText.getText().toString();
        DateModel toDate = ticket.getToDate();
        arriving += " (" + toDate.getMonth() + "/" + toDate.getDay() + "/" + toDate.getYear() + ")";
        returningText.setText(arriving);
    }

    private void setFlightTexts(){
        fromdepartTime.setText(flight1.getDepartTime());
        fromlandingTime.setText(flight1.getArriveTime());
        fromflightNumber.setText(flight1.getFlightNumber());
        fromnonstoptext.setText(flight1.getNonStop());
        fromflightLength.setText(flight1.getLength());
        fromflightPrice.setText(flight1.getPrice());

        todepartTime.setText(flight2.getDepartTime());
        tolandingTime.setText(flight2.getArriveTime());
        toflightNumber.setText(flight2.getFlightNumber());
        tononstoptext.setText(flight2.getNonStop());
        toflightLength.setText(flight2.getLength());
        toflightPrice.setText(flight2.getPrice());
    }

    private void calculateTotalPrice(){
        String fromPrice = flight1.getPrice().substring(1, flight1.getPrice().length());
        String toPrice = flight2.getPrice().substring(1, flight2.getPrice().length());

        int fPrice = Integer.valueOf(fromPrice);
        int tPrice = Integer.valueOf(toPrice);

        int total = fPrice + tPrice;

        String totalPrice = totalPriceText.getText().toString() + " $" + String.valueOf(total);

        totalPriceText.setText(totalPrice);
    }




}
