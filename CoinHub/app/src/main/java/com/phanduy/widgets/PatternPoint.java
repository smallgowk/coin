package com.phanduy.widgets;

public class PatternPoint {
	float x,y; // Toa do de ve len canvas
	int code; // Ma (tu 1->9)
	int row; // Vi tri row tren ma tran
	int column; // Vi tri column tren ma tran
	int position; // vi tri tren ma tran
	
	public PatternPoint(float x, float y, int code, int position) {
		super();
		this.x = x;
		this.y = y;
		this.code = code;
		this.position = position;
		this.row = position / 3;
		this.column = position % 3;
	}
	
	public PatternPoint() {
		
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
