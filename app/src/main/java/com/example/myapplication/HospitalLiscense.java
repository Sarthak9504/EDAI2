package com.example.myapplication;

public class HospitalLiscense {

    private String dinNumber,formCNumber;

    public HospitalLiscense() {
    }

    public HospitalLiscense(String dinNumber, String formCNumber) {
        this.dinNumber = dinNumber;
        this.formCNumber = formCNumber;
    }

    public String getDinNumber() {
        return dinNumber;
    }

    public void setDinNumber(String dinNumber) {
        this.dinNumber = dinNumber;
    }

    public String getFormCNumber() {
        return formCNumber;
    }

    public void setFormCNumber(String formCNumber) {
        this.formCNumber = formCNumber;
    }
}
