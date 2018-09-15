package com.phanduy.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import com.phanduy.interfaces.ShowHideListener;
import com.phanduy.coinhub.R;

public class AnimationUtil {

	public static void setAlphaAnimation(View view, boolean isShow, int time, final ShowHideListener showHideListener) {
		final AnimationSet animSet = new AnimationSet(true);
		AlphaAnimation alpha = null;
		if (isShow)
			alpha = new AlphaAnimation(0f, 1f);
		else
			alpha = new AlphaAnimation(1f, 0f);
		alpha.setAnimationListener(new Animation.AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				if (showHideListener != null)
					showHideListener.doAfter();
			}
		});
		alpha.setDuration(time);
		animSet.addAnimation(alpha);
		animSet.setFillEnabled(true);
		animSet.setFillAfter(true);
		view.setVisibility(View.VISIBLE);
		view.clearAnimation();
		view.startAnimation(animSet);
	}

	public static void setTranslateAnimation(Context context, View view, boolean isShow, int time, final ShowHideListener showHideListener) {
		final AnimationSet animSet = new AnimationSet(true);
		Animation animation = null;
		if (isShow) {
			animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_down);
		} else {
			animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_up);
		}
		// else
		// alpha = new AlphaAnimation(1f, 0f);
		animation.setAnimationListener(new Animation.AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				if (showHideListener != null)
					showHideListener.doAfter();
			}
		});

		animSet.addAnimation(animation);

		view.setVisibility(View.VISIBLE);
		view.clearAnimation();
		view.startAnimation(animSet);
	}

	public static void setTranslateAnimation(Context context, View view, int id, final ShowHideListener showHideListener) {
		final AnimationSet animSet = new AnimationSet(true);
		Animation animation = null;
		animation = AnimationUtils.loadAnimation(context, id);
		// else
		// alpha = new AlphaAnimation(1f, 0f);
		animation.setAnimationListener(new Animation.AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				if (showHideListener != null)
					showHideListener.doAfter();
			}
		});

		animSet.addAnimation(animation);

		view.setVisibility(View.VISIBLE);
		view.clearAnimation();
		view.startAnimation(animSet);
	}

	public static void setTranslateHorizontalAnimation(Context context, View view,final boolean isShow, int animRes,
			final ShowHideListener showHideListener) {
		final AnimationSet animSet = new AnimationSet(true);
		Animation animation = null;
		animation = AnimationUtils.loadAnimation(context, animRes);
		animation.setAnimationListener(new Animation.AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				if (showHideListener != null)
					showHideListener.doAfter();
			}
		});

		Animation alAnimation = new AlphaAnimation(0.0f, 1.0f);

		animSet.addAnimation(alAnimation);
		animSet.addAnimation(animation);

		view.setVisibility(View.VISIBLE);
		view.clearAnimation();
		view.startAnimation(animSet);
	}

}
