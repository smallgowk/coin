package com.phanduy.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by duyuno on 7/16/17.
 */
public class MemberHolder extends RecyclerView.ViewHolder  {

    public ImageView imageView;

    public MemberHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView;
    }
}
