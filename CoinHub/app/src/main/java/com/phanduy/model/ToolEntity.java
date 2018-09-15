package com.phanduy.model;

import java.util.ArrayList;

public class ToolEntity {
	
	public static final int MENU_PORT_FOLIO = 0;
	public static final int MENU_COIN_MARKET_CAP = 1;
	public static final int MENU_COIN_EVENT = 2;
	public static final int MENU_ICOS = 3;
	public static final int MENU_ABOUT = 4;

	private int toolId;
	private String toolName;
	private int toolResId;
	private String fragmentName;

	private ArrayList<ToolEntity> listChilds;
	
	private int numberNotify;
	
	private boolean isSelected;
	
	public ToolEntity(int toolId, String toolName, int toolResId, String fragmentName) {
		super();
		this.toolId = toolId;
		this.toolName = toolName;
		this.toolResId = toolResId;
		this.fragmentName = fragmentName;
	}
	public ToolEntity(int toolId, String toolName, int toolResId, boolean isSelected, String fragmentName) {
		super();
		this.toolId = toolId;
		this.toolName = toolName;
		this.toolResId = toolResId;
		this.isSelected = isSelected;
		this.fragmentName = fragmentName;
	}



	public ArrayList<ToolEntity> getListChilds() {
		return listChilds;
	}

	public void setListChilds(ArrayList<ToolEntity> listChilds) {
		this.listChilds = listChilds;
	}

	public int getToolId() {
		return toolId;
	}
	public void setToolId(int toolId) {
		this.toolId = toolId;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public int getToolResId() {
		return toolResId;
	}
	public void setToolResId(int toolResId) {
		this.toolResId = toolResId;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public int getNumberNotify() {
		return numberNotify;
	}
	public void setNumberNotify(int numberNotify) {
		this.numberNotify = numberNotify;
	}

	public String getFragmentName() {
		return fragmentName;
	}

	public void setFragmentName(String fragmentName) {
		this.fragmentName = fragmentName;
	}
}
