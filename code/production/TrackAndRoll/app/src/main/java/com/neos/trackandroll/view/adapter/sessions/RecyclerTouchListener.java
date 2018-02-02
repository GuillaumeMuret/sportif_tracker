package com.neos.trackandroll.view.adapter.sessions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private IClickListener clickListener;

    /**
     * constructor of the recycler touch listener
     * @param context : the context
     * @param recyclerView : the recyclerview
     * @param clickListener : the click listener
     */
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final IClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    /**
     * method that says when an touch event is intercepted
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child),e);
        }
        return false;
    }

    /**
     * method that says when there is a touch event
     * @param rv : the recycler view
     * @param e : the motion event
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    /**
     * method that says when the disallow is asked
     * @param disallowIntercept : the disallow boolean
     */
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}