package com.example.lguti.nytimesearch.activites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lguti.nytimesearch.R;
import com.example.lguti.nytimesearch.extra.DatePickerFragment;
import com.example.lguti.nytimesearch.model.SearchFilters;

import java.util.Calendar;

public class FilterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView tvDate;
    EditText etDate;
    Button btnSave;
    SearchFilters filter;

    public String year_string;
    public String day;
    public String month;

    public String Date;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filter = getIntent().getParcelableExtra("filter");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpView();

        etDate.setText(filter.getBegin_date());

    }

    //When save Button is clicked

    public void OnSave(View view) {
        //This is receiving the object!
        String date = etDate.getText().toString();
        //Suppose to update date.
        filter.begin_date = date;
        //Toast.makeText(this, filter.getBegin_date(), Toast.LENGTH_SHORT).show();

        Intent new_data = new Intent();
        new_data.putExtra("filter", filter);
        setResult(RESULT_OK, new_data);
        finish();


    }

    //Setting up the View objects
    public void setUpView(){
        tvDate = (TextView) findViewById(R.id.tvDate);
        etDate = (EditText) findViewById(R.id.etDate);
        btnSave = (Button) findViewById(R.id.btnSave);


    }

    //THIS WILL METHOD WILL BE USED WHEN THE EDIT TEXT IS CLICKED ON
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        //This is the new fragment that would basically be shown.
        newFragment.show(getFragmentManager(), "datePicker");



    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance

        //It's not saving it!
        final Calendar c =  Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear + 1);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        year_string = Integer.toString(year);
        day = Integer.toString(monthOfYear);
        month = Integer.toString(dayOfMonth);

        Date = year_string + "/" + month + "/" + day;
        etDate.setText(Date);
        Toast.makeText(this, Date, Toast.LENGTH_SHORT).show();

    }





}
