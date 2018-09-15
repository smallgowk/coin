package com.phanduy.interfaces;

import com.phanduy.model.coinmarket.CoinModel;

/**
 * Created by duyuno on 4/21/18.
 */
public interface ModifyListener {
    public void onDelete(Object object);
    public void onEdit(Object object);
    public void onDetail(Object object);
}
