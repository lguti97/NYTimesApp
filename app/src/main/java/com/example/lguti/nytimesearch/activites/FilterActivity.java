package com.example.lguti.nytimesearch.activites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
    Spinner spinner;
    CheckBox cbEducation;
    CheckBox cbArts;
    CheckBox cbSports;


    public String year_string;
    public String day;
    public String month;
    public String Date;
    public String order;






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
        order = spinner.getSelectedItem().toString();
        //Suppose to update date.
        //Suppose to update order.
        filter.begin_date = Date;
        filter.sort = order;

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
        spinner = (Spinner) findViewById(R.id.spinner);

        cbArts = (CheckBox) findViewById(R.id.cbArts);
        cbEducation = (CheckBox) findViewById(R.id.cbEducation);
        cbSports = (CheckBox) findViewById(R.id.cbSports);



        cbArts.setOnCheckedChangeListener(checkListener);
        cbEducation.setOnCheckedChangeListener(checkListener);
        cbSports.setOnCheckedChangeListener(checkListener);




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
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        year_string = Integer.toString(year);
        day = Integer.toString(dayOfMonth);
        month = Integer.toString(monthOfYear + 1);

        if (day.length() == 1){
            day = "0" + day;
        }

        if (month.length() == 1){
            month = "0" + month;
        }

        Date = year_string + month + day;
        etDate.setText(Date);
        Toast.makeText(this, Date, Toast.LENGTH_SHORT).show();

    }



    //THIS IS FOR THE CHECKBOXES

    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton view, boolean checked) {
            // compoundButton is the checkbox
            // boolean is whether or not checkbox is checked
            // Check which checkbox was clicked
            switch(view.getId()) {
                case R.id.cbEducation:
                    if (checked)
                    filter.news_desk = cbEducation.getText().toString();
                    else

                    break;
                case R.id.cbArts:
                    if (checked)
                    filter.news_desk = cbArts.getText().toString();
                    else

                    break;
                case R.id.cbSports:
                    if (checked)
                    filter.news_desk = cbSports.getText().toString();
                    else

                    break;
            }
        }
    };










}
