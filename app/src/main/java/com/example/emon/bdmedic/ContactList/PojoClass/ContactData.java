package com.example.emon.bdmedic.ContactList.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactData {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;

    public ContactData(String name, String phone, String bloodGroup) {
        this.name = name;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
