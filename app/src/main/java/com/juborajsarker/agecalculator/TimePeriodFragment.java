package com.juborajsarker.agecalculator;


import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimePeriodFragment extends Fragment {


    InterstitialAd mInterstitialAd;


    EditText select_from_year, select_to_year;
    Spinner select_from_date, select_from_month, select_to_date, select_to_month;

    int get_from_date, get_from_month, get_from_year;
    int get_to_date, get_to_month, get_to_year;

    String dayDifference = "";
    long differenceDates;
    Boolean leapYear = false;
    String from_sub_leap_year, to_sub_leap_year;


    public TimePeriodFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_period, container, false);

        select_from_year = (EditText) view.findViewById(R.id.select_from_year);
        select_to_year = (EditText) view.findViewById(R.id.select_to_year);

        select_from_date = (Spinner) view.findViewById(R.id.select_from_date);
        select_from_month = (Spinner) view.findViewById(R.id.select_from_month);

        select_to_date = (Spinner) view.findViewById(R.id.select_to_date);
        select_to_month = (Spinner) view.findViewById(R.id.select_to_month);

        view.findViewById(R.id.button_calculate).setOnClickListener(new View.OnClickListener() {
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











                get_from_date = select_from_date.getSelectedItemPosition();
                get_from_month = select_from_month.getSelectedItemPosition();

                get_to_date = select_to_date.getSelectedItemPosition();
                get_to_month = select_to_month.getSelectedItemPosition();

                if (get_from_date == 0 || get_from_month == 0 || get_to_date == 0 || get_to_month == 0 ||
                        select_from_year.getText().toString().equals("") || select_to_year.getText().toString().equals("") ||
                        (select_from_year.getText().toString().length()<4) || (select_to_year.getText().toString().length()<4))  {


                    Toast.makeText(getContext(), "Invalid date", Toast.LENGTH_SHORT).show();


                } else {


                    get_from_year = Integer.parseInt(select_from_year.getText().toString());
                    get_to_year = Integer.parseInt(select_to_year.getText().toString());


                    if (get_from_year > get_to_year) {

                        Toast.makeText(getContext(), "Invalid time period", Toast.LENGTH_SHORT).show();

                    } else if ((get_from_year >= get_to_year) && (get_from_month > get_to_month)) {


                        Toast.makeText(getContext(), "Invalid time period", Toast.LENGTH_SHORT).show();

                    } else if ((get_from_year >= get_to_year) && (get_from_month >= get_to_month) && (get_from_date > get_to_date)) {


                        Toast.makeText(getContext(), "Invalid time period", Toast.LENGTH_SHORT).show();

                    } else {


                        from_sub_leap_year = select_from_year.getText().toString().substring(2);
                        to_sub_leap_year = select_to_year.getText().toString().substring(2);

                        if (from_sub_leap_year.equals("00")) {

                            if ((get_from_year % 400) == 0) {

                                leapYear = true;

                            } else {

                                leapYear = false;
                            }

                        } else {

                            if ((get_from_year % 4) == 0) {

                                leapYear = true;

                            } else {

                                leapYear = false;
                            }

                        }


                        if (to_sub_leap_year.equals("00")) {

                            if ((get_to_year % 400) == 0) {

                                leapYear = true;

                            } else {

                                leapYear = false;
                            }

                        } else {

                            if ((get_to_year % 4) == 0) {

                                leapYear = true;

                            } else {

                                leapYear = false;
                            }

                        }


                        if ((get_from_date == 29 && get_from_month == 2 && leapYear == false) ||
                                (get_to_date == 29 && get_to_month == 2 && leapYear == false)) {

                            Toast.makeText(getContext(), "Invalid date. February month cannot be 29", Toast.LENGTH_SHORT).show();

                        } else if ((get_from_date == 30 && get_from_month == 2) ||
                                (get_to_date == 30 && get_to_month == 2)) {

                            Toast.makeText(getContext(), "Invalid date. February month cannot be 30", Toast.LENGTH_SHORT).show();

                        } else if ((get_from_date == 31 && get_from_month == 2) ||
                                (get_to_date == 31 && get_to_month == 2)) {

                            Toast.makeText(getContext(), "Invalid date. February month cannot be 31", Toast.LENGTH_SHORT).show();

                        } else if ((get_from_date == 31 && get_from_month == 4) ||
                                (get_to_date == 31 && get_to_month == 4)) {

                            Toast.makeText(getContext(), "Invalid date. April month cannot be 31", Toast.LENGTH_SHORT).show();

                        } else if ((get_from_date == 31 && get_from_month == 6) ||
                                (get_to_date == 31 && get_to_month == 6)) {

                            Toast.makeText(getContext(), "Invalid date. June month cannot be 31", Toast.LENGTH_SHORT).show();

                        } else if ((get_from_date == 31 && get_from_month == 9) ||
                                (get_to_date == 31 && get_to_month == 9)) {

                            Toast.makeText(getContext(), "Invalid date. September month cannot be 31", Toast.LENGTH_SHORT).show();

                        } else if ((get_from_date == 31 && get_from_month == 11) ||
                                (get_to_date == 31 && get_to_month == 11)) {

                            Toast.makeText(getContext(), "Invalid date. November month cannot be 31", Toast.LENGTH_SHORT).show();

                        } else {


                            try {

                                //Dates to compare
                                String CurrentDate = String.valueOf(get_from_month) + "/" + String.valueOf(get_from_date) + "/" +
                                        String.valueOf(get_from_year);

                                String FinalDate = String.valueOf(get_to_month) + "/" + String.valueOf(get_to_date) + "/" +
                                        String.valueOf(get_to_year);

                                Date date1;
                                Date date2;

                                SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

                                date1 = dates.parse(CurrentDate);
                                date2 = dates.parse(FinalDate);


                                long difference = Math.abs(date1.getTime() - date2.getTime());
                                differenceDates = difference / (24 * 60 * 60 * 1000);




                                Log.e("HERE", "HERE: " + dayDifference);






                            } catch (Exception exception) {
                                Log.e("DIDN'T WORK", "exception " + exception);
                            }


                            int date_result = 0;
                            int month_result = 0;
                            int year_result = 0;

                            int carry_date = 0;
                            int carry_month = 0;


                            int to_date_value = get_to_date;
                            int to_month_value = get_to_month;
                            int to_year_value = get_to_year;


                            int from_date_value = get_from_date;
                            int from_month_value = get_from_month;
                            int from_year_value = get_from_year;


                            if (to_date_value < from_date_value) {

                                if ((get_to_month == 1) || (get_to_month == 3) ||
                                        (get_to_month == 5) || (get_to_month == 7) ||
                                        (get_to_month == 8) || (get_to_month == 10) ||
                                        (get_to_month == 12)) {


                                    to_date_value = to_date_value + 31;
                                    carry_date++;

                                } else if ((get_to_month == 4) || (get_to_month == 6) ||
                                        (get_to_month == 9) || (get_to_month == 11)) {

                                    to_date_value = to_date_value + 30;
                                    carry_date++;

                                } else if (get_to_month == 2 && leapYear == false) {

                                    to_date_value = to_date_value + 28;
                                    carry_date++;

                                } else if (get_to_month == 2 && leapYear == true) {

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


                            int differentAllDates = 0;
                            differentAllDates = (int) differenceDates;
                            int differentAllMonths = differentAllDates / 30;
                            int differentAllWeeks = differentAllDates / 7;
                            int differentAllYears = differentAllDates / 365;






                            String fromDates = String.valueOf(get_from_date) + "/" + get_from_month + "/" + get_from_year;
                            String toDates = String.valueOf(get_to_date) + "/" + get_to_month + "/" + get_to_year;
                            String onlyDatesValues = String.valueOf(differenceDates);
                            String onlyMonthsValues = String.valueOf( differentAllMonths );
                            String onlyWeeksValues = String.valueOf( differentAllWeeks );
                            String onlyYearsValues = String.valueOf( differentAllYears );
                            String fullDifference = String.valueOf(year_result)+ " years, "+ String.valueOf(month_result) + " months, "+ String.valueOf(date_result) + " days";


                            ViewDialog alert = new ViewDialog();
                            alert.showDialog(getActivity(), "Press OK to continue",
                                    fromDates, toDates, onlyDatesValues, onlyWeeksValues,
                                    onlyMonthsValues, onlyYearsValues, fullDifference );
                            differenceDates = 0;


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
