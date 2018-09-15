package com.phanduy.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.Model.ParentWrapper;
import com.phanduy.adapter.holder.MenuHolder;
import com.phanduy.adapter.holder.SubmenuHolder;
import com.phanduy.coinhub.R;
import com.phanduy.interfaces.RecyclerItemClickListener;
import com.phanduy.model.ToolEntity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by mc.kim on 8/25/2016.
 */
public class ExpandableAdapter extends ExpandableRecyclerAdapter<MenuHolder, SubmenuHolder>
        implements RecyclerItemClickListener.OnItemClickListener, View.OnClickListener {


    private LayoutInflater mInflater = null;

    public ExpandableAdapter(LayoutInflater layoutInflater, List<ParentsMenu> parentItemList) {
        super(parentItemList);
        this.mInflater = layoutInflater;
    }

    @Override
    public MenuHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(R.layout.item_menu, parentViewGroup, false);

        RelativeLayout root = (RelativeLayout) view.findViewById(R.id.root);
        root.setOnClickListener(null);
        root.setOnTouchListener(null);

        view.setClickable(false);
        view.setOnClickListener(null);
        view.setOnTouchListener(null);
        TextView menuTxt = (TextView) view.findViewById(R.id.menuTxt);
        ImageView btnExpand = (ImageView) view.findViewById(R.id.btnExpand);
        LinearLayout layoutTitle = (LinearLayout) view.findViewById(R.id.layoutTitle);
        btnExpand.setTag(view);
        layoutTitle.setTag(view);
        menuTxt.setTag(view);
        layoutTitle.setOnClickListener(this);
        btnExpand.setOnClickListener(this);
        return new MenuHolder(view);
    }

    @Override
    public SubmenuHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(R.layout.item_menu_child, null, false);
        view.setClickable(true);
        TextView menuTxt = (TextView) view.findViewById(R.id.menuTxt);
        LinearLayout layoutItem = (LinearLayout) view.findViewById(R.id.layoutItem);

        view.setTag(view);
        menuTxt.setTag(view);
        layoutItem.setTag(view);
//        menuTxt.setOnClickListener(this);
        view.setOnClickListener(this);
        return new SubmenuHolder(view);
    }
