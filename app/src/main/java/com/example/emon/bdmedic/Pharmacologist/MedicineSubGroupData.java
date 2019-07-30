package com.example.emon.bdmedic.Pharmacologist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineSubGroupData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("generic_name")
    @Expose
    private String genericName;
    @SerializedName("pregnency_category")
    @Expose
    private String pregnencyCategory;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("medicine_subgroup_id")
    @Expose
    private Integer medicine_subgroup_id;

    public Integer getMedicine_subgroup_id() {
        return medicine_subgroup_id;
    }

    public void setMedicine_subgroup_id(Integer medicine_subgroup_id) {
        this.medicine_subgroup_id = medicine_subgroup_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getPregnencyCategory() {
        return pregnencyCategory;
    }

    public void setPregnencyCategory(String pregnencyCategory) {
        this.pregnencyCategory = pregnencyCategory;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
