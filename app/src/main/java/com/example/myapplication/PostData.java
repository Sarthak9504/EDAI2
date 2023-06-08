package com.example.myapplication;

public class PostData {
    private String hospitalName,patientName,patientAge,patientGender,bloodGroup,disease,organType,organSize;

    public PostData(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType;
    }

    public String getOrganSize() {
        return organSize;
    }

    public void setOrganSize(String organSize) {
        this.organSize = organSize;
    }

    public PostData(String hospitalName,String patientName, String patientAge, String patientGender, String bloodGroup, String disease, String organType, String organSize) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.bloodGroup = bloodGroup;
        this.disease = disease;
        this.organType = organType;
        this.organSize = organSize;
        this.hospitalName = hospitalName;
    }
}
