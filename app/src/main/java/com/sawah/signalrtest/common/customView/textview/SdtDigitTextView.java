package com.sawah.signalrtest.common.customView.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.sawah.signalrtest.utils.FontUtils;

public class SdtDigitTextView extends AppCompatTextView {

    private static final String TAG = SdtDigitTextView.class.getName();

    public SdtDigitTextView(Context context) {
        super(context);
        init(context);
    }

    public SdtDigitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SdtDigitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
/*
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CcsRegularTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        initLocationManager(context);
    }*/

    public void init(Context context) {

        if (isInEditMode()) {

            return;
        }
        try {
            Typeface myFont = FontUtils.getInstance(context).getFont(FontUtils.KEY_DIGIT);
            setTypeface(myFont);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}