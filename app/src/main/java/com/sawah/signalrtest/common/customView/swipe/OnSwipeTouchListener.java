package com.sawah.signalrtest.common.customView.swipe;


import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by Turk
 * on 2/22/2018.
 */
public class OnSwipeTouchListener implements OnTouchListener {


    float dX, dY;

    private View viewToAnimate;
    private View viewToMoveAlong;
    private View containerWhichWillDetectGesture;
    private View pointToBeNoted;


    private OnSlideListener onExitListener;
    private boolean isExit;

    public OnSwipeTouchListener(View containerWhichWillDetectGesture, View viewToMoveAlong, View viewToAnimate, View pointToBeNoted, OnSlideListener onExitListener) {

        this.containerWhichWillDetectGesture = containerWhichWillDetectGesture;
        this.viewToAnimate = viewToAnimate;
        this.viewToMoveAlong = viewToMoveAlong;
        this.pointToBeNoted = pointToBeNoted;
        this.onExitListener = onExitListener;
        isExit = false;

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
/*
                if (event.getRawX() > (viewToAnimate.getX() + viewToAnimate.getWidth())) {

                    return false;
                }*/

                isExit = false;
                dX = viewToAnimate.getX() - event.getRawX();
                dY = viewToAnimate.getY(); /*- event.getRawY();*/
                break;

            case MotionEvent.ACTION_MOVE:

                // restricting the textview so that it could no move out of its container

                if ((event.getRawX() + dX) + viewToAnimate.getWidth() <= view.getWidth()) {

                    viewToAnimate.animate()
                            .x(event.getRawX() + dX)
                            .y(dY)
                            .setDuration(0)// speed on animation can be changed here
                            .start();


                    viewToMoveAlong.animate()
                            .x((event.getRawX() - viewToMoveAlong.getWidth()))
                            .y(dY)
                            .setDuration(0)// speed on animation can be changed here
                            .start();


                    if (!isExit) {
                        float currentX2OfMovingTextView = event.getRawX() + dX + viewToAnimate.getWidth();
                        float centerXPonitOfPointToBeNotedTextView = pointToBeNoted.getX() + (pointToBeNoted.getWidth() / 2);
                        if (currentX2OfMovingTextView >= centerXPonitOfPointToBeNotedTextView) {
                            isExit = true;
                            onExitListener.onExit();
                        }

                    }

                }
                // Log.v("adeel","moved "+dX);
                break;

            case MotionEvent.ACTION_UP:

                if (!isExit) {
                    float currentX2OfMovingTextView = event.getRawX() + dX + viewToAnimate.getWidth();
                    float centerXPonitOfPointToBeNotedTextView = pointToBeNoted.getX() + (pointToBeNoted.getWidth() / 2);
                    if (currentX2OfMovingTextView < centerXPonitOfPointToBeNotedTextView) {

                        onExitListener.onCancel();
                    }

                }


                viewToAnimate.animate()
                        .x(0)
                        .y(dY)
                        .setDuration(1000) // 1000 is delay in milisec you can change it accordinly
                        .start();

                viewToMoveAlong.animate()
                        .x(-(viewToAnimate.getWidth() + containerWhichWillDetectGesture.getWidth()))
                        .y(dY)
                        .setDuration(1300)// speed on animation can be changed here
                        .start();
                break;
            default:
                return false;


        }
        return true;
        //   return gestureDetector.onTouchEvent(event);
    }

    public void moveToStart() {
        viewToMoveAlong.animate()
                .x(-(viewToAnimate.getWidth() + containerWhichWillDetectGesture.getWidth()))
                .y(dY)
                .setDuration(0)// speed on animation can be changed here
                .start();

    }

}

