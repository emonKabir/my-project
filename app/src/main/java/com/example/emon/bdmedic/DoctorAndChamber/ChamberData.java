package com.example.emon.bdmedic.DoctorAndChamber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChamberData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;
    @SerializedName("chamber_address")
    @Expose
    private String chamberAddress;
    @SerializedName("cham_dist")
    @Expose
    private Integer chamDist;
    @SerializedName("cham_upa")
    @Expose
    private Integer chamUpa;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("chamber_opens")
    @Expose
    private String chamberOpens;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getChamberAddress() {
        return chamberAddress;
    }

    public void setChamberAddress(String chamberAddress) {
        this.chamberAddress = chamberAddress;
    }

    public Integer getChamDist() {
        return chamDist;
    }

    public void setChamDist(Integer chamDist) {
        this.chamDist = chamDist;
    }

    public Integer getChamUpa() {
        return chamUpa;
    }

    public void setChamUpa(Integer chamUpa) {
        this.chamUpa = chamUpa;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getChamberOpens() {
        return chamberOpens;
    }

    public void setChamberOpens(String chamberOpens) {
        this.chamberOpens = chamberOpens;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
