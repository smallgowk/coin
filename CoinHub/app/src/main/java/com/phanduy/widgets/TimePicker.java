package com.phanduy.widgets;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.phanduy.interfaces.OnTimeSettedListener;

import java.util.Date;
import java.util.Calendar;

public class TimePicker extends EditText implements OnTimeSetListener {
	
	protected int hour;
	protected int minute;
	protected OnTimeSetListener onTimeSetListener = null;
	protected java.text.DateFormat dateFormat;

	private boolean enable = true;

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	private OnTimeSettedListener onTimeSettedListener;
	
	public OnTimeSetListener getOnDateSetListener() {
		return onTimeSetListener;
	}

	public void setOnDateSetListener(OnTimeSetListener onTimeSetListener) {
		this.onTimeSetListener = onTimeSetListener;
	}
	
	public void setOnTimeSettedListener(OnTimeSettedListener onTimeSettedListener) {
		this.onTimeSettedListener = onTimeSettedListener;
	}

	public TimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);

		dateFormat = DateFormat.getMediumDateFormat(getContext());
		
		setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
		setFocusable(false);
		
		setCurrentTime();
	}
	
	public void setTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
		updateText();
	}
	public void setTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		setTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
	}

	public String getTimeStr() {
		
		String hourTime = hour < 10 ? "0" + hour : "" + hour; 
		String minuteTime = minute < 10 ? "0" + minute : "" + minute;
		
		return "" + hourTime + minuteTime + "00";
	}
	
	public void setCurrentTime() {
		Calendar c = Calendar.getInstance();
		setTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
	}
	
	
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int compare(TimePicker timePicker) {
		if(hour < timePicker.hour) return -1;
		if(hour > timePicker.hour) return 1;

		if(minute < timePicker.minute) return -1;
		if(minute > timePicker.minute) return 1;

		return 0;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN && enable) {
			showDatePicker();
		}
		
		return super.onTouchEvent(event);
	}
	
	public java.text.DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(java.text.DateFormat dateFormat) {
		this.dateFormat = dateFormat;
		updateText();
	}
	
	public void showDatePicker() {
		TimePickerDialog timePickerDialog;
		timePickerDialog = new TimePickerDialog(
				getContext(),
				this,
				hour,
				minute,
				true);
		timePickerDialog.show();
	}
	
	public void updateText() {
		setText("" + (hour < 10 ? "0" + hour : "" + hour) + " : " + (minute < 10 ? "0" + minute : "" + minute));
	}

	@Override
	public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
		setTime(hourOfDay, minute);
		clearFocus();
		
		if(onTimeSetListener != null)
			onTimeSetListener.onTimeSet(view, hourOfDay, minute);
		
		if(onTimeSettedListener != null) {
			onTimeSettedListener.onTimeSetted();
		}
	}
}
