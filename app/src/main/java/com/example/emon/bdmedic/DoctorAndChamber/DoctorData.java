package com.example.emon.bdmedic.DoctorAndChamber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("blood_group")
    @Expose
    private Integer bloodGroup;
    @SerializedName("present_dist")
    @Expose
    private Integer presentDist;
    @SerializedName("present_upa")
    @Expose
    private Integer presentUpa;
    @SerializedName("permanent_dist")
    @Expose
    private Integer permanentDist;
    @SerializedName("permanent_upa")
    @Expose
    private Integer permanentUpa;
    @SerializedName("user_type")
    @Expose
    private Integer userType;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fb_link")
    @Expose
    private String  fbLink;
    @SerializedName("medical_id")
    @Expose
    private Integer medicalId;
    @SerializedName("batch_no")
    @Expose
    private String batchNo;
    @SerializedName("roll_no")
    @Expose
    private String rollNo;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("reg_type")
    @Expose
    private Integer regType;
   /* @SerializedName("user_token")
    @Expose
    private Object userToken;*/
    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;
    @SerializedName("bmdc_reg_no")
    @Expose
    private String bmdcRegNo;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("academic_career")
    @Expose
    private String academicCareer;
    @SerializedName("post_grad_branch_1")
    @Expose
    private Integer postGradBranch1;
    @SerializedName("post_grad_branch_2")
    @Expose
    private Integer postGradBranch2;

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
}
