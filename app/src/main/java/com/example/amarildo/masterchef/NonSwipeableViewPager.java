package com.example.amarildo.masterchef;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by amarildo on 17-12-26.
 */

public class NonSwipeableViewPager extends ViewPager {

    private boolean enabledViewPager;

    public NonSwipeableViewPager(Context context) {

        super(context);

        //this.enabledViewPager = true;
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        Log.i("onInterceptTouchEvent", enabledViewPager + "");

        if (this.enabledViewPager) {
            return super.onTouchEvent(event);
        }

        // Never allow swiping to switch between pages
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i("OnTouchEvent", enabledViewPager + "");

        return enabledViewPager && super.onTouchEvent(event);
    }

    public void setPagingEnabled(boolean enabledViewPager) {

        this.enabledViewPager = enabledViewPager;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {

        Log.i("OnScrollHorizontally", enabledViewPager + "");

        return enabledViewPager && super.canScrollHorizontally(direction);
    }

    @Override
    public boolean executeKeyEvent(KeyEvent event)
    {
        return enabledViewPager ? super.executeKeyEvent(event) : false;
    }

}
