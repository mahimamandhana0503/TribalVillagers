package com.example.chinmay.tribe;

/**
 * Created by Chinmay on 26-03-2018.
 */

public class Clothing {

    private String nameOfOutFit;
    private String cost;
    private String imageOfCloth;
    private String fabricUsed;
    private String shopName;
    private String shopLat;
    private String shopLong;
    private String rating;

    Clothing(String nameOfOutFit, String cost, String imageOfCloth, String fabricUsed, String shopName, String shopLat, String shopLong, String rating){
        this.nameOfOutFit = nameOfOutFit;
        this.cost = cost;
        this.imageOfCloth = imageOfCloth;
        this.fabricUsed = fabricUsed;
        this.shopName = shopName;
        this.shopLat = shopLat;
        this.shopLong = shopLong;
        this.rating = rating;
    }

    public String getNameOfOutFit() {
        return nameOfOutFit;
    }

    public void setNameOfOutFit(String nameOfOutFit) {
        this.nameOfOutFit = nameOfOutFit;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImageOfCloth() {
        return imageOfCloth;
    }

    public void setImageOfCloth(String imageOfCloth) {
        this.imageOfCloth = imageOfCloth;
    }

    public String getFabricUsed() {
        return fabricUsed;
    }

    public void setFabricUsed(String fabricUsed) {
        this.fabricUsed = fabricUsed;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLat() {
        return shopLat;
    }

    public void setShopLat(String shopLat) {
        this.shopLat = shopLat;
    }

    public String getShopLong() {
        return shopLong;
    }

    public void setShopLong(String shopLong) {
        this.shopLong = shopLong;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}