package com.phanduy.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.phanduy.coinhub.R;
import com.phanduy.interfaces.ConfirmListener;
import com.phanduy.interfaces.ModifyListener;
import com.phanduy.model.MyCoin;
import com.phanduy.model.coinmarket.CoinModel;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.DialogUtility;
import com.phanduy.utils.StringUtility;

import java.util.ArrayList;

public class ListCoinSwipeAdapter extends BaseSwipeAdapter {

    private Context mContext;

    ModifyListener modifyListener;

    public ListCoinSwipeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setModifyListener(ModifyListener modifyListener) {
        this.modifyListener = modifyListener;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);
        final SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));

        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewWithTag("Bottom2"));

        final CoinModel customerModel = getItem(position);

//        swipeLayout.setTag(customerModel);


        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                customerModel.setOpen(true);
            }

            @Override
            public void onClose(SwipeLayout layout) {
                customerModel.setOpen(false);
            }
        });

        swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                CustomerModel customerModel1 = (CustomerModel) swipeLayout.getTag();

                if(customerModel.isOpen()) {
                    swipeLayout.close(true);
                } else {
                    swipeLayout.close(true);
//                    ((ListCustomerActivity)mContext).goToDetail(customerModel1.getCustomerId());
                }

            }
        });

        if(!customerModel.isOpen()) {
            swipeLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeLayout.close();
                }
            }, 100);
        }



        return v;
    }


    class ViewHolder {
        public TextView txtName;
        public TextView txtBTCPrice;
        public TextView txtUSDPrice;
        public TextView txtAmount;
        public TextView txtBTCBalance;
        public TextView txtUSDBalance;
        public TextView txtVolume24h;
        public TextView txtChange1h;
        public TextView txtChange24h;
        public TextView txtChange7d;
        public TextView txtVNDBalance;
        View btnPhone, btnEdit, btnDelete, layoutFront;
        SwipeLayout swipeLayout;
    }

    @Override
    public void fillValues(final int position, View convertView) {
        ViewHolder viewHolder = null;
        if(convertView.getTag() != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtVolume24h = (TextView) convertView.findViewById(R.id.txtVolume24h);
            viewHolder.txtBTCPrice = (TextView) convertView.findViewById(R.id.txtBTCPrice);
            viewHolder.txtAmount = (TextView) convertView.findViewById(R.id.txtAmount);
            viewHolder.txtUSDPrice = (TextView) convertView.findViewById(R.id.txtUSDPrice);
            viewHolder.txtBTCBalance = (TextView) convertView.findViewById(R.id.txtBTCBalance);
            viewHolder.txtUSDBalance = (TextView) convertView.findViewById(R.id.txtUSDBalance);
            viewHolder.txtVNDBalance = (TextView) convertView.findViewById(R.id.txtVNDBalance);

            viewHolder.txtChange1h = (TextView) convertView.findViewById(R.id.txtChange1h);
            viewHolder.txtChange24h = (TextView) convertView.findViewById(R.id.txtChange24h);
            viewHolder.txtChange7d = (TextView) convertView.findViewById(R.id.txtChange7d);

            viewHolder.btnPhone = convertView.findViewById(R.id.btnPhone);
            viewHolder.btnEdit = convertView.findViewById(R.id.edit);
            viewHolder.btnDelete = convertView.findViewById(R.id.delete);
            viewHolder.layoutFront = convertView.findViewById(R.id.layoutFront);
            viewHolder.swipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
//            viewHolder.swipe = convertView.findViewById(R.id.swipe);
            convertView.setTag(viewHolder);
        }

        final MyCoin coinMarketModel = getItem(position);

        if (coinMarketModel != null) {
            viewHolder.txtName.setText(coinMarketModel.getName());
            viewHolder.txtVolume24h.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getVolume_24h()) + " $");
            viewHolder.txtAmount.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getAmmount()) + " " + coinMarketModel.getSymbol());
            viewHolder.txtUSDPrice.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getPrice_usd()) + " $");
            viewHolder.txtBTCPrice.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getPrice_btc()) + " BTC");
            viewHolder.txtUSDBalance.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getUSDBalance()) + " $");
            viewHolder.txtBTCBalance.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getBTCBalance()) + " BTC");

            viewHolder.txtVNDBalance.setText("" + StringUtility.convertDoubleToString(coinMarketModel.getUSDBalance() * 22675) + " VNĐ");

            viewHolder.txtChange1h.setText("" + coinMarketModel.getPercent_change_1h() + " %");
            viewHolder.txtChange1h.setTextColor(mContext.getResources().getColor(coinMarketModel.getPercentColor(coinMarketModel.getPercent_change_1h())));

            viewHolder.txtChange24h.setTextColor(mContext.getResources().getColor(coinMarketModel.getPercentColor(coinMarketModel.getPercent_change_24h())));
            viewHolder.txtChange24h.setText("" + coinMarketModel.getPercent_change_24h() + " %");

            viewHolder.txtChange7d.setText("" + coinMarketModel.getPercent_change_7d() + " %");
            viewHolder.txtChange7d.setTextColor(mContext.getResources().getColor(coinMarketModel.getPercentColor(coinMarketModel.getPercent_change_7d())));

            viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyListener.onEdit(coinMarketModel);
//                    EditCoinFragment editCoinFragment = new EditCoinFragment();
//                    editCoinFragment.setMyCoin(coinMarketModel);
//                    baseFragment.addSubFragment(editCoinFragment);
                }
            });
            viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DialogUtility.showDialogConfirm((Activity) mContext, "Are you sure?", new ConfirmListener() {

                        @Override
                        public void doCancel() {

                        }

                        @Override
                        public void doAccept() {

                            GlobalValue.deleteCoin(coinMarketModel);
//                            fetchData(false);
//                            coinAdapter.closeAllItems();
//                            notifyDatasetChanged();
//                            notifyDatasetChanged();
//                            notifyDataSetInvalidated();


                            modifyListener.onDelete(coinMarketModel);
                        }
                    });


                }
            });

            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.layoutFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!coinMarketModel.isOpen()) {
                        modifyListener.onDetail(coinMarketModel);
                    } else {
                        getItem(position).setOpen(false);

                        finalViewHolder.swipeLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finalViewHolder.swipeLayout.close();
                            }
                        }, 100);

                        notifyDataSetChanged();
                    }

                }
            });
        }
    }

    @Override
    public int getCount() {
        return GlobalValue.listMyCoins != null ? GlobalValue.listMyCoins.size() : 0;
    }

    @Override
    public MyCoin getItem(int position) {
        if (GlobalValue.listMyCoins == null || GlobalValue.listMyCoins.isEmpty() || position >= GlobalValue.listMyCoins.size()) {
            return null;
        }

        return GlobalValue.listMyCoins.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
