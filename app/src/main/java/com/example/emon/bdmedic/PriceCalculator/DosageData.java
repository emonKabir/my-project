package com.example.emon.bdmedic.PriceCalculator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DosageData {



    private int id;
    private int medicine_id;
    private String generic_type;
    private String specific_type;
    private String unit;
    private String package_method;
    private String package_price;
    private String unit_price;

    private String created_at;

    private String updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getGeneric_type() {
        return generic_type;
    }

    public void setGeneric_type(String generic_type) {
        this.generic_type = generic_type;
    }

    public String getSpecific_type() {
        return specific_type;
    }

    public void setSpecific_type(String specific_type) {
        this.specific_type = specific_type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPackage_method() {
        return package_method;
    }

    public void setPackage_method(String package_method) {
        this.package_method = package_method;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }
}
