package com.example.quoteapp;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewOnclickListner extends RecyclerView.SimpleOnItemTouchListener {
    private static final String TAG = "RecyclerViewOnclickList";

    interface OnItemClickListner {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private final OnItemClickListner mOnItemClickListner;
    private final GestureDetectorCompat mGestureDetector;

    public RecyclerViewOnclickListner(Context context, final RecyclerView recyclerView, OnItemClickListner onItemClickListner) {
        mOnItemClickListner = onItemClickListner;
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mOnItemClickListner != null){
                    Log.d(TAG, "onSingleTapUp: fuction called on mian");
                    mOnItemClickListner.onItemClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
                return true;

            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(TAG, "onLongTap: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mOnItemClickListner != null){
                    Log.d(TAG, "onLongTapUp: fuction called on mian");
                    mOnItemClickListner.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//        Log.d(TAG, "onInterceptTouchEvent: Item click");
        if (mGestureDetector != null) {
            boolean result = mGestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent: Result returned " + result);
            return result;
        } else {
            Log.d(TAG, "onInterceptTouchEvent: Returned false");
            return false;
        }

    }
}
