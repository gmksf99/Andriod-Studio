package com.cookandroid.app2018316023n5;

public class CustomerItem {
    private String customerName;
    private String orderItem;
    private String orderSum;
    private String orderDay;
    private int orderID; //그림아이디
    public CustomerItem(){}

    public CustomerItem(String customerName, String orderItem, String orderSum, String orderDay, int orderID){
        this.customerName = customerName;
        this.orderItem = orderItem;
        this.orderSum = orderSum;
        this.orderDay = orderDay;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public String getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(String orderSum) {
        this.orderSum = orderSum;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }
}
