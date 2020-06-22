package com.sawah.signalrtest.common.customView.dialog;

/**
 * Created by Turk
 * on 3/13/2018.
 */


/*
public class DialogSheet {
    private Context context;
    private MyDialog dialog;
    private int buttonColor = -1;
    private int backgroundColor = -1;
    private boolean showButtons = false;
    private TextView titleTextView;
    private TextView messageTextView;
    private ImageView iconImageView;
    private AutoResizeTextView positiveButton;
    private AutoResizeTextView negativeButton;
    private RelativeLayout textContainer;
    private LinearLayout messageContainer;
    private FrameLayout mainDialogContainer;
    private View inflatedView;

    public DialogSheet(Context context) {
        this.context = context;
        this.init(context);
    }

    public MyDialog getDialog() {
        return dialog;
    }

    public DialogSheet setTitle(CharSequence title) {
        this.titleTextView.setVisibility(View.VISIBLE);
        this.titleTextView.setText(title);
        return this;
    }


    public boolean isShowing() {

        if (dialog != null) {
            return dialog.isShowing();
        } else {

            return false;
        }
    }

    public DialogSheet setTitle(@StringRes int titleRes) {
        this.setTitle(this.context.getResources().getString(titleRes));
        return this;
    }

    public DialogSheet setMessage(CharSequence message) {
        this.messageTextView.setVisibility(View.VISIBLE);
        this.messageTextView.setText(message);
        return this;
    }

    public DialogSheet setMessage(@StringRes int messageRes) {
        this.setMessage(this.context.getResources().getString(messageRes));
        return this;
    }

    public DialogSheet setIcon(Drawable icon) {
        this.showIcon();
        this.iconImageView.setImageDrawable(icon);
        return this;
    }

    public DialogSheet setIcon(Bitmap icon) {
        this.showIcon();
        this.iconImageView.setImageBitmap(icon);
        return this;
    }

    public DialogSheet setIcon(@DrawableRes int iconRes) {
        this.showIcon();
        this.iconImageView.setImageResource(iconRes);
        return this;
    }

    private DialogSheet setPositiveButton(CharSequence text, final OnPositiveClickListener onPositiveClickListener) {
        this.positiveButton.setVisibility(View.VISIBLE);
        this.positiveButton.setText(text);
        this.positiveButton.setOnClickListener(view -> {

            DialogSheet.this.dialog.dismiss();
            if (onPositiveClickListener != null) {
                onPositiveClickListener.onClick(view);
            }

        });
        this.showButtons = true;
        return this;
    }

    private DialogSheet setPositiveButton(CharSequence text, final OnPositiveClickListenerNotDissmisable onPositiveClickListener) {
        this.positiveButton.setVisibility(View.VISIBLE);
        this.positiveButton.setText(text);
        this.positiveButton.setOnClickListener(view -> {


            if (onPositiveClickListener != null) {
                onPositiveClickListener.onClick(view);
            }


        });
        this.showButtons = true;
        return this;
    }

    public DialogSheet setCancelListener(MyOnCancelListener listener) {

        dialog.setOnCancelListener(listener);
        return this;
    }

    private DialogSheet setNegativeButton(CharSequence text, final OnNegativeClickListener onNegativeClickListener) {
        this.negativeButton.setVisibility(View.VISIBLE);
        this.negativeButton.setText(text);
        this.negativeButton.setOnClickListener(view -> {


            if (onNegativeClickListener != null) {
                onNegativeClickListener.onClick(view);
            }
            DialogSheet.this.dialog.dismiss();

        });
        this.showButtons = true;
        return this;
    }

    public DialogSheet setPositiveButtonNonAutoDismissable(@StringRes int textRes, OnPositiveClickListenerNotDissmisable onPositiveClickListener) {
        this.setPositiveButton(this.context.getResources().getString(textRes), onPositiveClickListener);
        return this;
    }

    public DialogSheet setPositiveButton(@StringRes int textRes, OnPositiveClickListener onPositiveClickListener) {
        this.setPositiveButton(this.context.getResources().getString(textRes), onPositiveClickListener);
        return this;
    }

    public DialogSheet setNegativeButton(@StringRes int textRes, OnNegativeClickListener onNegativeClickListener) {
        this.setNegativeButton(this.context.getResources().getString(textRes), onNegativeClickListener);
        return this;
    }

    @SuppressWarnings("unused")
    public DialogSheet setButtonsColor(@ColorInt int buttonsColor) {
        this.buttonColor = buttonsColor;
        return this;
    }

    public DialogSheet setButtonsColorRes(@ColorRes int buttonsColorRes) {
        this.buttonColor = ContextCompat.getColor(this.context, buttonsColorRes);
        return this;
    }


    public DialogSheet setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    @SuppressWarnings("unused")
    public DialogSheet setBackgroundColorRes(@ColorRes int backgroundColorRes) {
        this.backgroundColor = ContextCompat.getColor(this.context, backgroundColorRes);
        return this;
    }

    public DialogSheet setCancelable(boolean cancelable) {
        this.dialog.setCancelable(cancelable);
        return this;
    }


    public DialogSheet setView(View view) {

        this.messageContainer.addView(view);
        if (this.inflatedView == null) {
            this.inflatedView = view;
        }

        return this;
    }

    public void removePaddingAroundView() {


        mainDialogContainer.setPadding(0, 0, 0, 0);
    }

    public DialogSheet setView(@LayoutRes int layoutRes) {
        this.inflatedView = View.inflate(this.context, layoutRes, null);
        this.setView(this.inflatedView);
        return this;
    }

    @SuppressWarnings("unused")
    public View getInflatedView() {
        return this.inflatedView;
    }

    public void show() {
        if (this.backgroundColor == -1) {
            this.backgroundColor = DialogUtils.getThemeBgColor(this.context);
        }

        if (this.backgroundColor != -1) {
            // mainDialogContainer.setBackgroundColor(this.backgroundColor);
            this.titleTextView.setTextColor(DialogUtils.getTextColor(this.backgroundColor));
            //this.messageTextView.setTextColor(DialogUtils.getTextColorSec(this.backgroundColor));
        }

        if (!this.showButtons) {
            this.textContainer.setPadding(0, 0, 0, 0);
        } else {
            int color;
            if (this.buttonColor != -1) {
                color = this.buttonColor;
            } else {
                color = DialogUtils.getThemeAccentColor(this.context);
            }

            this.negativeButton.setTextColor(color);
            DialogUtils.setButton(this.backgroundColor, color, this.positiveButton, true);
            DialogUtils.setButton(this.backgroundColor, color, this.negativeButton, false);
            this.positiveButton.setTextColor(DialogUtils.buttonTextColor(color));
        }

        this.dialog.show();
    }

    @SuppressWarnings("unused")
    public void dismiss() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }


    private void init(Context context) {
        this.dialog = new MyDialog(context, R.style.BottomDialogTheme);
        this.dialog.requestWindowFeature(1);
        this.dialog.setContentView(R.layout.layout_bottomdialog);
        Objects.requireNonNull(this.dialog.getWindow()).setLayout(-1, -2);
        this.dialog.getWindow().setGravity(80);
        this.titleTextView = this.dialog.findViewById(R.id.dialogTitle);
        this.messageTextView = this.dialog.findViewById(R.id.dialogMessage);
        this.iconImageView = this.dialog.findViewById(R.id.dialogIcon);
        this.positiveButton = this.dialog.findViewById(R.id.buttonPositive);
        this.negativeButton = this.dialog.findViewById(R.id.buttonNegative);
        this.textContainer = this.dialog.findViewById(R.id.textContainer);
        this.messageContainer = this.dialog.findViewById(R.id.messageContainer);
        this.mainDialogContainer = this.dialog.findViewById(R.id.mainDialogContainer);
    }

    private void showIcon() {
        this.iconImageView.setVisibility(View.VISIBLE);
    }

    public interface OnNegativeClickListener {
        void onClick(View var1);
    }


    public interface OnPositiveClickListenerNotDissmisable {
        void onClick(View var1);
    }

    public interface OnPositiveClickListener {
        void onClick(View var1);
    }


    public interface MyOnCancelListener extends DialogInterface.OnCancelListener {


    }

    public class MyDialog extends Dialog {

        private MyOnCancelListener myOnCancelListener;

        public MyDialog(@NonNull Context context) {
            super(context);
        }

        MyDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }


        void setOnCancelListener(@Nullable MyOnCancelListener listener) {
            super.setOnCancelListener(dialogInterface -> {

            });
            this.myOnCancelListener = listener;
        }

        @Override
        public void cancel() {
            if (myOnCancelListener != null) {
                myOnCancelListener.onCancel(this);
            }

            myOnCancelListener = null;
            super.cancel();
        }
    }
}
*/
