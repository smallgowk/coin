package com.phanduy.adapter;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.phanduy.model.ToolEntity;

import java.util.List;

/**
 * Created by mc.kim on 8/25/2016.
 */
public class ParentsMenu implements ParentListItem {
    private ToolEntity menu = null;
    private boolean isLast = false;

    public ParentsMenu(ToolEntity menu, boolean last) {
        this.menu = menu;
        isLast = last;
    }

    @Override
    public List<?> getChildItemList() {
        if (menu == null) {
            return null;
        } else {
            return menu.getListChilds();
        }
    }


    public boolean hasChild() {
        return menu != null && menu.getListChilds() != null && !menu.getListChilds().isEmpty();
    }


    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public ToolEntity getMenu() {
        return menu;
    }
}
