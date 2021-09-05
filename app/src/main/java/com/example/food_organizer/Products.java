package com.example.food_organizer;

public class Products {
    private String productName;
    private String exDate;
    private String place;
    private String email;


    public Products(String productName,  String exDate,String place,String email) {
        this.productName = productName;
        this.exDate = exDate;
        this.place=place;
        this.email=email;
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
