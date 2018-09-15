package com.phanduy.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {

	private boolean enabled;
	
	public CustomViewPager(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    this.enabled = true;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
//	    if (this.enabled) {
//	        return super.onTouchEvent(event);
//	    }

	    return this.enabled ? super.onTouchEvent(event) : false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
//	    if (this.enabled) {
//	        return super.onInterceptTouchEvent(event);
//	    }

	    return this.enabled ? super.onInterceptTouchEvent(event) : false;
	}

	public void setPagingEnabled(boolean enabled) {
	    this.enabled = enabled;
	}
}
