package com.example.emon.bdmedic.Laboratory.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubPharmacologyData {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent_pharmacologies_id")
    @Expose
    private Integer parentPharmacologiesId;
    @SerializedName("rank")
    @Expose
    private Integer rank;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentPharmacologiesId() {
        return parentPharmacologiesId;
    }

    public void setParentPharmacologiesId(Integer parentPharmacologiesId) {
        this.parentPharmacologiesId = parentPharmacologiesId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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
