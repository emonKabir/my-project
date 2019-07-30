package com.example.emon.bdmedic.Laboratory.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacologyInfoData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("sub_pharmacologies_id")
    @Expose
    private Integer subPharmacologiesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSubPharmacologiesId() {
        return subPharmacologiesId;
    }

    public void setSubPharmacologiesId(Integer subPharmacologiesId) {
        this.subPharmacologiesId = subPharmacologiesId;
    }
}
