package com.phanduy.model.request;

import com.phanduy.GlobalInfo;

/**
 * Created by duyuno on 7/25/17.
 */
public class GetETHHistory extends BaseGetRequest{
    @Override
    public String genUrl() {
        return GlobalInfo.ServerConfig.ETH_DOMAIN + "historical/close.json?start=" + params.get(0) + "&end=" + params.get(1);
    }
}
