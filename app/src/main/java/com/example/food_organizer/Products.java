package com.example.food_organizer;

public class Products {
    private String productName;
    private String exDate;
    private String place;
    private String phone;


    public Products(String productName,  String exDate,String place,String phone) {
        this.productName = productName;
        this.exDate = exDate;
        this.place=place;
        this.phone=phone;
    }

    public Products(String productName, String exDate, String place) {
        this.productName = productName;
        this.exDate = exDate;
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Products(){

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String imgUrl) {
        this.place = place;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

}
