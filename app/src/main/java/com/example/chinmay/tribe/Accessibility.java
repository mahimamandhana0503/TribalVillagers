package com.example.chinmay.tribe;

/**
 * Created by Chinmay on 26-03-2018.
 */

public class Accessibility {

    private String nameAirport;
    private String nameRailway;
    private String nameBusstop;
    private String  airportLat;
    private String  airportLong;
    private String  railwayLat;
    private String  railwayLong;
    private String  buslat;
    private String  buslong;
    private String  taxi;
    private String  other;


    Accessibility(String nameAirport, String nameRailway, String nameBusstop, String airportLat, String airportLong, String railwayLat,String railwayLong,String buslat, String buslong, String taxi, String other){
        this.setNameAirport(nameAirport);
        this.setNameRailway(nameRailway);
        this.setNameBusstop(nameBusstop);
        this.setAirportLat(airportLat);
        this.setAirportLong(airportLong);
        this.setBuslat(buslat);
        this.setBuslong(buslong);
        this.setRailwayLat(railwayLat);
        this.setRailwayLong(railwayLong);
        this.setTaxi(taxi);
        this.other = other;
    }


    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getNameRailway() {
        return nameRailway;
    }

    public void setNameRailway(String nameRailway) {
        this.nameRailway = nameRailway;
    }

    public String getNameBusstop() {
        return nameBusstop;
    }

    public void setNameBusstop(String nameBusstop) {
        this.nameBusstop = nameBusstop;
    }

    public String getAirportLat() {
        return airportLat;
    }

    public void setAirportLat(String airportLat) {
        this.airportLat = airportLat;
    }

    public String getAirportLong() {
        return airportLong;
    }

    public void setAirportLong(String airportLong) {
        this.airportLong = airportLong;
    }

    public String getRailwayLat() {
        return railwayLat;
    }

    public void setRailwayLat(String railwayLat) {
        this.railwayLat = railwayLat;
    }

    public String getRailwayLong() {
        return railwayLong;
    }

    public void setRailwayLong(String railwayLong) {
        this.railwayLong = railwayLong;
    }

    public String getBuslat() {
        return buslat;
    }

    public void setBuslat(String buslat) {
        this.buslat = buslat;
    }

    public String getBuslong() {
        return buslong;
    }

    public void setBuslong(String buslong) {
        this.buslong = buslong;
    }

    public String getTaxi() {
        return taxi;
    }

    public void setTaxi(String taxi) {
        this.taxi = taxi;
    }
}
