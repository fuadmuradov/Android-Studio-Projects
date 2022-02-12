package com.fuadmuradov.landmarkbook;

import java.io.Serializable;

public class LandMark implements Serializable {
    String country;
    String monument;
    int image;

    public LandMark(String country, String monument, int image) {
        this.country = country;
        this.monument = monument;
        this.image = image;
    }



}
