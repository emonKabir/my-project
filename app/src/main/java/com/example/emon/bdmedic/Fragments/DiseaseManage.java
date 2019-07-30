package com.example.emon.bdmedic.Fragments;

import java.util.List;

public class DiseaseManage {
    private Integer status;
    private String message;
    List<DiseaseManageData>data;

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

    public List<DiseaseManageData> getData() {
        return data;
    }

    public void setData(List<DiseaseManageData> data) {
        this.data = data;
    }
}
