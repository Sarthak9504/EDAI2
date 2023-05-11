package com.example.myapplication;

public class HospitalInformation {
    private String addressLine1,addressLine2,addressLine3,directorPhoneNo,directorEmail,year;

    public HospitalInformation() {
    }

    public HospitalInformation(String addLine1, String addLine2, String addLine3) {
        this.addressLine1 = addLine1;
        this.addressLine2 = addLine2;
        this.addressLine3 = addLine3;
    }

    public HospitalInformation(String directorPhoneNo, String directorEmail) {
        this.directorPhoneNo = directorPhoneNo;
        this.directorEmail = directorEmail;
    }

    public HospitalInformation(String year) {
        this.year = year;
    }


}
