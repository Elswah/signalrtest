package com.sawah.signalrtest.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontUtils {

    public static final String KEY_DIGIT = "digit";
    public static final String KEY_AVNIER_BOLD = "avnierBold";
    public static final String KEY_SEMI_BOLD = "semibold";
    public static final String KEY_BOLD = "bold";
    public static FontUtils fontUtils;
    private Hashtable<String, Typeface> fontCache;

    private Context context;

    private FontUtils(Context context) {
        this.context = context;

        fontCache = new Hashtable<>();
        setTypeface(fontCache, KEY_DIGIT, "fonts/digit.ttf", context);


    }

    public static FontUtils getInstance(Context context) {

        if (fontUtils == null) {

            fontUtils = new FontUtils(context);
        } else {

            fontUtils.setContext(context);
        }

        return fontUtils;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTypeface(Hashtable<String, Typeface> fontCache, String fontKey, String fontName, Context context) {
        Typeface tf = fontCache.get(fontName);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            fontCache.put(fontKey, tf);
        }

    }

    public Typeface getFont(String fontKey) {

        /*if(LocaleManager.isRtl(context)) {
            if (arabicFontCache.containsKey(fontKey)) {

                return arabicFontCache.get(fontKey);
            } else
                return null;

        }else{*/

        if (fontCache.containsKey(fontKey)) {

            return fontCache.get(fontKey);
        } else
            return null;
        // }
    }

    public void dispose() {
        fontCache = null;
    }
}