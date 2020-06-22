package com.sawah.signalrtest.common.customView.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sawah.vehicledashboardapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


/**
 * Created by Turk
 * on 2/23/2018.
 */
public class MyProgressDialog extends DialogFragment {


    private View root;
    private String message;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        root = LayoutInflater.from(this.getContext()).inflate(R.layout.progress_dialog_view, (ViewGroup) getView(), false);
        // creating the fullscreen dialog

        final Dialog dialog = new Dialog(Objects.requireNonNull(this.getContext()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);
        TextView messageTv = root.findViewById(R.id.message);
        messageTv.setText(message);
        return dialog;

    }

    @SuppressLint("LogNotTimber")
    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }
    }


    public void setMessage(String message) {

        this.message = message;

    }

    @SuppressWarnings("unused")
    public View getRootView() {

        return root;
    }

    @Override
    public void onDismiss(@NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }


}
