package com.phanduy.adapter.holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.phanduy.coinhub.R;


/**
 * Created by mc.kim on 8/24/2016.
 */
public class ColleagueHolder extends ChildViewHolder {

    public TextView txtName;
    public TextView txtPhone;
    public ImageView avatar;
    public ImageButton btnPhone;

    public ColleagueHolder(View itemView) {
        super(itemView);

        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        btnPhone = (ImageButton) itemView.findViewById(R.id.btnPhone);
    }
}