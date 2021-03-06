package com.phanduy.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class BgCustomViewBlueVertical extends View {

	Context mContext;
	private int width;
	private int length;
	static Paint paint = new Paint();
	private Paint canvasPaint;
	
	private Canvas drawCanvas;
	// canvas bitmap
	private Bitmap canvasBitmap;

	public BgCustomViewBlueVertical(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0xff1da1f3);

		canvasPaint = new Paint(Paint.DITHER_FLAG);
	}
	public BgCustomViewBlueVertical(Context context) {
		super(context);
		mContext = context;
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0xff1da1f3);

		canvasPaint = new Paint(Paint.DITHER_FLAG);
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

		width = getWidth();
		length = getHeight();
		
		if(length <= width) {
			canvas.drawCircle(width / 2, length - width / 2, width / 2, paint);
		} else {
			canvas.drawCircle(width / 2, width / 2, width / 2, paint);
			canvas.drawCircle(width / 2, length - width / 2, width / 2, paint);
			canvas.drawRect(new Rect(0, width / 2, width, length - width / 2), paint);
		}
		

	}
	
}
