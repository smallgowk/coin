package com.phanduy.adapter.holder;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.phanduy.model.ColleagueGroup;

import java.util.List;

/**
 * Created by mc.kim on 8/25/2016.
 */
public class ColleagueItem implements ParentListItem {
    private ColleagueGroup colleagueGroup;
    public ColleagueItem() {
    }

    public void setColleagueGroup(ColleagueGroup colleagueGroup) {
        this.colleagueGroup = colleagueGroup;
    }

    public ColleagueGroup getColleagueGroup() {
        return colleagueGroup;
    }

    public int getChildCount() {
        return colleagueGroup.getListMember() != null ? colleagueGroup.getListMember().size() : 0;
    }

    @Override
    public List<?> getChildItemList() {
        return  colleagueGroup.getListMember();
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public boolean hasChild() {
        return colleagueGroup.getListMember() != null && !colleagueGroup.getListMember().isEmpty();
    }
}
