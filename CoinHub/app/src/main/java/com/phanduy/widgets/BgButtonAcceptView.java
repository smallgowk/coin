package com.phanduy.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.phanduy.interfaces.OnClickButtonListener;

public class BgButtonAcceptView extends View {

	private static final int VERTICAL = 0;
	private static final int HORIZONTAL = 1;

	Context mContext;
	private int width;
	private int length;
	static Paint paint = new Paint();
	private Paint canvasPaint;

	private int normalColor = -1;
	private int pressColor = -1;
	private int orientation;

	private Canvas drawCanvas;
	// canvas bitmap
	private Bitmap canvasBitmap;
	private int color;
	private String text;
	
	private OnClickButtonListener onClickButtonListener;

	public BgButtonAcceptView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		paint.setStyle(Paint.Style.FILL);

//		normalColor = 0xff1bb2df;
		normalColor = 0xff1da1f3;
		pressColor = 0xff3db1ff;

		paint.setColor(normalColor);
//		paint.setARGB(255, r, g, b);

		canvasPaint = new Paint(Paint.DITHER_FLAG);
	}
	
	public OnClickButtonListener getOnClickButtonListener() {
		return onClickButtonListener;
	}

	public void setOnClickButtonListener(OnClickButtonListener onClickButtonListener) {
		this.onClickButtonListener = onClickButtonListener;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);

		width = getHeight();
		length = getWidth();
		canvas.drawCircle(width / 2, width / 2, width / 2, paint);
		canvas.drawCircle(length - width / 2, width / 2, width / 2, paint);
		canvas.drawRect(new Rect(width / 2, 0, length - width / 2, width),
				paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			paint.setColor(pressColor);
			break;
		case MotionEvent.ACTION_MOVE:
			paint.setColor(pressColor);
			break;
		case MotionEvent.ACTION_UP:
			paint.setColor(normalColor);
			
			if(onClickButtonListener != null) {
				onClickButtonListener.onClick();
			}
			
			break;
		default:
			return false;
		}

		invalidate();
		return true;
	}
}
