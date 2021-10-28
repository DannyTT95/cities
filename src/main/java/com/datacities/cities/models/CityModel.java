package com.datacities.cities.models;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class CityModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long geonameid;
    private String name;
    private String country;
    private String state;
    private Double latitude;
    private Double longitude;
    private Double score;

    public Long getGeonameid(){
        return geonameid;
    }

    public void setGeonameid(Long geonameid){
        this.geonameid = geonameid;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public Double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public Double getLongitude(){
        return longitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public Double getScore(){
        return score;
    }

    public void setScore(Double score){
        this.score = score;
    }
}
