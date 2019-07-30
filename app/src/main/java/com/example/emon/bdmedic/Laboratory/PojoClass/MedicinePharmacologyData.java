package com.example.emon.bdmedic.Laboratory.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicinePharmacologyData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("medicine_sub_group_id")
    @Expose
    private Integer medicineSubGroupId;
    @SerializedName("pharmacology_id")
    @Expose
    private Integer pharmacologyId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sub_pharmacology_id")
    @Expose
    private Integer subPharmacologyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicineSubGroupId() {
        return medicineSubGroupId;
    }

    public void setMedicineSubGroupId(Integer medicineSubGroupId) {
        this.medicineSubGroupId = medicineSubGroupId;
    }

    public Integer getPharmacologyId() {
        return pharmacologyId;
    }

    public void setPharmacologyId(Integer pharmacologyId) {
        this.pharmacologyId = pharmacologyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getSubPharmacologyId() {
        return subPharmacologyId;
    }

    public void setSubPharmacologyId(Integer subPharmacologyId) {
        this.subPharmacologyId = subPharmacologyId;
    }

}
