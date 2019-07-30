package com.example.emon.bdmedic.PriceCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medicine {
    private Integer status;
    private String message;
    private List<MedicineData> data;
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

    public List<MedicineData> getData() {
        return data;
    }

    public void setData(List<MedicineData> data) {
        this.data = data;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
