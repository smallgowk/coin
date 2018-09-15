package com.phanduy.adapter.holder;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.phanduy.coinhub.R;

/**
 * Created by mc.kim on 10/3/2016.
 */
public class PaddingForVodLand extends RecyclerView.ItemDecoration {
    private int space;
    private int space_poster;

    public PaddingForVodLand(Context context) {
//        this.space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13, context.getResources().getDisplayMetrics());
//        this.space_poster =  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics());
        this.space = (int)context.getResources().getDimension(R.dimen.margin_edt);
        this.space_poster =  (int)context.getResources().getDimension(R.dimen.margin_item);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = 0;
        }else{
            outRect.left = space_poster;
        }
    }
}