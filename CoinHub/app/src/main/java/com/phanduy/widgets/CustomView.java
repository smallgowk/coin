package com.phanduy.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.phanduy.coinhub.R;

public class CustomView extends View {

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

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		paint.setStyle(Paint.Style.FILL);

		if (attrs != null) {
			TypedArray styled = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
			ColorStateList color1 = styled.getColorStateList(R.styleable.CustomView_normalColor);
			normalColor = color1.getColorForState(getDrawableState(), Color.TRANSPARENT);
			ColorStateList color2 = styled.getColorStateList(R.styleable.CustomView_pressColor);
			pressColor = color2.getColorForState(getDrawableState(), Color.TRANSPARENT);

			orientation = styled.getInt(R.styleable.CustomView_orientation, HORIZONTAL);

			styled.recycle();

			int[] attrsArray = new int[] { android.R.attr.layout_width, // 0
					android.R.attr.layout_height // 1
			};

		}

		paint.setColor(normalColor);

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

		switch (orientation) {
		case VERTICAL:
			width = getWidth();
			length = getHeight();
			canvas.drawCircle(width / 2, width / 2, width / 2, paint);
			canvas.drawCircle(width / 2, length - width / 2, width / 2, paint);
			canvas.drawRect(new Rect(0, width / 2, width, length - width / 2), paint);
			break;
		case HORIZONTAL:
			width = getHeight();
			length = getWidth();
			canvas.drawCircle(width / 2, width / 2, width / 2, paint);
			canvas.drawCircle(length - width / 2, width / 2, width / 2, paint);
			canvas.drawRect(new Rect(width / 2, 0, length - width / 2, width), paint);
			break;
		}

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
			break;
		default:
			return false;
		}

		invalidate();
		return true;
	}
}
