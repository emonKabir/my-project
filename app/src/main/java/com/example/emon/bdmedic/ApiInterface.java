package com.example.emon.bdmedic;




import com.example.emon.bdmedic.ContactList.PojoClass.ContactPjo;
import com.example.emon.bdmedic.DoctorAndChamber.Chamber;
import com.example.emon.bdmedic.DoctorAndChamber.Doctor;
import com.example.emon.bdmedic.Fragments.Branch;
import com.example.emon.bdmedic.Fragments.Brief;
import com.example.emon.bdmedic.Fragments.Discipline;
import com.google.gson.JsonObject;
import com.example.emon.bdmedic.Fragments.DiseaseManage;
import com.example.emon.bdmedic.Fragments.RevisedBy;
import com.example.emon.bdmedic.Fragments.TreatmentMedicine;
import com.example.emon.bdmedic.Laboratory.Investigation;
import com.example.emon.bdmedic.Laboratory.PojoClass.Hospital;
import com.example.emon.bdmedic.Laboratory.PojoClass.InvestigationPrice;
import com.example.emon.bdmedic.Laboratory.PojoClass.MedicinePharmacology;
import com.example.emon.bdmedic.Laboratory.PojoClass.PharmacologyInfo;
import com.example.emon.bdmedic.Laboratory.PojoClass.SubPharmacology;
import com.example.emon.bdmedic.Login.LoginVerify;
import com.example.emon.bdmedic.Media.MediaPojoClass.Media;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.Category;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.Sponsor;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemCategory;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.Systems;
import com.example.emon.bdmedic.Pharmacologist.MedicineSubGroup;
import com.example.emon.bdmedic.PriceCalculator.Dosage;
import com.example.emon.bdmedic.PriceCalculator.Medicine;
import com.example.emon.bdmedic.UpdateHistory.UpdateTable;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "http://bdmedics.com";

    @GET("/investigation/getInv/{date}")
        // API's endpoints
    Call<Investigation> getInvestigation(@Path("date") String date);

    @GET("/allHomeContent/{date}")
        // API's endpoints
    Call<Media> getMedia(@Path("date") String date);

    @GET("/allMedicines/{date}")
        // API's endpoints
    Call<Medicine> getMedicine(@Path("date") String date);

    @GET("/allMedicineDosage/{date}")
        // API's endpoints
    Call<Dosage> getDosage(@Path("date") String date);

    @GET("/allMedicineSubGroup/{date}")
        // API's endpoints
    Call<MedicineSubGroup> getSystem(@Path("date") String date);


    @GET("/allDiseaseManage/{date}")
        // API's endpoints
    Call<DiseaseManage> getDisease(@Path("date") String date);



    @GET("/allBranch/{date}")
        // API's endpoints
    Call<Branch> getBranch(@Path("date") String date);

    @GET("/medicine_group/getSystem/{date}")
        // API's endpoints
    Call<Systems> getSystemData(@Path("date") String date);

    @GET("/medicine_group/getCategory/{date}")
        // API's endpoints
    Call<Category> getCategoryData(@Path("date") String date);

    @GET("/allSystemCategory/{date}")
        // API's endpoints
    Call<SystemCategory> getSystemCategoryData(@Path("date") String date);

    @GET("/allDiscipline/{date}")
        // API's endpoints
    Call<Discipline> getDiscipline(@Path("date") String date);

    @GET("/allBrief/{date}")
        // API's endpoints
    Call<Brief> getBrief(@Path("date") String date);

    @GET("/allHospital/{date}")
        // API's endpoints
    Call<Hospital> getHospital(@Path("date") String date);

    @GET("/allInvestigationPrice/{date}")
        // API's endpoints
    Call<InvestigationPrice> getInvestigationPrice(@Path("date") String date);

    @GET("/allMedicinePharmacology/{date}")
        // API's endpoints

    Call<MedicinePharmacology> getMedicinePharmacology(@Path("date") String date);
    @GET("/allPharmacology/{date}")
        // API's endpoints
    Call<PharmacologyInfo> getPharmacologyInfo(@Path("date") String date);

    @GET("/allSubPharmacology/{date}")
        // API's endpoints
    Call<SubPharmacology> getSubPharmacology(@Path("date") String date);

    @GET("/allTreatmentMedicine/{date}")
        // API's endpoints
    Call<TreatmentMedicine> getTreatmentMedicine(@Path("date") String date);


    @GET("/allReference/{date}")
        // API's endpoints
    Call<Brief> getReference(@Path("date") String date);

    @GET("/appUsers/getDoctor/{date}")
        // API's endpoints
    Call<Doctor> getDoctor(@Path("date") String date);

    @GET("/appUsers/getChambers/{date}")
        // API's endpoints
    Call<Chamber> getChambers(@Path("date") String date);

    @GET("/allRevisedBy/{date}")
        // API's endpoints
    Call<RevisedBy> getRevisedBy(@Path("date") String date);







    @POST("/bloodByContact")
    @FormUrlEncoded
        // API's endpoints
    Call<ContactPjo> getBlood(
//            @HeaderMap Map<String, String> headers,
            @FieldMap Map<String,String> params
    );

    @GET("/sponsor/allSponsor/{date}")
        // API's endpoints
    Call<Sponsor> getSponsor(@Path("date") String date);

    @GET("/update_history/{date}")
        // API's endpoints
    Call<UpdateTable> getUpdateHistory(@Path("date") String date);
    @GET("/appUsers/userInfo/{phone}")
        // API's endpoints
    Call<AppUser> getUserInfo(@Path("phone") String phone);

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/appUsers/store")
        // API's endpoints
    Call<SignUpResponse> registration(@Field("mobile") String mNumber,
                                      @Field("full_name") String fName,
                                      @Field("sex") String sexId,
                                      @Field("blood_group") String bGroup,
                                      @Field("district_present") String dPresentId,
                                      @Field("district_parmanent") String dParmanentId,
                                      @Field("upazila_present") String uPresentId,
                                      @Field("upazila_parmanent") String uParmanentId,
                                      @Field("user_token") String token
                                      );







    @GET("/appUsers/login/{phone}/{token}")
    Call<LoginVerify> loginVerify(@Path("phone") String phone,
                                  @Path("token") String token
    );

/*
    @FormUrlEncoded
    @POST("/appUsers/login/{contacts}")
    Call<ContactPjo> getBloodGroup(@Path("numberArray[]") ArrayList<String>arrayList);
*/



   /* @GET("/bloodByContact/{contacts}")
    Call<ContactPjo> getBloodGroups(@Path("contacts") String contacts);*/
/*   @FormUrlEncoded
    @POST("/bloodByContact")
    Call<ContactPjo> getBloodGroups(@Field("contacts") String contacts);*/


    @FormUrlEncoded
    @POST("/blood-request/notification")
    Call<NotificationResponse> postBloodRequest(@Field("blood_group") String bldGroup,
                                                @Field("body_message") String bodyMessage,
                                                @Field("district_send") String dstrctSend,
                                                @Field("district_sort") String dstrctSort,
                                                @Field("upazila_send") String upazlaSend,
                                                @Field("upazila_sort") String upazlaSort,
                                                @Field("mobile_number") String mobileNumber
    );




}
