package com.example.emon.bdmedic.PriceCalculator;

public class DrugList {
    private String drugName;
    private String dose;
//    private String star;
    private String quantity;
    private String equal;
    private String total;
    private String dPrice;
    private String currency;
    private String genType;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    private int totalPrice;

    public DrugList(String drugName, String dose, String quantity, String total, String dPrice, String currency,String equal,int totalPrice,String genType) {
        this.drugName = drugName;
        this.dose = dose;
        this.quantity = quantity;
        this.total = total;
        this.dPrice = dPrice;
        this.currency = currency;
        this.equal = equal;
        this.totalPrice = totalPrice;
        this.genType = genType;
    }

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }
    /*  public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }*/

    public String getEqual() {
        return equal;
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }




    public String getdPrice() {
        return dPrice;
    }

    public void setdPrice(String dPrice) {
        this.dPrice = dPrice;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



//    private String currency;

}
