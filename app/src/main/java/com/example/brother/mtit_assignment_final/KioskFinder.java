package com.example.brother.mtit_assignment_final;

import java.io.Serializable;

/**
 * Created by Brother on 29/05/2017.
 */
public class KioskFinder  implements Serializable {

    private String kioskId;
    private String kioskName;
    private String description;
    private double kioskLat;
    private double kioskLng;

    public KioskFinder(){

    }

    public KioskFinder(String kioskId, String kioskName, String description, double kioskLat, double kioskLng) {
        this.kioskId = kioskId;
        this.kioskName = kioskName;
        this.description = description;
        this.kioskLat = kioskLat;
        this.kioskLng = kioskLng;
    }

    public String getKioskId() {
        return kioskId;
    }

    public void setKioskId(String kioskId) {
        this.kioskId = kioskId;
    }

    public String getKioskName() {
        return kioskName;
    }

    public void setKioskName(String kioskName) {
        this.kioskName = kioskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKioskLat() {
        return kioskLat;
    }

    public void setKioskLat(double kioskLat) {
        this.kioskLat = kioskLat;
    }

    public double getKioskLng() {
        return kioskLng;
    }

    public void setKioskLng(double kioskLng) {
        this.kioskLng = kioskLng;
    }
}
