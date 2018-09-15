package com.phanduy.widgets;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.phanduy.interfaces.OnTimeSettedListener;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker extends EditText implements DatePickerDialog.OnDateSetListener {

	protected int year;
	protected int month;
	protected int day;
	protected OnTimeSetListener onTimeSetListener = null;
//	protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd/MM/yyyy");
	protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyyMMdd");

	private OnTimeSettedListener onTimeSettedListener;

	private boolean enable = true;

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public OnTimeSetListener getOnDateSetListener() {
		return onTimeSetListener;
	}

	public void setOnDateSetListener(OnTimeSetListener onTimeSetListener) {
		this.onTimeSetListener = onTimeSetListener;
	}

	public void setOnTimeSettedListener(OnTimeSettedListener onTimeSettedListener) {
		this.onTimeSettedListener = onTimeSettedListener;
	}

	public DatePicker(Context context, AttributeSet attrs) {
		super(context, attrs);

//		dateFormat = DateFormat.getMediumDateFormat(getContext());
		
		setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
		setFocusable(false);
		
		setCurrentTime();
	}
	
	public void setTime(int year, int month, int day) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		this.year = year;
		this.month = month;
		this.day = day;
//		updateText();

		setText(simpleDateFormat.format(c.getTime()));
	}

	public void setPrevDay() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.add(Calendar.DAY_OF_MONTH, -1);

		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		setText(simpleDateFormat.format(c.getTime()));
	}

	public void setDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		setText(simpleDateFormat.format(c.getTime()));
	}

	public void setNextDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.add(Calendar.DAY_OF_MONTH, 1);

		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		setText(simpleDateFormat.format(c.getTime()));
	}

	public Calendar getDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		return c;
	}
	
	public String getTimeStr() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		
		return serverDateFormat.format(c.getTime());
	}
	
	public void setCurrentTime() {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		setText(simpleDateFormat.format(c.getTime()));

//		setTempCreatedDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}
	
	


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN && enable) {
			showDatePicker();
		}
		
		return super.onTouchEvent(event);
	}

	public void showDatePicker() {
		DatePickerDialog datePickerDialog;
		datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
		datePickerDialog.show();
	}

	public int compare(DatePicker datePicker) {
		if(this.year < datePicker.year) return -1;
		if(this.year > datePicker.year) return 1;

		if(this.month < datePicker.month) return -1;
		if(this.month > datePicker.month) return 1;

		if(day < datePicker.day) return -1;
		if(day > datePicker.day) return 1;

		return 0;
	}
	
//	public java.text.DateFormat getDateFormat() {
//		return dateFormat;
//	}
//
//	public void setDateFormat(java.text.DateFormat dateFormat) {
//		this.dateFormat = dateFormat;
//		updateText();
//	}

	
//	public void updateText() {
//		setText(getTime());
//	}


	@Override
	public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		setTime(year, monthOfYear, dayOfMonth);
		clearFocus();
	}
}
