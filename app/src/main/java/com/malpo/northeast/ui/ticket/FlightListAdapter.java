package com.malpo.northeast.ui.ticket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malpo.northeast.R;
import com.malpo.northeast.models.Flight;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jack on 12/20/15.
 */
public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.FlightViewHolder> {

    private List<Flight> flights;
    private Context context;
    private OnFlightSelectedListener listener;

    public FlightListAdapter(List<Flight> flights, Context context) {
        this.flights = flights;
        this.context = context;
    }

    public void setOnFlightSelectedListener(OnFlightSelectedListener listener) {
        this.listener = listener;
    }

    public void updateData(List<Flight> flights) {
        this.flights = flights;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.flight_row, viewGroup, false);
        return new FlightViewHolder(v, listener);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder personViewHolder, int i) {
        Flight flight = flights.get(i);
        personViewHolder.bindFlight(flight);
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.depart_time)
        TextView departTime;

        @Bind(R.id.landing_time)
        TextView landingTime;

        @Bind(R.id.flight_number_text)
        TextView flightNumber;

        @Bind(R.id.nonstop_text)
        TextView nonstoptext;

        @Bind(R.id.flight_length_text)
        TextView flightLength;

        @Bind(R.id.flight_price_text)
        TextView flightPrice;

        private Flight flight;
        private OnFlightSelectedListener listener;

        FlightViewHolder(View itemView, OnFlightSelectedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
        }

        public void bindFlight(Flight flight) {
            this.flight = flight;
            departTime.setText(flight.getDepartTime());
            landingTime.setText(flight.getArriveTime());
            flightNumber.setText(flight.getFlightNumber());
            nonstoptext.setText(flight.getNonStop());
            flightLength.setText(flight.getLength());
            flightPrice.setText(flight.getPrice());
        }

        @OnClick(R.id.cv)
        public void onClick(View v) {
            listener.onFlightSelected(flight);
        }
    }

    public interface OnFlightSelectedListener {
        void onFlightSelected(Flight flight);
    }
}