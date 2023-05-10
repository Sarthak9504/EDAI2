package com.example.myapplication;

public class UserHelperClass {

    private String email,mob,password;

    public UserHelperClass() {
    }

    public UserHelperClass(String email, String mob, String password) {
        this.email = email;
        this.mob = mob;
        this.password = password;
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
