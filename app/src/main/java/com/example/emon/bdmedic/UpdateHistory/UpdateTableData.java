package com.example.emon.bdmedic.UpdateHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class UpdateTableData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("table_name")
    @Expose
    private String tableName;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
