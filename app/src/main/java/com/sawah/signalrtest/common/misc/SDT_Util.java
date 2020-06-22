package com.sawah.signalrtest.common.misc;

import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SDT_Util {
    public static final int CARD_WIDTH_LARGE_DEVICE_PERCENTAGE = 30;
    public static final int CARD_WIDTH_PERCENTAGE = 25;
    public static final int CARD_HEITGHT_PERCENTAGE = 100;
    public static final int CARD_OFFSET_PERCENTAGE = 3;
    public static final int ROUTE_CARD_WIDTH_LARGE_DEVICE_PERCENTAGE = 30;
    public static final int ROUTE_CARD_WIDTH_PERCENTAGE = 25;
    public static final int ROUTE_CARD_HEITGHT_PERCENTAGE = 100;
    public static final int ROUTE_CARD_OFFSET_PERCENTAGE = 3;

    public static void preventTwoClick(final View view) {
        view.setEnabled(false);
        view.postDelayed(() -> view.setEnabled(true), 500);
    }

    public static CardSize getCardSize(AppCompatActivity activity) {

        float cardWidth = 0;
        float cardHeight = 0;
        float spaceOffset = 0;
        float screenWidth = 0;
        float screenHeight = 0;
        float density;


        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        density = displayMetrics.density;
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        screenHeight = height;
        screenWidth = width;
        float widthDp = pxToDp(width, density);

        if (widthDp <= 320) {
            cardWidth = width;

        } else if (widthDp > 500) {

            cardWidth = (width / 100) * CARD_WIDTH_LARGE_DEVICE_PERCENTAGE;


        } else {

            cardWidth = (width / 100) * CARD_WIDTH_PERCENTAGE;
        }

        cardHeight = (cardWidth / 100) * CARD_HEITGHT_PERCENTAGE;

        spaceOffset = (width / 100) * CARD_OFFSET_PERCENTAGE;


        return new CardSize(cardWidth, cardHeight, spaceOffset, screenWidth, screenHeight, density);

    }

    public static CardSize getRouteCardSize(AppCompatActivity activity) {

        float cardWidth = 0;
        float cardHeight = 0;
        float spaceOffset = 0;
        float screenWidth = 0;
        float screenHeight = 0;
        float density;


        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        density = displayMetrics.density;
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        screenHeight = height;
        screenWidth = width;
        float widthDp = pxToDp(width, density);

        if (widthDp <= 320) {
            cardWidth = width;

        } else if (widthDp > 500) {

            cardWidth = (width / 100) * CARD_WIDTH_LARGE_DEVICE_PERCENTAGE;


        } else {

            cardWidth = (width / 100) * CARD_WIDTH_PERCENTAGE;
        }

        cardHeight = (cardWidth / 100) * CARD_HEITGHT_PERCENTAGE;

        spaceOffset = (width / 100) * CARD_OFFSET_PERCENTAGE;


        return new CardSize(cardWidth, cardHeight, spaceOffset, screenWidth, screenHeight, density);

    }

    public static float pxToDp(int somePxValue, float density) {

        return somePxValue / density;
    }


    @SuppressLint("SimpleDateFormat")
    public static String parseDate(String dateTime, String inputPattern, String outputPattern) {

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dateTime);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
