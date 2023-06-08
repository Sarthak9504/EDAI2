package com.example.myapplication;

public class HospitalInformation {
    private String addressLine1,addressLine2,addressLine3,directorPhoneNo,directorEmail,year;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getDirectorPhoneNo() {
        return directorPhoneNo;
    }

    public void setDirectorPhoneNo(String directorPhoneNo) {
        this.directorPhoneNo = directorPhoneNo;
    }

    public String getDirectorEmail() {
        return directorEmail;
    }

    public void setDirectorEmail(String directorEmail) {
        this.directorEmail = directorEmail;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

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
