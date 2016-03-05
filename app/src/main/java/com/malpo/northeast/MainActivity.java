package com.malpo.northeast;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.metova.slim.annotation.Layout;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Cal Instances / Date
        calendar = Calendar.getInstance();
        dateFormatSymbols = DateFormatSymbols.getInstance();
        fromYear = toYear = calendar.get(Calendar.YEAR);
        fromMonth = toYear = calendar.get(Calendar.MONTH);
        fromDay = toYear = calendar.get(Calendar.DAY_OF_MONTH);

        //Set default from / to date text;
        String fromDateDefaultText = dateFormatSymbols.getShortMonths()[fromMonth] + " " + fromDay;
        fromDateText.setText(fromDateDefaultText);
        toDateText.setText("?");
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
                fromText.setText("BNA");
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
        int val = Integer.valueOf(adultQuantity.getText().toString()) + 1;
        adultQuantity.setText(String.valueOf(val));
    }

    @OnClick(R.id.adult_down_btn)
    public void onAdultDownClick(View v){
        int val = Integer.valueOf(adultQuantity.getText().toString()) - 1;
        adultQuantity.setText(val >= 0 ? String.valueOf(val) : String.valueOf(0));
    }

    //CHILD
    @OnClick(R.id.child_up_btn)
    public void onChildUpClick(View v){
        int val = Integer.valueOf(childQuantity.getText().toString()) + 1;
        childQuantity.setText(String.valueOf(val));
    }

    @OnClick(R.id.child_down_btn)
    public void onChildDownClick(View v){
        int val = Integer.valueOf(childQuantity.getText().toString()) - 1;
        childQuantity.setText(val >= 0 ? String.valueOf(val) : String.valueOf(0));
    }

    //SENIOR
    @OnClick(R.id.senior_up_btn)
    public void onSeniorUpClick(View v){
        int val = Integer.valueOf(seniorQuantity.getText().toString()) + 1;
        seniorQuantity.setText(String.valueOf(val));
    }

    @OnClick(R.id.senior_down_btn)
    public void onSeniorDownClick(View v){
        int val = Integer.valueOf(seniorQuantity.getText().toString()) - 1;
        seniorQuantity.setText(val >= 0 ? String.valueOf(val) : String.valueOf(0));
    }
}
