package com.example.chinmay.tribe;



/**
 * Created by Chinmay on 26-03-2018.
 */

public class Accomodation {
    private Address address;
    private String price;
    private String h_name;
    private String contact;
    private int capacityEachRoom;
    private String[] image;
    private int roomsAvailable;
    private String email;
    private int rating;


    Accomodation(Address address, String price, String h_name, String contact, int capacityEachRoom, String[] image, int roomsAvailable,String email,int rating){
        this.address = address;
        this.price = price;
        this.h_name = h_name;
        this.contact = contact;
        this.capacityEachRoom = capacityEachRoom;
        this.image = image;
        this.roomsAvailable = roomsAvailable;
        this.email = email;
        this.rating = rating;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getCapacityEachRoom() {
        return capacityEachRoom;
    }

    public void setCapacityEachRoom(int capacityEachRoom) {
        this.capacityEachRoom = capacityEachRoom;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

