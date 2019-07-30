package com.example.emon.bdmedic.Laboratory.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvestigationPriceData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("investigation_id")
    @Expose
    private Integer investigationId;
    @SerializedName("hospital_id")
    @Expose
    private Integer hospitalId;
    @SerializedName("price")
    @Expose
    private String price;
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

    public Integer getInvestigationId() {
        return investigationId;
    }

    public void setInvestigationId(Integer investigationId) {
        this.investigationId = investigationId;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
