package com.phanduy.model.response;

import com.phanduy.model.CustomerModel;

import java.util.ArrayList;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetListCustomerResponse extends ResponseObject{

    ArrayList<CustomerModel> listCustomer;

    public ArrayList<CustomerModel> getListCustomer() {
        return listCustomer;
    }
}