//
//    private boolean disableMenu(ToolEntity selectedMenu) {
//        if (selectedMenu.getTag() != null && selectedMenu.getTag().equals(MenuFields.TAG_SETTING_ROOT)) {
//            return true;
//        }
//        return false;
//    }


    @Override
    public void onBindParentViewHolder(MenuHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ParentsMenu parentsMenu = (ParentsMenu) parentListItem;

        ToolEntity item = parentsMenu.getMenu();

        parentViewHolder.menuTxt.setText(parentsMenu.getMenu().getToolName());
        parentViewHolder.menuIcon.setImageResource(item.getToolResId());

        parentViewHolder.btnExpanded.setVisibility(parentsMenu.hasChild() ? View.VISIBLE : View.GONE);
        if (parentsMenu.hasChild()) {
            parentViewHolder.btnExpanded.setVisibility(View.VISIBLE);
            parentViewHolder.layoutTitle.setVisibility(View.VISIBLE);
        } else {
            parentViewHolder.btnExpanded.setVisibility(View.GONE);
            parentViewHolder.layoutTitle.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onBindChildViewHolder(SubmenuHolder childViewHolder, int position, Object childListItem) {


        ToolEntity subMenu = (ToolEntity) childListItem;

        childViewHolder.itemView.findViewById(R.id.bottomSpace).setVisibility(View.GONE);
        childViewHolder.menuTxt.setText(subMenu.getToolName());

//        Picasso picasso = Picasso.with(mInflater.getContext());
//        if (childViewHolder.logoTarget != null) {
//            picasso.cancelRequest(childViewHolder.logoTarget);
//        }
//        childViewHolder.logoTarget = new SubLogoTarget(childViewHolder, false);
//        picasso.load(PictureAPI.getInstance().getMenuIcon(subMenu.getId())).into(childViewHolder.logoTarget);

    }


    public class LogoTarget implements Target {
        private final MenuHolder mHolder;
        private boolean isFocused = false;

        LogoTarget(MenuHolder holder, boolean isFocused) {
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

//    public class SubLogoTarget implements Target {
//        private final SubmenuHolder mHolder;
//        private boolean isFocused = false;
//
//        SubLogoTarget(SubmenuHolder holder, boolean isFocused) {
//            mHolder = holder;
//            this.isFocused = isFocused;
//        }
//
//        @Override
//        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
//            mHolder.menuIcon.setVisibility(View.VISIBLE);
//
//            mHolder.menuIcon.setImageBitmap(bitmap);
//        }
//
//        @Override
//        public void onBitmapFailed(Drawable drawable) {
//            mHolder.menuIcon.setVisibility(View.GONE);
//        }
//
//        @Override
//        public void onPrepareLoad(Drawable drawable) {
//            mHolder.menuIcon.setVisibility(View.GONE);
//        }
//
//    }

    private RecyclerView mRecyclerView = null;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.e("onItemClick", "OK");
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }

    public Object getListItem(int position) {
        return super.getListItem(position);
    }

    @Override
    public void onClick(View v) {
        View adapterView = (View) v.getTag();
        if (adapterView == null) {
            return;
        }

        int position = mRecyclerView.getChildLayoutPosition(adapterView);

        if (v.getId() == R.id.btnExpand) {

            onClickExpand(position);

        } else {
            Object listItem = getListItem(position);
            ToolEntity selectedMenu = null;
            if (listItem instanceof ParentWrapper) {



                ParentWrapper wrapper = (ParentWrapper) listItem;
                ParentListItem item = wrapper.getParentListItem();
                ParentsMenu menu = (ParentsMenu) item;


                if(menu.hasChild()) {
                    onClickExpand(position);
                } else {
                    selectedMenu = menu.getMenu();
                    if (mListener != null)
                        mListener.onMenuSelected(selectedMenu);
                }

            } else {
                ToolEntity menu = (ToolEntity) listItem;
                selectedMenu = menu;

                if (mListener != null)
                    mListener.onMenuSelected(selectedMenu);
            }

//            if (!disableMenu(selectedMenu)) {
//                if (selectedMenu.isClickable()) {
//
//                } else {
//
//                }
//            }


        }
    }

    public void onClickExpand(int position) {
        int positionExpanding = -1;
        int menuExpandingChildCount = -1;

        for (int i = 0, count = getItemCount(); i < count; i++) {
            if (i == position) {
                continue;
            }
            Object listItem = getListItem(i);
            if (listItem instanceof ParentWrapper) {
                ParentWrapper wrapper = (ParentWrapper) listItem;
                ParentListItem item = wrapper.getParentListItem();

                if (wrapper.isExpanded()) {
                    ParentsMenu menu = (ParentsMenu) item;
                    positionExpanding = i;
                    menuExpandingChildCount = menu.getChildItemList().size();
                    collapseParent(item);
                }
            }
        }
        int updateNextExpand = positionExpanding < 0 || positionExpanding > position ? position : position - menuExpandingChildCount;
        Object listItemPosition = getListItem(updateNextExpand);
        if (listItemPosition instanceof ParentWrapper) {
            ParentWrapper wrapper = (ParentWrapper) listItemPosition;
            ParentListItem item = wrapper.getParentListItem();
            if (wrapper.isExpanded()) {
                collapseParent(item);
            } else {
                expandParent(item);
            }
        }

        notifyDataSetChanged();
    }

    private OnMenuSelectedListener mListener = null;

    public void setOnMenuSelectedListener(OnMenuSelectedListener mListener) {
        this.mListener = mListener;
    }

    public interface OnMenuSelectedListener {
        void onMenuSelected(ToolEntity menu);
    }
}
