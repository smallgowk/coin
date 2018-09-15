package com.phanduy.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.Model.ParentWrapper;
import com.phanduy.adapter.ExpandableAdapter;
import com.phanduy.adapter.ParentsMenu;
import com.phanduy.coinhub.R;
import com.phanduy.model.ToolEntity;
import com.phanduy.store.AppSharePreference;
import com.phanduy.utils.DataManager;
import com.phanduy.utils.StringUtility;
import com.phanduy.view.activity.MainHomeActivity;
import com.phanduy.view.custom.TextViewWithFont;

import java.util.ArrayList;
import java.util.Map;

public class MenuLeft extends Fragment implements ExpandableAdapter.OnMenuSelectedListener{


	private MainHomeActivity mainHomeActivity;
	AppSharePreference appSharePreference;

	RecyclerView menuList;
	private ExpandableAdapter expandableAdapter = null;

//	public SampleAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_behind, null);

		mainHomeActivity = (MainHomeActivity) getActivity();

		appSharePreference = com.phanduy.store.AppSharePreference.getInstance(mainHomeActivity);

		menuList = (RecyclerView) v.findViewById(R.id.listView);
		menuList.setLayoutManager(new LinearLayoutManager(mainHomeActivity));
		ArrayList<ParentsMenu> listParentMenu = DataManager.genParentObj(DataManager.listMenu);
		expandableAdapter = new ExpandableAdapter(inflater, listParentMenu);
		expandableAdapter.onRestoreInstanceState(savedInstanceState);
		expandableAdapter.setOnMenuSelectedListener(this);
		expandableAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
			@Override
			public void onListItemExpanded(int position) {
				Log.e("onListItemExpanded", "" + position);
				int positionExpanding = -1;
				int menuExpandingChildCount = -1;

				for (int i = 0, count = expandableAdapter.getItemCount(); i < count; i++) {
					if (i == position) {
						continue;
					}
					Object listItem = expandableAdapter.getListItem(i);
					if (listItem instanceof ParentWrapper) {
						ParentWrapper wrapper = (ParentWrapper) listItem;
						ParentListItem item = wrapper.getParentListItem();

						if (wrapper.isExpanded()) {
							ParentsMenu menu = (ParentsMenu) item;
							positionExpanding = i;
							menuExpandingChildCount = menu.getChildItemList().size();
							expandableAdapter.collapseParent(item);
						}
					}
				}
				int updateNextExpand = positionExpanding < 0 || positionExpanding > position ? position : position - menuExpandingChildCount;
				Object listItemPosition = expandableAdapter.getListItem(updateNextExpand);
				if (listItemPosition instanceof ParentWrapper) {
					ParentWrapper wrapper = (ParentWrapper) listItemPosition;
					ParentListItem item = wrapper.getParentListItem();
					if (!wrapper.isExpanded()) {
						expandableAdapter.collapseParent(item);
					} else {
						expandableAdapter.expandParent(item);
					}
				}
//                expandableAdapter.notifyDataSetChanged();
			}

			@Override
			public void onListItemCollapsed(int position) {
				Log.e("onListItemCollapsed", "" + position);
				ParentWrapper wrapper = (ParentWrapper) expandableAdapter.getListItem(position);
				wrapper.setExpanded(false);

			}
		});


//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy =
//                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

		menuList.setAdapter(expandableAdapter);


//		adapter = new SampleAdapter(getActivity());
//
//		listView.setAdapter(adapter);
//
//		adapter.setPickPosition(0);

//		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				ToolEntity toolEntity = adapter.getItem(position);
//
//				if(toolEntity != null) {
//					adapter.setPickPosition(position);
//					mainHomeActivity.onClickMenu(toolEntity);
//				}
//			}
//		});

		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mainHomeActivity = (MainHomeActivity) activity;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onMenuSelected(ToolEntity menu) {
		mainHomeActivity.onClickMenu(menu);
	}

	private class ViewHolder {
		public RelativeLayout layoutItem;
		public TextViewWithFont title;
		public ImageView imageView;

	}

//	public class SampleAdapter extends BaseAdapter {
//
//		private int currentPosition;
//		private Activity context;
//
//		public SampleAdapter(Activity context) {
//			this.context = context;
//		}
//
//		public void setPickPosition(int pickPosition) {
//
//			if(DataManager.listMenu == null || DataManager.listMenu.isEmpty())  return;
//
////			int i = 0;
////			for (Map.Entry<Integer, ToolEntity> entry : DataManager.hashMenu.entrySet()) {
////				ToolEntity toolEntity = entry.getValue();
////				if (pickPosition == i) {
////					toolEntity.setSelected(true);
////				} else {
////					toolEntity.setSelected(false);
////				}
////
////				i++;
////			}
//
//			for(int i = 0, size = DataManager.listMenu.size() ; i < size; i++) {
//				ToolEntity toolEntity = DataManager.listMenu.get(i);
//				if (pickPosition == i) {
//					toolEntity.setSelected(true);
//				} else {
//					toolEntity.setSelected(false);
//				}
//			}
//
//			notifyDataSetChanged();
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
//
//			ViewHolder viewHolder;
//			if (convertView == null) {
//				convertView = context.getLayoutInflater().inflate(R.layout.item_category, null);
//				viewHolder = new ViewHolder();
//				viewHolder.layoutItem = (RelativeLayout) convertView.findViewById(R.id.layoutItem);
//				viewHolder.title = (TextViewWithFont) convertView.findViewById(R.id.textTitle);
//				viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageIcon);
////				viewHolder.textNumber.setTypeface(FontTypeface.getTypecace(context, FontTypeface.FONT_PROXIMA_BOLD));
//				convertView.setTag(viewHolder);
//			} else {
//				viewHolder = (ViewHolder) convertView.getTag();
//			}
//
//			final ToolEntity menuItemCategory = getItem(position);
//
//			if (menuItemCategory != null) {
//				viewHolder.title.setText(menuItemCategory.getToolName());
//				viewHolder.imageView.setImageResource(menuItemCategory.getToolResId());
//				if (menuItemCategory.isSelected()) {
//					viewHolder.layoutItem.setBackgroundResource(R.drawable.drawable_selected);
////					viewHolder.title.setTypeface(FontTypeface.getTypecace(context, FontTypeface.FONT_ROBOTO_BOLD));
//				} else {
//					viewHolder.layoutItem.setBackgroundResource(R.drawable.drawable_transparent);
////					viewHolder.title.setTypeface(FontTypeface.getTypecace(context, FontTypeface.FONT_ROBOTO_REGULAR));
//				}
//
////                if (menuItemCategory.getToolId() == ToolEntity.MENU_BILLING && menuItemCategory.getNumberNotify() > 0) {
////                    viewHolder.textNumber.setVisibility(View.VISIBLE);
////                    viewHolder.textNumber.setText("" + menuItemCategory.getNumberNotify());
////                } else {
////                    viewHolder.textNumber.setVisibility(View.GONE);
////                }
//			}
//
//			return convertView;
//		}
//
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return DataManager.listMenu != null ? DataManager.listMenu.size() : 0;
//		}
//
//		@Override
//		public ToolEntity getItem(int position) {
//
//			if(DataManager.listMenu == null || DataManager.listMenu.isEmpty() || position >= DataManager.listMenu.size())  return null;
//
////			int i = 0;
////			for (Map.Entry<Integer, ToolEntity> entry : DataManager.hashMenu.entrySet()) {
////				if(i == position) {
////					return entry.getValue();
////				} else {
////					i++;
////				}
////			}
//
//			return DataManager.listMenu.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//	}
}
