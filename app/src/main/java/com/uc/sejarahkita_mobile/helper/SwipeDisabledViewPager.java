package com.uc.sejarahkita_mobile.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class SwipeDisabledViewPager extends ViewPager {
    public SwipeDisabledViewPager(Context context) {
        super(context);
    }

    public SwipeDisabledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
