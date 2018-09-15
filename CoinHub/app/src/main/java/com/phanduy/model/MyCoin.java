package com.phanduy.model;

import com.phanduy.model.coinmarket.CoinModel;

/**
 * Created by duyuno on 4/11/18.
 */
public class MyCoin extends CoinModel {
    private long rowId;
    private double ammount;


    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public void copyData(CoinModel coinModel) {
        setPrice_btc(coinModel.getPrice_btc());
        setPrice_usd(coinModel.getPrice_usd());
        setPercent_change_24h(coinModel.getPercent_change_24h());
        setPercent_change_1h(coinModel.getPercent_change_1h());
        setPercent_change_7d(coinModel.getPercent_change_7d());
        setVolume_24h(coinModel.getVolume_24h());
        setRank(coinModel.getRank());
        setId(coinModel.getId());
        setName(coinModel.getName());
        setSymbol(coinModel.getSymbol());
    }

    public double getUSDBalance() {
        return ammount * getPrice_usd();
    }

    public double getBTCBalance() {
        return ammount * getPrice_btc();
    }
}
