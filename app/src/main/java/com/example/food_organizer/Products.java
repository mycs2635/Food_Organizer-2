package com.example.food_organizer;

public class Products {
    private String productName;
    private String imgUrl;
    private String exDate;


    public Products(String productName, String imgUrl, String exDate) {
        this.productName = productName;
        this.imgUrl = imgUrl;
        this.exDate = exDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }
}
