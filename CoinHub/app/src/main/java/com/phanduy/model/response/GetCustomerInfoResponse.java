package com.phanduy.model.response;

import com.phanduy.model.CustomerModel;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetCustomerInfoResponse extends ResponseObject{

    CustomerModel customerInfo;

    public CustomerModel getCustomerInfo() {
        return customerInfo;
    }
}
