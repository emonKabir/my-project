package com.example.emon.bdmedic.ContactList;

import com.google.gson.annotations.SerializedName;

public class Android_Contact {

    //----------------< fritzbox_Contacts() >----------------
//    @SerializedName("name")
    public String name="";
//    @SerializedName("phone_number")
    public String phone_number="" ;
//    @SerializedName("blood_group")
    public String bloodGroup;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public Android_Contact() {
    }

    public Android_Contact(String name, String phone_number,String bloodGroup) {
        this.name = name;
        this.phone_number = phone_number;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
