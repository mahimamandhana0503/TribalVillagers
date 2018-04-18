package com.example.chinmay.tribe;

/**
 * Created by Chinmay on 26-03-2018.
 */

public class Address {
    private String houseno;
    private String locality;
    private String street;
    private String district;
    private String pincode;
    private String post;
    private String state;
    private String country;


    Address(String houseno, String locality, String street, String district,String pincode, String post, String state, String country){
        this.houseno = houseno;
        this.locality = locality;
        this.street = street;
        this.district = district;
        this.pincode = pincode;
        this.post = post;
        this.state = state;
        this.country = country;
    }




    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}


