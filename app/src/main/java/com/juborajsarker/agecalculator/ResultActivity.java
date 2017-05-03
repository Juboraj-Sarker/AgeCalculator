package com.juborajsarker.agecalculator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    String year, month, date, dob , weeks, days, daysLeft, dayOfTheWeek ;
    int monthValue, dateValue, yearValue, weeksValue;

    private TextView date_of_birth_TV, age_TV, age_In_Years_TV, age_In_Months_TV,
            age_In_Weeks_TV, age_In_Days_TV, next_Birthday_TV, day_of_the_week_TV;





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        date_of_birth_TV = (TextView) findViewById(R.id.date_of_birth_TV);
        age_TV = (TextView) findViewById(R.id.age_TV);
        age_In_Years_TV = (TextView) findViewById(R.id.age_In_Years_TV);
        age_In_Months_TV = (TextView) findViewById(R.id.age_In_Months_TV);
        age_In_Weeks_TV = (TextView) findViewById(R.id.age_In_Weeks_TV);
        age_In_Days_TV = (TextView) findViewById(R.id.age_In_Days_TV);
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


        int months = yearValue * 12 + monthValue;

        date_of_birth_TV.setText(dob + "   (dd/mm/yyyy)");
        age_TV.setText(year);
        age_In_Years_TV.setText(year + " years, " + month + " months, " + date + " days");
        age_In_Months_TV.setText(String.valueOf(months));
        age_In_Weeks_TV.setText(String.valueOf(weeksValue));
        age_In_Days_TV.setText(days);
        next_Birthday_TV.setText("After " + daysLeft + " days");
        day_of_the_week_TV.setText(dayOfTheWeek);










    }


    public void go_back(View view) {

        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}
