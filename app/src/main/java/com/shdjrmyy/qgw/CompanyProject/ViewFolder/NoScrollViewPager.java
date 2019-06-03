package com.shdjrmyy.qgw.CompanyProject.ViewFolder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 16001 on 2017/10/17 0017.
 */

public class NoScrollViewPager extends ViewPager {
    //com.kachao.shanghu.view.NoScrollViewPager

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    // 重写onTouchEvent事件，什么都不用做
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 重写onTouchEvent，什么都不用做
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
