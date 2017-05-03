package com.juborajsarker.agecalculator;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeCalculatorFragment extends Fragment {


    InterstitialAd mInterstitialAd;


    AgeCalculation age;


    EditText et;
    Spinner getSelectedDate, getSelectedMonth;

    String currentDate = "";
    String dateValue, monthValue, yearValue;
    int currentDateValue, currentMonthValue, currentYearValue;
    int getDate, getMonth, getYear;
    Boolean leapYear = false;

    long differenceDates;
    String dayDifference = "";

    String subLeapYear;

    Date dd;

    int futureDate = 0;
    int futureMonth = 0;
    int futureYear = 0;


    public AgeCalculatorFragment() {


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age_calculator, container, false);


        et = (EditText) view.findViewById(R.id.edit_text);
        getSelectedDate = (Spinner) view.findViewById(R.id.select_dates);
        getSelectedMonth = (Spinner) view.findViewById(R.id.select_months);

        view.findViewById(R.id.button_b).setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                mInterstitialAd = new InterstitialAd(getContext());
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen1));

                AdRequest adRequest = new AdRequest.Builder().addTestDevice("2BA46C54FD47FD80CBBAD95AE0F70E1A").build(); //add test device
                mInterstitialAd.loadAd(adRequest);

                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        showInterstitial();
                    }
                });


                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat dateFormatOnlyDate = new SimpleDateFormat("dd");
                SimpleDateFormat dateFormatOnlyMonth = new SimpleDateFormat("MM");
                SimpleDateFormat dateFormatOnlyYear = new SimpleDateFormat("yyyy");
                currentDate = dateFormat.format(c.getTime());


                dateValue = dateFormatOnlyDate.format(c.getTime());
                monthValue = dateFormatOnlyMonth.format(c.getTime());
                yearValue = dateFormatOnlyYear.format(c.getTime());

                currentDateValue = Integer.valueOf(dateValue);
                currentMonthValue = Integer.valueOf(monthValue);
                currentYearValue = Integer.valueOf(yearValue);


                if (et.getText().toString().length() > 0) {

                    subLeapYear = et.getText().toString().substring(2);
                }


                getMonth = getSelectedMonth.getSelectedItemPosition();
                getDate = getSelectedDate.getSelectedItemPosition();


                if ((et.getText().toString().equals("")) ||
                        (getMonth == 0) ||
                        (getDate == 0) || (et.getText().toString().length() < 4)) {

                    Toast.makeText(getContext(), "Invalid date of birth", Toast.LENGTH_SHORT).show();

                } else {

                    getYear = Integer.valueOf(et.getText().toString());

                    getMonth = getSelectedMonth.getSelectedItemPosition();
                    getDate = getSelectedDate.getSelectedItemPosition();

                    if (subLeapYear.equals("00")) {

                        if ((getYear % 400) == 0) {

                            leapYear = true;

                        } else {

                            leapYear = false;
                        }

                    } else {

                        if ((getYear % 4) == 0) {

                            leapYear = true;

                        } else {

                            leapYear = false;

                        }
                    }


                    if (getYear > currentYearValue) {

                        Toast.makeText(getContext(), "You Cant born on future.", Toast.LENGTH_SHORT).show();


                    } else if (getYear >= currentYearValue && getMonth > currentMonthValue) {

                        Toast.makeText(getContext(), "You Cant born on future.", Toast.LENGTH_SHORT).show();

                    } else if (getYear >= currentYearValue && getMonth >= currentMonthValue && getDate > currentDateValue) {

                        Toast.makeText(getContext(), "You Cant born on future.", Toast.LENGTH_SHORT).show();

                    } else {


                        if (getDate == 29 && getMonth == 2 && leapYear == false) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();

                        } else if (getDate == 30 && getMonth == 2) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();

                        } else if (getDate == 31 && getMonth == 2) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();

                        } else if (getDate == 31 && getMonth == 4) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();


                        } else if (getDate == 31 && getMonth == 6) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();


                        } else if (getDate == 31 && getMonth == 9) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();


                        } else if (getDate == 31 && getMonth == 11) {

                            Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();


                        } else {


                            Calendar date1 = Calendar.getInstance();
                            Calendar date2 = Calendar.getInstance();

                            date1.clear();
                            date1.set(getYear, getMonth, getDate); // set date 1 (yyyy,mm,dd)
                            date2.clear();
                            date2.set(currentYearValue, currentMonthValue, currentDateValue); //set date 2 (yyyy,mm,dd)

                            long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

                            float dayCount = (float) diff / (24 * 60 * 60 * 1000);

                            int finalDayCount = (int) dayCount;

                            int weeks = (int) (dayCount / 7);


                            age = new AgeCalculation();

                            age.getCurrentDate();
                            age.setDateOfBirth(getYear, getMonth, getDate);
                            String dob = age.dob(getYear, getMonth, getDate);
                            final String strBDay = String.valueOf(getYear) + "/" + String.valueOf(getMonth) + "/" + String.valueOf(getDate);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            Date dt = null;
                            try {
                                dt = sdf.parse(strBDay);
                            } catch (final java.text.ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            final Calendar BDay = Calendar.getInstance();
                            BDay.setTime(dt);

                            final Calendar today = Calendar.getInstance();


                            final int BMonth = BDay.get(Calendar.MONTH);
                            final int CMonth = today.get(Calendar.MONTH);
                            BDay.set(Calendar.YEAR, today.get(Calendar.YEAR));
                            if (BMonth < CMonth) {
                                BDay.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);

                            } else if ((BMonth == CMonth) && (getDate < currentDateValue)) {

                                BDay.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
                            }

                            final long millis = BDay.getTimeInMillis() - today.getTimeInMillis();

                            // Convert to days
                            final long daysLeft = millis / 86400000; //  (24 * 60 * 60 * 1000)

                            sdf = new SimpleDateFormat("EEEE");

                            final String dayOfTheWeek = sdf.format(dt);


                            int date_result = 0;
                            int month_result = 0;
                            int year_result = 0;

                            int carry_date = 0;
                            int carry_month = 0;


                            int to_date_value = currentDateValue;
                            int to_month_value = currentMonthValue;
                            int to_year_value = currentYearValue;


                            int from_date_value = getDate;
                            int from_month_value = getMonth;
                            int from_year_value = getYear;


                            if (to_date_value < from_date_value) {

                                if ((currentMonthValue == 1) || (currentMonthValue == 3) ||
                                        (currentMonthValue == 5) || (currentMonthValue == 7) ||
                                        (currentMonthValue == 8) || (currentMonthValue == 10) ||
                                        (currentMonthValue == 12)) {


                                    to_date_value = to_date_value + 31;
                                    carry_date++;

                                } else if ((currentMonthValue == 4) || (currentMonthValue == 6) ||
                                        (currentMonthValue == 9) || (currentMonthValue == 11)) {

                                    to_date_value = to_date_value + 30;
                                    carry_date++;

                                } else if (currentMonthValue == 2 && leapYear == false) {

                                    to_date_value = to_date_value + 28;
                                    carry_date++;

                                } else if (currentMonthValue == 2 && leapYear == true) {

                                    to_date_value = to_date_value + 29;
                                    carry_date++;
                                }
                            }


                            date_result = to_date_value - from_date_value;


                            if (carry_date == 1) {

                                from_month_value++;

                            }

                            if (to_month_value < from_month_value) {


                                to_month_value = to_month_value + 12;
                                carry_month++;

                            }


                            month_result = to_month_value - from_month_value;


                            if (carry_month == 1) {

                                from_year_value++;

                            }

                            year_result = to_year_value - from_year_value;


                            futureDate = 0;
                            futureMonth = 0;
                            futureYear = 0;


                            if (currentMonthValue < getMonth) {

                                futureDate = getDate;
                                futureMonth = getMonth;
                                futureYear = currentYearValue;

                            } else if ((currentMonthValue == getMonth) && (currentDateValue < getDate)) {

                                futureDate = getDate;
                                futureMonth = getMonth;
                                futureYear = currentYearValue;

                            } else if ((currentMonthValue == getMonth) && (currentDateValue >= getDate)) {

                                futureDate = getDate;
                                futureMonth = getMonth;
                                futureYear = currentYearValue + 1;

                            } else if (currentMonthValue > getMonth) {

                                futureDate = getDate;
                                futureMonth = getMonth;
                                futureYear = currentYearValue + 1;

                            }


                            try {

                                //Dates to compare
                                String CurrentDate = String.valueOf(currentMonthValue) + "/" + String.valueOf(currentDateValue) + "/" +
                                        String.valueOf(currentYearValue);

                                String FinalDate = String.valueOf(futureMonth) + "/" + String.valueOf(futureDate) + "/" +
                                        String.valueOf(futureYear);

                                Date date1_new;
                                Date date2_new;

                                SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

                                date1_new = dates.parse(CurrentDate);
                                date2_new = dates.parse(FinalDate);


                                long difference = Math.abs(date1_new.getTime() - date2_new.getTime());
                                differenceDates = difference / (24 * 60 * 60 * 1000);


                                Log.e("HERE", "HERE: " + dayDifference);


                            } catch (Exception exception) {
                                Log.e("DIDN'T WORK", "exception " + exception);
                            }


                            String futureDates = String.valueOf(futureDate) + "/" + String.valueOf(futureMonth) + "/" +
                                    String.valueOf(futureYear);

                            SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                dd = formats.parse(futureDates);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            SimpleDateFormat week_of_dates = new SimpleDateFormat("EEEE");

                            String dayOfTheWeeks = week_of_dates.format(dd);


                            Intent intent = new Intent(getContext(), ResultActivity.class);

                            intent.putExtra("yearValue", String.valueOf(year_result));
                            intent.putExtra("monthValue", String.valueOf(month_result));
                            intent.putExtra("dateValue", String.valueOf(date_result));
                            intent.putExtra("weeksValue", String.valueOf(weeks));
                            intent.putExtra("daysValue", String.valueOf(finalDayCount));
                            intent.putExtra("daysLeft", String.valueOf(differenceDates));
                            intent.putExtra("dayOfTheWeek", String.valueOf(dayOfTheWeeks));

                            intent.putExtra("dob", dob);

                            differenceDates = 0;
                            futureDate = 0;
                            futureMonth = 0;
                            futureYear = 0;


                            startActivity(intent);


                        }

                    }

                }


            }
        });


        return view;


    }


    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}
