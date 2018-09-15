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

public class BgStrokeFillBlueWhite extends View {

	Context mContext;
	private int width;
	private int length;
	static Paint borderPaint = new Paint();
	static Paint fillPaint = new Paint();
	private Paint canvasPaint;
	
	private int normalColor = -1;
	private int pressColor = -1;
	
	private Canvas drawCanvas;
	// canvas bitmap
	private Bitmap canvasBitmap;
	
	private int strokeWidth = 5;
	
	private OnClickButtonListener onClickButtonListener;

	public BgStrokeFillBlueWhite(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		borderPaint.setStyle(Paint.Style.FILL);
		borderPaint.setColor(0xff1da1f3);
		borderPaint.setStrokeWidth(strokeWidth);
		
		normalColor = 0xffffffff;
		pressColor = 0xff3db1ff;
		
		fillPaint.setStyle(Paint.Style.FILL);
		fillPaint.setColor(normalColor);
		
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
		canvas.drawCircle(width / 2, width / 2, width / 2, borderPaint);
		canvas.drawCircle(length - width / 2, width / 2, width / 2, borderPaint);
		canvas.drawRect(new Rect(width / 2, 0, length - width / 2, width),
				borderPaint);
		
		canvas.drawCircle(width / 2, width / 2, width / 2 - strokeWidth, fillPaint);
		canvas.drawCircle(length - width / 2, width / 2, width / 2 - strokeWidth, fillPaint);
		canvas.drawRect(new Rect(width / 2, strokeWidth, length - width / 2, width - strokeWidth),
				fillPaint);

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			fillPaint.setColor(pressColor);
			break;
		case MotionEvent.ACTION_MOVE:
			fillPaint.setColor(pressColor);
			break;
		case MotionEvent.ACTION_UP:
			fillPaint.setColor(normalColor);
			
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
