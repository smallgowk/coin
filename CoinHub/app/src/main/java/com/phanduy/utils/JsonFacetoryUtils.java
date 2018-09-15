package com.phanduy.utils;

import android.support.annotation.Nullable;

import com.phanduy.model.coinmarket.CoinModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by duyuno on 4/15/18.
 */
public class JsonFacetoryUtils {

    @Nullable
    public static ArrayList<CoinModel> parseData(String json) {
        ArrayList<CoinModel> list = new ArrayList<>();

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(json);
        } catch (Exception ex) {
            return null;
        }

        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                CoinModel coinModel = new CoinModel();

                coinModel.setId(jsonObject.getString("id"));
                coinModel.setSymbol(jsonObject.getString("symbol"));
                coinModel.setName(jsonObject.getString("name"));
                coinModel.setPrice_btc(jsonObject.getDouble("price_btc"));
                coinModel.setPrice_usd(jsonObject.getDouble("price_usd"));
                coinModel.setVolume_24h(jsonObject.getDouble("24h_volume_usd"));
                coinModel.setPercent_change_1h(jsonObject.getDouble("percent_change_1h"));
                coinModel.setPercent_change_24h(jsonObject.getDouble("percent_change_24h"));
                coinModel.setPercent_change_7d(jsonObject.getDouble("percent_change_7d"));

                list.add(coinModel);
            } catch (Exception ex) {
            }


        }


        return list;
    }
}
