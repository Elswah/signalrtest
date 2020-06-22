package com.sawah.signalrtest.common.snackbar;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sawah.vehicledashboardapp.R;


/**
 * Created by Turk
 * On 3/13/2018.
 * Provides a method to showToolbar a Snackbar.
 */
public class SnackbarUtils {


    public static void showSnackbar(View v, SnackBarData snackBarData, Context context) {
        try {
            if (context == null || v == null || snackBarData == null) {
                return;
            }

            switch (snackBarData.getMessageType()) {


                case SnackBarData.ERROR:
                    showErrorSnackbarWithIcon(v, snackBarData.getMessage(), context, Snackbar.LENGTH_SHORT);
                    break;
                case SnackBarData.SUCCESS:
                    showSuccesSnackbarWithIcon(v, snackBarData.getMessage(), context, Snackbar.LENGTH_SHORT);
                    break;
                case SnackBarData.SUCCESS_SMALL:
                    showSuccesSnackbarWithIcon(v, snackBarData.getMessage(), context, Snackbar.LENGTH_SHORT);
                    break;
                case SnackBarData.INFO:
                    showInfoSnackbarWithIcon(v, snackBarData.getMessage(), context, Snackbar.LENGTH_SHORT);
                    break;


            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private static void showInfoSnackbarWithIcon(View v, String snackbarText, Context context, int duration) {
        if (v == null || snackbarText == null) {
            return;
        }
        Snackbar snackbar = Snackbar.make(v, snackbarText, duration);
        View snackbarLayout = snackbar.getView();
        snackbarLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        TextView textView = snackbarLayout.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setGravity(Gravity.CENTER_VERTICAL);
       /* textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.info_icon, 0, 0, 0);
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.icon_padding));*/
        snackbar.show();
    }

    private static void showSuccesSnackbarWithIcon(View v, String snackbarText, Context context, int duration) {
        if (v == null || snackbarText == null) {
            return;
        }
        Snackbar snackbar = Snackbar.make(v, snackbarText, duration);
        View snackbarLayout = snackbar.getView();
        snackbarLayout.setBackgroundColor(context.getResources().getColor(R.color.snack_bar_success_color));
        TextView textView = snackbarLayout.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        /*textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_cardnumber, 0, 0, 0);
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.icon_padding));*/
        snackbar.show();
    }


    private static void showErrorSnackbarWithIcon(View v, String snackbarText, Context context, int duration) {
        if (v == null || snackbarText == null) {
            return;
        }
        Snackbar snackbar = Snackbar.make(v, snackbarText, duration);
        View snackbarLayout = snackbar.getView();
        snackbarLayout.setBackgroundColor(context.getResources().getColor(R.color.snack_bar_error_color));
        TextView textView = snackbarLayout.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setGravity(Gravity.CENTER_VERTICAL);
       /* textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_cardnumber, 0, 0, 0);
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.icon_padding));*/
        snackbar.show();
    }

}
