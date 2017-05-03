package com.juborajsarker.agecalculator;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jubor on 5/1/2017.
 */

public class ViewDialog {

    public void showDialog(Activity activity, String msg, String from_date_txt,
                           String to_date_txt, String only_date_txt, String only_weeks_txt,
                           String only_months_txt, String only_years_txt,    String full_difference ){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView default_text = (TextView) dialog.findViewById(R.id.text_dialog);
        TextView from_date_text = (TextView) dialog.findViewById(R.id.from_date_text);
        TextView to_date_text = (TextView) dialog.findViewById(R.id.to_date_text);
        TextView only_days_text = (TextView) dialog.findViewById(R.id.only_days_text);
        TextView only_weeks_text = (TextView) dialog.findViewById(R.id.only_weeks_text);
        TextView only_months_text = (TextView) dialog.findViewById(R.id.only_months_text);
        TextView only_years_text = (TextView) dialog.findViewById(R.id.only_years_text);
        TextView full_difference_text = (TextView) dialog.findViewById(R.id.full_difference_text);


        default_text.setText(msg);
        from_date_text.setText(from_date_txt);
        to_date_text.setText(to_date_txt);
        only_days_text.setText(only_date_txt);
        only_weeks_text.setText(only_weeks_txt);
        only_months_text.setText(only_months_txt);
        only_years_text.setText(only_years_txt);
        full_difference_text.setText(full_difference);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}