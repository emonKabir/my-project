package com.example.emon.bdmedic.Fragments.ShowMedicineDetails;

public class MedicineDetails {

    private String firstTextView ;
    private String secondTextView;
    private String thirdTextView ;
    private String genericName ;
    private String medicineSubGroupId ;

    public String getMedicineSubGroupId() {
        return medicineSubGroupId;
    }

    public void setMedicineSubGroupId(String medicineSubGroupId) {
        this.medicineSubGroupId = medicineSubGroupId;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public MedicineDetails(String firstTextView, String secondTextView, String thirdTextView,String genericName,String medicineSubGroupId) {
        this.firstTextView = firstTextView;
        this.secondTextView = secondTextView;
        this.thirdTextView = thirdTextView;
        this.genericName = genericName;
        this.medicineSubGroupId = medicineSubGroupId;

    }

    public String getFirstTextView() {
        return firstTextView;
    }

    public void setFirstTextView(String firstTextView) {
        this.firstTextView = firstTextView;
    }

    public String getSecondTextView() {
        return secondTextView;
    }

    public void setSecondTextView(String secondTextView) {
        this.secondTextView = secondTextView;
    }

    public String getThirdTextView() {
        return thirdTextView;
    }

    public void setThirdTextView(String thirdTextView) {
        this.thirdTextView = thirdTextView;
    }
}
