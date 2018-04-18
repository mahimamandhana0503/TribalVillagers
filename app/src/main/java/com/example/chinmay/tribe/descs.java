package com.example.chinmay.tribe;

/**
 * Created by Chinmay on 26-03-2018.
 */

public class descs {

    private String nameOfVillage;
    private String description;
    private String  galleryImage1;
    private String  galleryImage2;
    private String  galleryImage3;
    private String  galleryImage4;
    private String history;
    private String culture;
    private double rating;
    private String speciality;
    private String des;

    descs(String des,String nameOfVillage, String description, String galleryImage, String history, String culture,double rating,String speciality){
        this.setDes(des);
        this.setNameOfVillage(nameOfVillage);
        this.setDescription(description);
        this.setGalleryImage(galleryImage);
        this.setHistory(history);
        this.setCulture(culture);
        this.setRating(rating);
        this.setSpeciality(speciality);

    }

    public String getNameOfVillage() {
        return nameOfVillage;
    }

    public void setNameOfVillage(String nameOfVillage) {
        this.nameOfVillage = nameOfVillage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGalleryImage() {
        return galleryImage1;
    }

    public void setGalleryImage(String galleryImage) {
        galleryImage1 = galleryImage;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGalleryImage2() {
        return galleryImage2;
    }

    public void setGalleryImage2(String galleryImage2) {
        this.galleryImage2 = galleryImage2;
    }

    public String getGalleryImage3() {
        return galleryImage3;
    }

    public void setGalleryImage3(String galleryImage3) {
        this.galleryImage3 = galleryImage3;
    }

    public String getGalleryImage4() {
        return galleryImage4;
    }

    public void setGalleryImage4(String galleryImage4) {
        this.galleryImage4 = galleryImage4;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
