package com.phanduy.adapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.phanduy.coinhub.R;

/**
 * Created by mc.kim on 8/24/2016.
 */
public class SubmenuHolder extends ChildViewHolder {

    public TextView menuTxt;
//    public ImageView menuIcon;
    public LinearLayout layoutItem;
//    public ExpandableAdapter.SubLogoTarget logoTarget = null;

    public SubmenuHolder(View itemView) {
        super(itemView);

        menuTxt = (TextView) itemView.findViewById(R.id.menuTxt);
//        menuIcon = (ImageView) itemView.findViewById(R.id.menuIcon);
        layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
    }
}