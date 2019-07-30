package com.example.emon.bdmedic.DoctorAndChamber;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorAndChamberData implements Parcelable{

    private Integer id;



    public String name;
    private String phone;
    private String sex;
    private Integer bloodGroup;
    private Integer presentDist;
    private Integer presentUpa;
    private Integer permanentDist;
    private Integer permanentUpa;
    private Integer userType;
    private String email;
    private String  fbLink;
    private Integer medicalId;
    private String batchNo;
    private String rollNo;
    private Integer status;
    private String createdAt;
    private String updatedAt;
    private Integer regType;
    /* @SerializedName("user_token")
     @Expose
     private Object userToken;*/
    private Integer doctorId;
    private String bmdcRegNo;
    private String designation;
    private String speciality;
    private String academicCareer;
    private Integer postGradBranch1;
    private Integer postGradBranch2;

    private String chamberAddress;

    private Integer chamDist;
    private Integer chamUpa;
    private String contactNo;
    private String academicStatus;

    public DoctorAndChamberData(String name, String phone, String sex, Integer bloodGroup, Integer presentDist, Integer presentUpa, Integer permanentDist, Integer permanentUpa, Integer userType, String email,  Integer medicalId, String batchNo, String rollNo,Integer regType, Integer doctorId, String bmdcRegNo, String designation, String speciality, String academicCareer, Integer postGradBranch1, Integer postGradBranch2, String chamberAddress, Integer chamDist, Integer chamUpa, String contactNo, String chamberOpens) {

        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.bloodGroup = bloodGroup;
        this.presentDist = presentDist;
        this.presentUpa = presentUpa;
        this.permanentDist = permanentDist;
        this.permanentUpa = permanentUpa;
        this.userType = userType;
        this.email = email;
        this.medicalId = medicalId;
        this.batchNo = batchNo;
        this.rollNo = rollNo;
        this.regType = regType;
        this.doctorId = doctorId;
        this.bmdcRegNo = bmdcRegNo;
        this.designation = designation;
        this.speciality = speciality;
        this.academicCareer = academicCareer;
        this.postGradBranch1 = postGradBranch1;
        this.postGradBranch2 = postGradBranch2;
        this.chamberAddress = chamberAddress;
        this.chamDist = chamDist;
        this.chamUpa = chamUpa;
        this.contactNo = contactNo;
        this.chamberOpens = chamberOpens;

    }

    protected DoctorAndChamberData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        phone = in.readString();
        sex = in.readString();
        if (in.readByte() == 0) {
            bloodGroup = null;
        } else {
            bloodGroup = in.readInt();
        }
        if (in.readByte() == 0) {
            presentDist = null;
        } else {
            presentDist = in.readInt();
        }
        if (in.readByte() == 0) {
            presentUpa = null;
        } else {
            presentUpa = in.readInt();
        }
        if (in.readByte() == 0) {
            permanentDist = null;
        } else {
            permanentDist = in.readInt();
        }
        if (in.readByte() == 0) {
            permanentUpa = null;
        } else {
            permanentUpa = in.readInt();
        }
        if (in.readByte() == 0) {
            userType = null;
        } else {
            userType = in.readInt();
        }
        email = in.readString();
        fbLink = in.readString();
        if (in.readByte() == 0) {
            medicalId = null;
        } else {
            medicalId = in.readInt();
        }
        batchNo = in.readString();
        rollNo = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        if (in.readByte() == 0) {
            regType = null;
        } else {
            regType = in.readInt();
        }
        if (in.readByte() == 0) {
            doctorId = null;
        } else {
            doctorId = in.readInt();
        }
        bmdcRegNo = in.readString();
        designation = in.readString();
        speciality = in.readString();
        academicCareer = in.readString();
        if (in.readByte() == 0) {
            postGradBranch1 = null;
        } else {
            postGradBranch1 = in.readInt();
        }
        if (in.readByte() == 0) {
            postGradBranch2 = null;
        } else {
            postGradBranch2 = in.readInt();
        }
        chamberAddress = in.readString();
        if (in.readByte() == 0) {
            chamDist = null;
        } else {
            chamDist = in.readInt();
        }
        if (in.readByte() == 0) {
            chamUpa = null;
        } else {
            chamUpa = in.readInt();
        }
        contactNo = in.readString();
        chamberOpens = in.readString();
    }

    public static final Creator<DoctorAndChamberData> CREATOR = new Creator<DoctorAndChamberData>() {
        @Override
        public DoctorAndChamberData createFromParcel(Parcel in) {
            return new DoctorAndChamberData(in);
        }

        @Override
        public DoctorAndChamberData[] newArray(int size) {
            return new DoctorAndChamberData[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(Integer bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Integer getPresentDist() {
        return presentDist;
    }

    public void setPresentDist(Integer presentDist) {
        this.presentDist = presentDist;
    }

    public Integer getPresentUpa() {
        return presentUpa;
    }

    public void setPresentUpa(Integer presentUpa) {
        this.presentUpa = presentUpa;
    }

    public Integer getPermanentDist() {
        return permanentDist;
    }

    public void setPermanentDist(Integer permanentDist) {
        this.permanentDist = permanentDist;
    }

    public Integer getPermanentUpa() {
        return permanentUpa;
    }

    public void setPermanentUpa(Integer permanentUpa) {
        this.permanentUpa = permanentUpa;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public Integer getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Integer medicalId) {
        this.medicalId = medicalId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRegType() {
        return regType;
    }

    public void setRegType(Integer regType) {
        this.regType = regType;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getBmdcRegNo() {
        return bmdcRegNo;
    }

    public void setBmdcRegNo(String bmdcRegNo) {
        this.bmdcRegNo = bmdcRegNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAcademicCareer() {
        return academicCareer;
    }

    public void setAcademicCareer(String academicCareer) {
        this.academicCareer = academicCareer;
    }

    public Integer getPostGradBranch1() {
        return postGradBranch1;
    }

    public void setPostGradBranch1(Integer postGradBranch1) {
        this.postGradBranch1 = postGradBranch1;
    }

    public Integer getPostGradBranch2() {
        return postGradBranch2;
    }

    public void setPostGradBranch2(Integer postGradBranch2) {
        this.postGradBranch2 = postGradBranch2;
    }

    public String getChamberAddress() {
        return chamberAddress;
    }

    public void setChamberAddress(String chamberAddress) {
        this.chamberAddress = chamberAddress;
    }

    public Integer getChamDist() {
        return chamDist;
    }

    public void setChamDist(Integer chamDist) {
        this.chamDist = chamDist;
    }

    public Integer getChamUpa() {
        return chamUpa;
    }

    public void setChamUpa(Integer chamUpa) {
        this.chamUpa = chamUpa;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getChamberOpens() {
        return chamberOpens;
    }

    public void setChamberOpens(String chamberOpens) {
        this.chamberOpens = chamberOpens;
    }

    private String chamberOpens;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(sex);
        if (bloodGroup == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bloodGroup);
        }
        if (presentDist == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(presentDist);
        }
        if (presentUpa == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(presentUpa);
        }
        if (permanentDist == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(permanentDist);
        }
        if (permanentUpa == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(permanentUpa);
        }
        if (userType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userType);
        }
        dest.writeString(email);
        dest.writeString(fbLink);
        if (medicalId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(medicalId);
        }
        dest.writeString(batchNo);
        dest.writeString(rollNo);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        if (regType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(regType);
        }
        if (doctorId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(doctorId);
        }
        dest.writeString(bmdcRegNo);
        dest.writeString(designation);
        dest.writeString(speciality);
        dest.writeString(academicCareer);
        if (postGradBranch1 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(postGradBranch1);
        }
        if (postGradBranch2 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(postGradBranch2);
        }
        dest.writeString(chamberAddress);
        if (chamDist == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(chamDist);
        }
        if (chamUpa == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(chamUpa);
        }
        dest.writeString(contactNo);
        dest.writeString(chamberOpens);
    }
}
