package com.phanduy.model;

import java.util.ArrayList;

/**
 * Created by duyuno on 7/14/17.
 */
public class ConversationGroup {

    public static final int ON_GOING = 0;
    public static final int UP_COMMING = 1;
    public static final int FINISHED = 2;

    private int type;
    private String name;
    private ArrayList<ConversationModel> listChild;

    public ArrayList<ConversationModel> getListChild() {
        return listChild;
    }

    public void setListChild(ArrayList<ConversationModel> listChild) {
        this.listChild = listChild;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
