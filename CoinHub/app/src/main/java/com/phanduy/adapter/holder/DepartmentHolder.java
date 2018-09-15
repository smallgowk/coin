package com.phanduy.adapter.holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.phanduy.coinhub.R;

/**
 * Created by mc.kim on 8/24/2016.
 */
public class DepartmentHolder extends ParentViewHolder {

    public TextView menuTxt;
//    public ImageView menuIcon;
    public ImageView btnExpanded;
//    public View line;
//    public LinearLayout layoutTitle;

//    public ListColleagueAdapter.LogoTarget logoTarget = null;

    public DepartmentHolder(View itemView) {
        super(itemView);

        menuTxt = (TextView) itemView.findViewById(R.id.menuTxt);
//        menuIcon = (ImageView) itemView.findViewById(R.id.menuIcon);
        btnExpanded = (ImageView) itemView.findViewById(R.id.btnExpand);
//        layoutTitle = (LinearLayout) itemView.findViewById(R.id.layoutTitle);
//        line = itemView.findViewById(R.id.line);
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        btnExpanded.setSelected(expanded);
        btnExpanded.setVisibility(View.VISIBLE);

//        if (btnExpanded.getVisibility() == View.VISIBLE) {
//            line.setVisibility(expanded ? View.VISIBLE : View.INVISIBLE);
//        } else {
//            line.setVisibility(View.INVISIBLE);
//        }
    }


}