package com.example.myapplication;

public class ScannedQR {
    private String donorUID;

    public String getScannedData() {
        return donorUID;
    }

    public void setScannedData(String scannedData) {
        this.donorUID = scannedData;
    }

    public ScannedQR(String scannedData) {
        this.donorUID = scannedData;
    }


}
