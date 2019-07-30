package com.example.emon.bdmedic.Fragments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("discpline_id")
    @Expose
    private Integer discplineId;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
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

    public Integer getDiscplineId() {
        return discplineId;
    }

    public void setDiscplineId(Integer discplineId) {
        this.discplineId = discplineId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
