package com.juborajsarker.agecalculator.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.juborajsarker.agecalculator.R;

public class ResultActivity extends AppCompatActivity {


    String year, month, date, dob , weeks, days, daysLeft, dayOfTheWeek ;
    int monthValue, dateValue, yearValue, weeksValue, daysValue;



    private TextView date_of_birth_TV, age_TV, age_In_Years_TV, age_In_Months_TV,
            age_In_Weeks_TV, age_In_Days_TV, age_In_Hours_TV, age_In_Miniuts_TV, age_In_Seconds_TV, next_Birthday_TV, day_of_the_week_TV;





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);




        date_of_birth_TV = (TextView) findViewById(R.id.date_of_birth_TV);
        age_TV = (TextView) findViewById(R.id.age_TV);
        age_In_Years_TV = (TextView) findViewById(R.id.age_In_Years_TV);
        age_In_Months_TV = (TextView) findViewById(R.id.age_In_Months_TV);
        age_In_Weeks_TV = (TextView) findViewById(R.id.age_In_Weeks_TV);
        age_In_Days_TV = (TextView) findViewById(R.id.age_In_Days_TV);

        age_In_Hours_TV = (TextView) findViewById(R.id.age_In_Hours_TV);
        age_In_Miniuts_TV = (TextView) findViewById(R.id.age_In_Miniuts_TV);
        age_In_Seconds_TV = (TextView) findViewById(R.id.age_In_Seconds_TV);

        next_Birthday_TV = (TextView) findViewById(R.id.next_Birthday_TV);
        day_of_the_week_TV = (TextView) findViewById(R.id.day_of_the_week_TV);




        Intent intent = getIntent();

        year = intent.getStringExtra("yearValue");
        month = intent.getStringExtra("monthValue");
        date = intent.getStringExtra("dateValue");
        weeks = intent.getStringExtra("weeksValue") ;
        days = intent.getStringExtra("daysValue") ;
        dob = intent.getStringExtra("dob");
        daysLeft = intent.getStringExtra("daysLeft");
        dayOfTheWeek = intent.getStringExtra("dayOfTheWeek");

        dateValue = Integer.parseInt(date);
        monthValue = Integer.parseInt(month);
        yearValue = Integer.parseInt(year);
        weeksValue = Integer.parseInt(weeks);
        daysValue = Integer.parseInt(days);


        int months = yearValue * 12 + monthValue;

        date_of_birth_TV.setText(dob + "   (dd/mm/yyyy)");
        age_TV.setText(year);
        age_In_Years_TV.setText(year + " years, " + month + " months, " + date + " days");
        age_In_Months_TV.setText(String.valueOf(months));
        age_In_Weeks_TV.setText(String.valueOf(weeksValue));
        age_In_Days_TV.setText(days);

        int ageInHours = daysValue * 24;
        int ageInMiniuts = daysValue * 1440;
        int ageInSeconds = daysValue * 86400;

        age_In_Hours_TV.setText(String.valueOf(ageInHours));
        age_In_Miniuts_TV.setText(String.valueOf(ageInMiniuts));
        age_In_Seconds_TV.setText(String.valueOf(ageInSeconds));

        next_Birthday_TV.setText("After " + daysLeft + " days");
        day_of_the_week_TV.setText(dayOfTheWeek);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }





}
