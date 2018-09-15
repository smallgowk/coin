package com.phanduy.model.coinmarket;

/**
 * Created by duyuno on 4/29/18.
 */
public class GlobalMarket {
    private double total_market_cap_usd;
    private double total_24h_volume_usd;
    private double bitcoin_percentage_of_market_cap;
    private int active_currencies;
    private int active_assets;
    private int active_markets;

    public double getTotal_market_cap_usd() {
        return total_market_cap_usd;
    }

    public void setTotal_market_cap_usd(double total_market_cap_usd) {
        this.total_market_cap_usd = total_market_cap_usd;
    }

    public double getTotal_24h_volume_usd() {
        return total_24h_volume_usd;
    }

    public void setTotal_24h_volume_usd(double total_24h_volume_usd) {
        this.total_24h_volume_usd = total_24h_volume_usd;
    }

    public double getBitcoin_percentage_of_market_cap() {
        return bitcoin_percentage_of_market_cap;
    }

    public void setBitcoin_percentage_of_market_cap(double bitcoin_percentage_of_market_cap) {
        this.bitcoin_percentage_of_market_cap = bitcoin_percentage_of_market_cap;
    }

    public int getActive_currencies() {
        return active_currencies;
    }

    public void setActive_currencies(int active_currencies) {
        this.active_currencies = active_currencies;
    }

    public int getActive_assets() {
        return active_assets;
    }

    public void setActive_assets(int active_assets) {
        this.active_assets = active_assets;
    }

    public int getActive_markets() {
        return active_markets;
    }

    public void setActive_markets(int active_markets) {
        this.active_markets = active_markets;
    }
}
