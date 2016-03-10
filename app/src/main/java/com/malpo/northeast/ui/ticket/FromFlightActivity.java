package com.malpo.northeast.ui.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.malpo.northeast.R;
import com.malpo.northeast.models.DateModel;
import com.malpo.northeast.models.Flight;
import com.malpo.northeast.models.Ticket;
import com.malpo.northeast.ui.BaseActivity;
import com.malpo.northeast.utils.FlightGenerator;
import com.metova.slim.annotation.Layout;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Created by Jack on 3/5/16.
 */
@Layout(R.layout.activity_ticket)
public class FromFlightActivity extends BaseActivity implements FlightListAdapter.OnFlightSelectedListener{

    @Bind(R.id.ticket_rv)
    RecyclerView ticketRv;

    private Ticket ticket;
    private FlightListAdapter flightListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticket = Parcels.unwrap(getIntent().getParcelableExtra("ticket"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.ticket_toolbar);
        setSupportActionBar(toolbar);

        String from = ticket.getFromLocation() != null ? ticket.getFromLocation() : "?";
        DateModel fromDate = ticket.getFromDate();
        String date = fromDate != null ? fromDate.getMonth() + "/" + fromDate.getDay() + "/" + fromDate.getYear() : "?";

        String title = "From: " + from + " On: " + date;
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }

        setupRecyclerView();
    }

    private void setupRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        ticketRv.setLayoutManager(llm);

        List<Flight> flights = FlightGenerator.generateFlightList();
        flightListAdapter = new FlightListAdapter(flights, this);
        flightListAdapter.setOnFlightSelectedListener(this);
        ticketRv.setAdapter(flightListAdapter);
    }

    @Override
    public void onFlightSelected(Flight flight) {
        startNextActivity(flight);
    }

    private void startNextActivity(Flight flight){
        Intent intent = new Intent(this, ToFlightActivity.class);
        intent.putExtra("ticket", Parcels.wrap(Ticket.class, ticket));
        intent.putExtra("flight1", Parcels.wrap(Flight.class, flight));
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
