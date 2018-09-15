package com.phanduy.model.request;

import com.phanduy.GlobalInfo;

/**
 * Created by duyuno on 7/25/17.
 */
public class GetCMGlobal extends BaseGetRequest{
    @Override
    public String genUrl() {
        return GlobalInfo.ServerConfig.CM_DOMAIN + "global/";
    }
}
