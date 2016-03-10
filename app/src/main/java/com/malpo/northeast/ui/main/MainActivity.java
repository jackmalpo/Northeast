package com.malpo.northeast.ui.main;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.malpo.northeast.R;
import com.malpo.northeast.models.DateModel;
import com.malpo.northeast.models.Ticket;
import com.malpo.northeast.ui.BaseActivity;
import com.malpo.northeast.ui.ticket.FromFlightActivity;
import com.metova.slim.annotation.Layout;

import org.parceler.Parcels;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;

@Layout(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Bind(R.id.parent)
    RelativeLayout parent;

    @Bind(R.id.from_calendar_btn)
    ImageView fromCalendarBtn;

    @Bind(R.id.to_calendar_btn)
    ImageView toCalendarBtn;

    @Bind(R.id.from_text)
    AutoCompleteTextView fromText;

    @Bind(R.id.to_text)
    AutoCompleteTextView toText;

    @Bind(R.id.gps_pin_btn)
    ImageView gpsPinBtn;

    @Bind(R.id.adult_quantity)
    TextView adultQuantity;

    @Bind(R.id.child_quantity)
    TextView childQuantity;

    @Bind(R.id.senior_quantity)
    TextView seniorQuantity;

    @Bind(R.id.from_date_text)
    TextView fromDateText;

    @Bind(R.id.to_date_text)
    TextView toDateText;

    private Calendar calendar;
    private DateFormatSymbols dateFormatSymbols;

    private int fromYear;
    private int fromMonth;
    private int fromDay;

    private int toYear;
    private int toMonth;
    private int toDay;

    private boolean toDateSet;
    private boolean fromDateSet;

    private String fromLocation;
    private String toLocation;

    private int adultQuantityVal;
    private int childQuantityVal;
    private int seniorQuantityVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Cal Instances / Date
        calendar = Calendar.getInstance();
        dateFormatSymbols = DateFormatSymbols.getInstance();
        fromYear = toYear = calendar.get(Calendar.YEAR);
        fromMonth = toMonth = calendar.get(Calendar.MONTH);
        fromDay = toDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Set default from / to date text;
        String fromDateDefaultText = dateFormatSymbols.getShortMonths()[fromMonth] + " " + fromDay;
        fromDateText.setText(fromDateDefaultText);
        toDateText.setText("?");

        adultQuantityVal = 1;
        adultQuantity.setText(String.valueOf(adultQuantityVal));
    }

    private void startNextActivity(Ticket ticket){
        Intent intent = new Intent(this, FromFlightActivity.class);
        intent.putExtra("ticket", Parcels.wrap(Ticket.class, ticket));
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    /*
        GPS Click Listener
     */
    @OnClick(R.id.gps_pin_btn)
    public void onGpsClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Allow Northeast app to use your location?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                fromLocation = "BNA";
                fromText.setText(fromLocation);
                toText.requestFocus();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*
        Done Click Listener
     */
    @OnClick(R.id.check_button)
    public void onCheckClick(View v){
        if(!toDateSet){
            Snackbar.make(parent, "To date has not been set!", Snackbar.LENGTH_SHORT).show();
        } else {
            toLocation = toText.getText().toString();
            DateModel fromDateModel = new DateModel(fromYear, fromMonth, fromDay);
            DateModel toDateModel = new DateModel(toYear, toMonth, toDay);
            Ticket myTicket = new Ticket(fromDateModel, toDateModel, fromLocation, toLocation,
                    adultQuantityVal, childQuantityVal, seniorQuantityVal);
            startNextActivity(myTicket);
        }
    }

    /*
        Calendar Click Listeners
     */

    @OnClick(R.id.from_calendar_btn)
    public void onFromCalendarClick(View v){
        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                onFromDateDialogClosed(year, monthOfYear, dayOfMonth);
                fromDateSet = true;
            }
        }, fromYear, fromMonth, fromDay);

        mDatePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onFromDateDialogClosed(fromYear, fromMonth, fromDay);
            }
        });

        mDatePicker.setTitle("Select departure date");
        mDatePicker.show();
    }

    private void onFromDateDialogClosed(int year, int monthOfYear, int dayOfMonth){
        initFromVars(year, monthOfYear, dayOfMonth);

        String date = dateFormatSymbols.getShortMonths()[monthOfYear] + " " + dayOfMonth;
        fromDateText.setText(date);

        if(!toDateSet)
            initToVars(year, monthOfYear, dayOfMonth);
    }


    @OnClick(R.id.to_calendar_btn)
    public void onToCalendarClick(View v){
        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                onToDateDialogClosed(year, monthOfYear, dayOfMonth);
                toDateSet = true;
            }
        }, !fromDateSet ? toYear : fromYear, !fromDateSet ? toMonth : fromMonth, !fromDateSet ? toDay : fromDay);

        mDatePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(!toDateSet){
                    toDateText.setText("?");
                } else {
                    onToDateDialogClosed(toYear, toMonth, toDay);
                }

            }
        });
        mDatePicker.setTitle("Select return date");
        mDatePicker.show();
    }

    private void onToDateDialogClosed(int year, int monthOfYear, int dayOfMonth){

        String date = dateFormatSymbols.getShortMonths()[monthOfYear] + " " + dayOfMonth;
        toDateText.setText(date);

        if(!toDateSet)
            initToVars(year, monthOfYear, dayOfMonth);
    }

    private void initToVars(int year, int monthOfYear, int dayOfMonth){
        this.toYear = year;
        this.toMonth = monthOfYear;
        this.toDay = dayOfMonth;
    }

    private void initFromVars(int year, int monthOfYear, int dayOfMonth){
        this.fromYear = year;
        this.fromMonth = monthOfYear;
        this.fromDay = dayOfMonth;
    }


    /*
        Ticket Quantity Click Listeners...
     */

    //ADULT
    @OnClick(R.id.adult_up_btn)
    public void onAdultUpClick(View v){
        adultQuantityVal = Integer.valueOf(adultQuantity.getText().toString()) + 1;
        adultQuantity.setText(String.valueOf(adultQuantityVal));
    }

    @OnClick(R.id.adult_down_btn)
    public void onAdultDownClick(View v){
        adultQuantityVal = Integer.valueOf(adultQuantity.getText().toString()) - 1;
        adultQuantity.setText(adultQuantityVal >= 0 ? String.valueOf(adultQuantityVal) : String.valueOf(0));
    }

    //CHILD
    @OnClick(R.id.child_up_btn)
    public void onChildUpClick(View v){
        childQuantityVal = Integer.valueOf(childQuantity.getText().toString()) + 1;
        childQuantity.setText(String.valueOf(childQuantityVal));
    }

    @OnClick(R.id.child_down_btn)
    public void onChildDownClick(View v){
        childQuantityVal = Integer.valueOf(childQuantity.getText().toString()) - 1;
        childQuantity.setText(childQuantityVal >= 0 ? String.valueOf(childQuantityVal) : String.valueOf(0));
    }

    //SENIOR
    @OnClick(R.id.senior_up_btn)
    public void onSeniorUpClick(View v){
        seniorQuantityVal = Integer.valueOf(seniorQuantity.getText().toString()) + 1;
        seniorQuantity.setText(String.valueOf(seniorQuantityVal));
    }

    @OnClick(R.id.senior_down_btn)
    public void onSeniorDownClick(View v){
        seniorQuantityVal = Integer.valueOf(seniorQuantity.getText().toString()) - 1;
        seniorQuantity.setText(seniorQuantityVal >= 0 ? String.valueOf(seniorQuantityVal) : String.valueOf(0));
    }
}
