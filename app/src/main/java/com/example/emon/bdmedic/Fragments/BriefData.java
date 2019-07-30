package com.example.emon.bdmedic.Fragments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BriefData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("disease_management_id")
    @Expose
    private Integer diseaseManagementId;
    @SerializedName("brief")
    @Expose
    private String brief;
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

    public Integer getDiseaseManagementId() {
        return diseaseManagementId;
    }

    public void setDiseaseManagementId(Integer diseaseManagementId) {
        this.diseaseManagementId = diseaseManagementId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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
