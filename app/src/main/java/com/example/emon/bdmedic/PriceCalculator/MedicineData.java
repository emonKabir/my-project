package com.example.emon.bdmedic.PriceCalculator;

import java.util.HashMap;
import java.util.Map;

public class MedicineData {
    private int id;
    private int medicine_sub_group_id;
    private int company_id;
    private String sub_generic_name;
    private String name;
    private String company_name;
    private String generic_name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMedicine_sub_group_id() {
        return medicine_sub_group_id;
    }

    public void setMedicine_sub_group_id(int medicine_sub_group_id) {
        this.medicine_sub_group_id = medicine_sub_group_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getSub_generic_name() {
        return sub_generic_name;
    }

    public void setSub_generic_name(String sub_generic_name) {
        this.sub_generic_name = sub_generic_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
