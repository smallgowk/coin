package com.phanduy.model.request.coinmarketcal;

import com.phanduy.GlobalInfo;
import com.phanduy.model.request.BaseGetRequest;
import com.phanduy.store.AppSharePreference;

/**
 * Created by duyuno on 7/25/17.
 */
public class GetListCalCoins extends BaseGetRequest{

    @Override
    public String genUrl() {
        return GlobalInfo.CoinmarkCalendar.BASE_RESOURCE_URL + "coins?access_token=" + params.get(0);
    }
}
