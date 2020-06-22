package com.sawah.signalrtest.common.snackbar;

/**
 * Created by Turk
 * on 3/13/2018.
 */

public class SnackBarData {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int SUCCESS_SMALL = 3;
    public static final int DEFAULT_TYPE = -1;
    private String message;
    private int messageType;

    public SnackBarData(String message, int messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    @SuppressWarnings("unused")
    public static int getSUCCESS() {
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageType() {
        return messageType;
    }

    @SuppressWarnings("unused")
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }


}
