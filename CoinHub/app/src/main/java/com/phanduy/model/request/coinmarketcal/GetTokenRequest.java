package com.phanduy.model.request.coinmarketcal;

import com.phanduy.GlobalInfo;
import com.phanduy.model.request.BaseGetRequest;

/**
 * Created by duyuno on 4/20/18.
 */
public class GetTokenRequest extends BaseGetRequest {

    @Override
    public String genUrl() {
        return GlobalInfo.CoinmarkCalendar.AUTHEN_URL + "?grant_type=client_credentials&client_id=" + GlobalInfo.CoinmarkCalendar.CLIENT_ID + "&client_secret=" + GlobalInfo.CoinmarkCalendar.CLIENT_SECRET;
    }
}
