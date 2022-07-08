package com.example.wyw;

public class Modal {

    private String address,phone,requirements;

    public Modal(String address,String phone,String requirements) {
        this.address = address;
        this.phone=phone;
        this.requirements=requirements;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
