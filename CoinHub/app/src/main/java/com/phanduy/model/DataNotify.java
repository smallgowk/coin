package com.phanduy.model;

/**
 * Created by duyuno on 8/9/16.
 */
public class DataNotify {
    private int sourceId;
    private int actionId;
    private String action;
    private SourceNotify source;
    private OwnerNotify owner;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SourceNotify getSource() {
        return source;
    }

    public void setSource(SourceNotify source) {
        this.source = source;
    }

    public OwnerNotify getOwner() {
        return owner;
    }

    public void setOwner(OwnerNotify owner) {
        this.owner = owner;
    }
}
