package com.sawah.signalrtest.common.customView.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.core.graphics.ColorUtils;

/**
 * Created by Turk
 * on 3/13/2018.
 */
public class DialogUtils {
    public DialogUtils() {
    }

    private static boolean isColorLight(@ColorInt int color) {
        if (color == -16777216) {
            return false;
        } else if (color != -1 && color != 0) {
            double darkness = 1.0D - (0.299D * (double) Color.red(color) + 0.587D * (double) Color.green(color) + 0.114D * (double) Color.blue(color)) / 255.0D;
            return darkness < 0.4D;
        } else {
            return true;
        }
    }

    static int buttonTextColor(@ColorInt int color) {
        return isColorLight(color) ? -16777216 : -1;
    }

    static int getThemeAccentColor(Context context) {
        int colorAttr;
        if (VERSION.SDK_INT >= 21) {
            colorAttr = 16843829;
        } else {
            colorAttr = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        }

        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

    @SuppressWarnings("deprecation,unused")
    static void setButton(@ColorInt int bgColor, @ColorInt int color, Button button, boolean colored) {
        if (!colored) {
            if (bgColor != -1) {
                color = bgColor;
            } else {
                color = Color.parseColor("#ffffff");
            }
        }

        int selectedColor = isColorLight(color) ? ColorUtils.blendARGB(color, Color.parseColor("#000000"), 0.15F) : ColorUtils.blendARGB(color, Color.parseColor("#FFFFFF"), 0.2F);
        GradientDrawable drawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{selectedColor, selectedColor});
        GradientDrawable drawable2 = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{color, color});
        drawable.setCornerRadius((float) dpToPx());
        drawable2.setCornerRadius((float) dpToPx());
        StateListDrawable button1bg = new StateListDrawable();
        button1bg.addState(new int[]{16842919}, drawable);
        button1bg.addState(new int[0], drawable2);
        button1bg.setExitFadeDuration(250);
        button.setBackgroundDrawable(button1bg);
    }

    @SuppressWarnings("deprecation")
    static void setButton(@ColorInt int bgColor, @ColorInt int color, TextView button, boolean colored) {
        if (!colored) {
            if (bgColor != -1) {
                color = bgColor;
            } else {
                color = Color.parseColor("#ffffff");
            }
        }

        int selectedColor = isColorLight(color) ? ColorUtils.blendARGB(color, Color.parseColor("#000000"), 0.15F) : ColorUtils.blendARGB(color, Color.parseColor("#FFFFFF"), 0.2F);
        GradientDrawable drawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{selectedColor, selectedColor});
        GradientDrawable drawable2 = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{color, color});
        drawable.setCornerRadius((float) dpToPx());
        drawable2.setCornerRadius((float) dpToPx());
        StateListDrawable button1bg = new StateListDrawable();
        button1bg.addState(new int[]{16842919}, drawable);
        button1bg.addState(new int[0], drawable2);
        button1bg.setExitFadeDuration(250);
        button.setBackgroundDrawable(button1bg);
        //button.setSingleLine(true);
    }

    static int getTextColor(int color) {
        return isColorLight(color) ? Color.parseColor("#DE000000") : Color.parseColor("#FFFFFFFF");
    }

    @SuppressWarnings("unused")
    static int getTextColorSec(int color) {
        return isColorLight(color) ? Color.parseColor("#8A000000") : Color.parseColor("#B3FFFFFF");
    }

    static int getThemeBgColor(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(16842836, typedValue, true) ? typedValue.data : -1;
    }

    private static int dpToPx() {
        return (int) ((float) 2 * Resources.getSystem().getDisplayMetrics().density);
    }
}
