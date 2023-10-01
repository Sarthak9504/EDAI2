package com.example.myapplication;

public class UserHelperClass {

    private String email,mob,password,adminPhoneNumber,adminEmail,hospitalName;
    private String donorName,donorEmail,donorMobile,donorAddress,donorAge,donorBloodGroup,donorGender,disease,donorWeight,adhharNumber,donorPassword;

    public UserHelperClass(String donorName, String donorEmail, String donorMobile, String donorAddress, String donorAge, String donorBloodGroup, String donorGender, String disease, String donorWeight, String adhharNumber, String donorPassword) {
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorMobile = donorMobile;
        this.donorAddress = donorAddress;
        this.donorAge = donorAge;
        this.donorBloodGroup = donorBloodGroup;
        this.donorGender = donorGender;
        this.disease = disease;
        this.donorWeight = donorWeight;
        this.adhharNumber = adhharNumber;
        this.donorPassword = donorPassword;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getDonorMobile() {
        return donorMobile;
    }

    public void setDonorMobile(String donorMobile) {
        this.donorMobile = donorMobile;
    }

    public String getDonorAddress() {
        return donorAddress;
    }

    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    public String getDonorAge() {
        return donorAge;
    }

    public void setDonorAge(String donorAge) {
        this.donorAge = donorAge;
    }

    public String getDonorBloodGroup() {
        return donorBloodGroup;
    }

    public void setDonorBloodGroup(String donorBloodGroup) {
        this.donorBloodGroup = donorBloodGroup;
    }

    public String getDonorGender() {
        return donorGender;
    }

    public void setDonorGender(String donorGender) {
        this.donorGender = donorGender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDonorWeight() {
        return donorWeight;
    }

    public void setDonorWeight(String donorWeight) {
        this.donorWeight = donorWeight;
    }

    public String getAdhharNumber() {
        return adhharNumber;
    }

    public void setAdhharNumber(String adhharNumber) {
        this.adhharNumber = adhharNumber;
    }

    public String getDonorPassword() {
        return donorPassword;
    }

    public void setDonorPassword(String donorPassword) {
        this.donorPassword = donorPassword;
    }

    public UserHelperClass() {
    }

    public UserHelperClass(String hospitalName,String email, String mob, String password) {
        this.hospitalName = hospitalName;
        this.email = email;
        this.mob = mob;
        this.password = password;
    }

    public UserHelperClass(String adminPhoneNumber, String adminEmail) {
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminEmail = adminEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
