package com.malpo.northeast;

import android.os.Bundle;

import com.malpo.northeast.models.Ticket;

import org.parceler.Parcels;

import timber.log.Timber;

/**
 * Created by Jack on 3/5/16.
 */
public class TicketListActivity extends BaseActivity {
    private Ticket ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticket = Parcels.unwrap(getIntent().getParcelableExtra("ticket"));
    }
}
