package com.example.emon.bdmedic.Laboratory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Investigation {

    private Integer status;
    private String message;
    private List<InvestigationData> data = null ;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public List<InvestigationData> getData() {
        return data;
    }

    public void setData(List<InvestigationData> data) {
        this.data = data;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



}
