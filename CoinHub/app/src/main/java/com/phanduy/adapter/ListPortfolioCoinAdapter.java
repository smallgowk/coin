package com.phanduy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phanduy.coinhub.R;
import com.phanduy.interfaces.ModifyListener;
import com.phanduy.model.MyCoin;
import com.phanduy.store.GlobalValue;
import com.phanduy.utils.StringUtility;

public class ListPortfolioCoinAdapter extends BaseAdapter {

    private Context mContext;

    ModifyListener modifyListener;

    public ListPortfolioCoinAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setModifyListener(ModifyListener modifyListener) {
        this.modifyListener = modifyListener;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_portfolio, null);
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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
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
//
//            viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    modifyListener.onEdit(coinMarketModel);
////                    EditCoinFragment editCoinFragment = new EditCoinFragment();
////                    editCoinFragment.setMyCoin(coinMarketModel);
////                    baseFragment.addSubFragment(editCoinFragment);
//                }
//            });
//            viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    DialogUtility.showDialogConfirm((Activity) mContext, "Are you sure?", new ConfirmListener() {
//
//                        @Override
//                        public void doCancel() {
//
//                        }
//
//                        @Override
//                        public void doAccept() {
//
//                            GlobalValue.deleteCoin(coinMarketModel);
////                            fetchData(false);
////                            coinAdapter.closeAllItems();
////                            notifyDatasetChanged();
////                            notifyDatasetChanged();
////                            notifyDataSetInvalidated();
//
//
//                            modifyListener.onDelete(coinMarketModel);
//                        }
//                    });
//
//
//                }
//            });
        }

        return convertView;
    }
}
