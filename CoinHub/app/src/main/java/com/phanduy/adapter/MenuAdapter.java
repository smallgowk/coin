package com.phanduy.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phanduy.coinhub.R;
import com.phanduy.model.ToolEntity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by mc.kim on 8/24/2016.
 */
public class MenuAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mInflater = null;
    private ArrayList<ToolEntity> mList = null;

    public MenuAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    private LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public void setParentsMenuList(ArrayList<ToolEntity> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        try {
            return mList.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        try {
//            String menu = mList.get(groupPosition).getPath_id();
            ArrayList<ToolEntity> subMenu = mList.get(groupPosition).getListChilds();

            return subMenu.size();
        } catch (NullPointerException e) {
            return 0;
        }

    }

    @Override
    public ToolEntity getGroup(int groupPosition) {
        try {
            return mList.get(groupPosition);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public ToolEntity getChild(int groupPosition, int childPosition) {
        try {
//            String menu = mList.get(groupPosition).getPath_id();
            ArrayList<ToolEntity> subMenu = mList.get(groupPosition).getListChilds();

            return subMenu.get(childPosition);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getLayoutInflater().inflate(R.layout.item_menu, parent, false);

            holder.menuTxt = (TextView) convertView.findViewById(R.id.menuTxt);
            holder.menuIcon = (ImageView) convertView.findViewById(R.id.menuIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        ToolEntity menu = mList.get(groupPosition);
        updateView(menu, holder, false);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getLayoutInflater().inflate(R.layout.item_menu, parent, false);
            holder.menuTxt = (TextView) convertView.findViewById(R.id.menuTxt);
            holder.menuIcon = (ImageView) convertView.findViewById(R.id.menuIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        String menu = mList.get(groupPosition).getPath_id();
        ArrayList<ToolEntity> subMenu = mList.get(groupPosition).getListChilds();

        updateView(subMenu.get(childPosition), holder, false);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ViewHolder {
        TextView menuTxt = null;
        ImageView menuIcon = null;
        LogoTarget target = null;
    }


    private void updateView(ToolEntity menu, ViewHolder holder, boolean isFocused) {

        String menuName = menu.getToolName();
        holder.menuTxt.setText(menuName);
        holder.menuTxt.setSelected(isFocused);
        holder.menuIcon.setSelected(isFocused);
    }

    private class LogoTarget implements Target {
        private final ViewHolder mHolder;
        private boolean isFocused = false;

        LogoTarget(ViewHolder holder, boolean isFocused) {
            mHolder = holder;
            this.isFocused = isFocused;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            mHolder.menuIcon.setVisibility(View.VISIBLE);

            mHolder.menuIcon.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable drawable) {
            mHolder.menuIcon.setVisibility(View.GONE);
        }

        @Override
        public void onPrepareLoad(Drawable drawable) {
            mHolder.menuIcon.setVisibility(View.GONE);
        }

    }
}
