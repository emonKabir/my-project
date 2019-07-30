package com.example.emon.bdmedic.Pharmacologist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DrugsData  implements Parcelable {
    private String name;
    private String companyName;
    private String genericType;

    String sss;
    private List<String>unit;


public int priceShowFlag = 0;
public int favShowFlag = 0;

    private String unitPrice;
    private String packagePrice;
    private String packageMethod;
    private String generciName;
    private String medicineSubGroupId;
    private int id;

    protected DrugsData(Parcel in) {
        name = in.readString();
        companyName = in.readString();
        genericType = in.readString();
        sss = in.readString();
        unit = in.createStringArrayList();
        priceShowFlag = in.readInt();
        favShowFlag = in.readInt();
        unitPrice = in.readString();
        packagePrice = in.readString();
        packageMethod = in.readString();
        generciName = in.readString();
        medicineSubGroupId = in.readString();
        id = in.readInt();
    }

    public static final Creator<DrugsData> CREATOR = new Creator<DrugsData>() {
        @Override
        public DrugsData createFromParcel(Parcel in) {
            return new DrugsData(in);
        }

        @Override
        public DrugsData[] newArray(int size) {
            return new DrugsData[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getMedicineSubGroupId() {
        return medicineSubGroupId;
    }

    public void setMedicineSubGroupId(String medicineSubGroupId) {
        this.medicineSubGroupId = medicineSubGroupId;
    }



    public DrugsData(int id,String name, String companyName, String genericType, String generciName, String sss,String unitPrice,String packagePrice,String medicineSubGroupId ) {
        this.name = name;
        this.companyName = companyName;
        this.genericType = genericType;
        this.generciName = generciName;
//        this.unit = unit;
        this.sss = sss;
        this.unitPrice = unitPrice;
        this.packagePrice = packagePrice;
        this.medicineSubGroupId = medicineSubGroupId;
        this.id = id;
    }

    public DrugsData(int id,String name, String companyName, String genericType, String generciName, String sss,String unitPrice,String packagePrice ) {
        this.name = name;
        this.companyName = companyName;
        this.genericType = genericType;
        this.generciName = generciName;
//        this.unit = unit;
        this.sss = sss;
        this.unitPrice = unitPrice;
        this.packagePrice = packagePrice;
        this.id = id;

    }

    public String getSss() {
        return sss;
    }

    public void setSss(String sss) {
        this.sss = sss;
    }
    public List<String> getUnit() {
        return unit;
    }

    public void setUnit(List<String> unit) {
        this.unit = unit;
    }

    public String getGenerciName() {
        return generciName;
    }

    public void setGenerciName(String generciName) {
        this.generciName = generciName;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGenericType() {
        return genericType;
    }

    public void setGenericType(String genericType) {
        this.genericType = genericType;
    }



    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageMethod() {
        return packageMethod;
    }

    public void setPackageMethod(String packageMethod) {
        this.packageMethod = packageMethod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(companyName);
        dest.writeString(genericType);
        dest.writeString(sss);
        dest.writeStringList(unit);
        dest.writeInt(priceShowFlag);
        dest.writeInt(favShowFlag);
        dest.writeString(unitPrice);
        dest.writeString(packagePrice);
        dest.writeString(packageMethod);
        dest.writeString(generciName);
        dest.writeString(medicineSubGroupId);
        dest.writeInt(id);
    }
}
