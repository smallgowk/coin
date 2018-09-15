package com.phanduy.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class BgCustomViewBlue extends View {

	Context mContext;
	private int width;
	private int length;
	private Paint paint;
	private Paint canvasPaint;

	private Canvas drawCanvas;
	// canvas bitmap
	private Bitmap canvasBitmap;

	public BgCustomViewBlue(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0xff1da1f3);

		canvasPaint = new Paint(Paint.DITHER_FLAG);
	}

	public void setColor(int color) {
		paint.setColor(color);
		invalidate();
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

}
