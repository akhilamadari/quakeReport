package com.example.android.quakereport;

/**
 * Created by akhilamadari on 12/16/17.
 */

public class Quake {
    private double tMagnitude;
    private String tparameter2;
    private Long tTimeInMS;
    private  String tUrl;

    public Quake (double magnitude, String parameter2, Long timeInMS,String url){
        tMagnitude = magnitude;
        tparameter2 =parameter2;
        tTimeInMS = timeInMS;
        tUrl = url;

    }
    public double gettMagnitude(){
        return tMagnitude;

    }
    public  String getTparameter2(){
        return tparameter2;
    }

    public Long getTimeInMS(){
        return tTimeInMS;
    }

    public String gettUrl(){
        return tUrl;
    }


}
