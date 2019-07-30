package com.example.emon.bdmedic.Laboratory.PojoClass;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacologyInfo {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<PharmacologyInfoData> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PharmacologyInfoData> getData() {
        return data;
    }

    public void setData(List<PharmacologyInfoData> data) {
        this.data = data;
    }

}
