package com.example.emon.bdmedic.Fragments;

import android.os.Parcel;
import android.os.Parcelable;

public class DiseaseData implements Parcelable {

    private String diseaseName;
    private int id;

    protected DiseaseData(Parcel in) {
        diseaseName = in.readString();
        id = in.readInt();
        flag = in.readInt();
    }

    public static final Creator<DiseaseData> CREATOR = new Creator<DiseaseData>() {
        @Override
        public DiseaseData createFromParcel(Parcel in) {
            return new DiseaseData(in);
        }

        @Override
        public DiseaseData[] newArray(int size) {
            return new DiseaseData[size];
        }
    };

    public int getId() {
        return id;
    }

    public int flag = 0;

    public DiseaseData(String diseaseName,int id) {
        this.diseaseName = diseaseName;
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diseaseName);
        dest.writeInt(id);
        dest.writeInt(flag);
    }
}
