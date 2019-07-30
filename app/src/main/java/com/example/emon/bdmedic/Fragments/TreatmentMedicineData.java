package com.example.emon.bdmedic.Fragments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreatmentMedicineData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("treat_brief_id")
    @Expose
    private Integer treatBriefId;
    @SerializedName("medicine_id")
    @Expose
    private Integer medicineId;
    @SerializedName("dosage_id")
    @Expose
    private Integer dosageId;
    @SerializedName("sponsor_rank")
    @Expose
    private Integer sponsorRank;
    @SerializedName("dose_frequency")
    @Expose
    private String doseFrequency;
    @SerializedName("relation")
    @Expose
    private String relation;
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

    public Integer getTreatBriefId() {
        return treatBriefId;
    }

    public void setTreatBriefId(Integer treatBriefId) {
        this.treatBriefId = treatBriefId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getDosageId() {
        return dosageId;
    }

    public void setDosageId(Integer dosageId) {
        this.dosageId = dosageId;
    }

    public Integer getSponsorRank() {
        return sponsorRank;
    }

    public void setSponsorRank(Integer sponsorRank) {
        this.sponsorRank = sponsorRank;
    }

    public String getDoseFrequency() {
        return doseFrequency;
    }

    public void setDoseFrequency(String doseFrequency) {
        this.doseFrequency = doseFrequency;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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
