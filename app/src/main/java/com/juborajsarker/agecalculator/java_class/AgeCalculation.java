package com.juborajsarker.agecalculator.java_class;

import java.util.Calendar;

/**
 * Created by jubor on 4/20/2017.
 */

public class AgeCalculation {


    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int resYear;
    private int resMonth;
    private int resDay;
    private Calendar start;
    private Calendar end;

    public String getCurrentDate() {
        end = Calendar.getInstance();
        endYear = end.get(Calendar.YEAR);
        endMonth = end.get(Calendar.MONTH);
        endMonth++;
        endDay = end.get(Calendar.DAY_OF_MONTH);
        return endDay + ":" + endMonth + ":" + endYear;
    }

    public void setDateOfBirth(int sYear, int sMonth, int sDay) {
        startYear = sYear;
        startMonth = sMonth;
        startDay = sDay;

    }

    public int calcualteYear() {
        resYear = endYear - startYear;

        if (endMonth < startMonth){

            resYear --;

        }


        return resYear;

    }

    public int calcualteMonth() {
        if (endMonth >= startMonth) {
            resMonth = endMonth - startMonth;
        } else {
            resMonth = endMonth - startMonth;
            resMonth = 12 + resMonth;
            resYear--;
        }

        return resMonth;
    }

    public int calcualteDay() {

        if (endDay >= startDay) {
            resDay = endDay - startDay;
        } else {
            resDay = endDay - startDay;
            resDay = 30 + resDay;
            if (resMonth == 0) {
                resMonth = 11;
                resYear--;
            } else {
                resMonth--;
            }

        }

        return resDay;
    }

    public String getResult() {
        return resDay + ":" + resMonth + ":" + resYear;
    }


    public String dob (int sYear, int sMonth, int sDay) {
        startYear = sYear;
        startMonth = sMonth;
        startDay = sDay;

        return  startDay + "/" + startMonth + "/" + startYear;

    }





}