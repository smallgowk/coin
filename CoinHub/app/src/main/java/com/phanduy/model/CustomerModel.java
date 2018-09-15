package com.phanduy.model;

import android.os.Parcel;

/**
 * Created by duyuno on 7/11/17.
 */
public class CustomerModel extends PBXModel {
    private boolean isOpen;

    private int customerId;
    private String customerName;
    private String numberOfExt;
    private String companyName;
    private String position;
    private String phoneNumber;
    private String phoneNumber2;
    private String phoneNumber3;
    private String email;

    public boolean isSelected;

    public CustomerModel() {

    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getNumberOfExt() {
        return numberOfExt;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public String getEmail() {
        return email;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setNumberOfExt(String numberOfExt) {
        this.numberOfExt = numberOfExt;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(customerId);
        dest.writeString(customerName);
        dest.writeString(numberOfExt);
        dest.writeString(companyName);
        dest.writeString(phoneNumber);

    }

    // Parcelling part
    public CustomerModel(Parcel in) {
        this.customerId = in.readInt();
        this.customerName = in.readString();
        this.numberOfExt = in.readString();
        this.companyName = in.readString();
        this.phoneNumber = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Creator CREATOR = new Creator() {
        public CustomerModel createFromParcel(Parcel in) {
            return new CustomerModel(in);
        }

        public CustomerModel[] newArray(int size) {
            return new CustomerModel[size];
        }
    };

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public void changeSelected() {
        isSelected = !isSelected;
    }
}
