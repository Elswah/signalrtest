package com.sawah.signalrtest.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListDecorator : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {


        val itemPosition: Int = parent.getChildAdapterPosition(view)

        outRect.apply {

            left = 10
            /*if(itemPosition%2==0) {
                right = 10
            }else{

                right = 60
            }*/
            right = 10
            bottom = 10
            top = 10

        }
    }

}