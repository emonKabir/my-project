package com.example.emon.bdmedic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.example.emon.bdmedic.ContactList.PojoClass.ContactData;
import com.example.emon.bdmedic.ContactList.PojoClass.ContactPjo;
import com.example.emon.bdmedic.DoctorAndChamber.Chamber;
import com.example.emon.bdmedic.DoctorAndChamber.ChamberData;
import com.example.emon.bdmedic.DoctorAndChamber.Doctor;
import com.example.emon.bdmedic.DoctorAndChamber.DoctorData;
import com.example.emon.bdmedic.Fragments.Branch;
import com.example.emon.bdmedic.Fragments.BranchAndDisciplineData;
import com.example.emon.bdmedic.Fragments.BranchData;
import com.example.emon.bdmedic.Fragments.Brief;
import com.example.emon.bdmedic.Fragments.BriefData;
import com.example.emon.bdmedic.Fragments.Discipline;
import com.example.emon.bdmedic.Fragments.DisciplineData;
import com.example.emon.bdmedic.Fragments.DiseaseManage;
import com.example.emon.bdmedic.Fragments.DiseaseManageData;
import com.example.emon.bdmedic.Fragments.RevisedBy;
import com.example.emon.bdmedic.Fragments.RevisedByData;
import com.example.emon.bdmedic.Fragments.TreatmentMedicine;
import com.example.emon.bdmedic.Fragments.TreatmentMedicineData;
import com.example.emon.bdmedic.Laboratory.InvestigationData;
import com.example.emon.bdmedic.Laboratory.Investigation;
import com.example.emon.bdmedic.Laboratory.PojoClass.Hospital;
import com.example.emon.bdmedic.Laboratory.PojoClass.HospitalData;
import com.example.emon.bdmedic.Laboratory.PojoClass.InvestigationPrice;
import com.example.emon.bdmedic.Laboratory.PojoClass.InvestigationPriceData;
import com.example.emon.bdmedic.Laboratory.PojoClass.MedicinePharmacology;
import com.example.emon.bdmedic.Laboratory.PojoClass.MedicinePharmacologyData;
import com.example.emon.bdmedic.Laboratory.PojoClass.PharmacologyInfo;
import com.example.emon.bdmedic.Laboratory.PojoClass.PharmacologyInfoData;
import com.example.emon.bdmedic.Laboratory.PojoClass.SubPharmacology;
import com.example.emon.bdmedic.Laboratory.PojoClass.SubPharmacologyData;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.Media.MediaPojoClass.Media;
import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.Category;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.CategoryData;

import com.example.emon.bdmedic.MedicineGroupPojoClasses.Sponsor;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SponsorData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemCategory;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemCategoryData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.Systems;
import com.example.emon.bdmedic.OnUpdate.OnUpdateTable;
import com.example.emon.bdmedic.Pharmacologist.MedicineSubGroup;
import com.example.emon.bdmedic.Pharmacologist.MedicineSubGroupData;
import com.example.emon.bdmedic.PriceCalculator.Dosage;
import com.example.emon.bdmedic.PriceCalculator.DosageData;
import com.example.emon.bdmedic.PriceCalculator.Medicine;
import com.example.emon.bdmedic.PriceCalculator.MedicineData;
import com.example.emon.bdmedic.UpdateHistory.UpdateTable;
import com.example.emon.bdmedic.UpdateHistory.UpdateTableData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MethodTesting {


//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
       Activity activity;
       OnUpdateTable onUpdateTable;
    List<DisciplineData> disciplineDataList;
    List<BranchData> branchDataList;
    List<BriefData>briefDataList;
    List<AppUserData>appUserDataList;
    List<TreatmentMedicineData>treatmentMedicineDataList;
    List<DoctorData>doctorDataList;
    List<ChamberData>chamberDataList;
    List<RevisedByData>revisedByDataList;
    List<DosageData>dosageDataList;
    List<MedicineData>medicineDataList;
    List<MedicineSubGroupData>medicineSubGroupDataList;
    List<InvestigationData> investigationDataList;
    List<DiseaseManageData>diseaseManageDataList;
    List<BranchAndDisciplineData>branchAndDisciplineDataList;
    List<UpdateTableData>updateTableDataList;
    List<ContactData>contactData;
    String tableName;
    List<String>tableList;
    List<SystemData> systemDataList;
    List<CategoryData> categoryDataList;
    List<SystemCategoryData> systemCategoryList;
    private List<SponsorData> sponsorDataList;
    private List<HospitalData> hospitalDataList;
    private List<InvestigationPriceData> investigationPriceDataList;
    private List<MedicinePharmacologyData> medicinePharmacologyDataList;
    private List<PharmacologyInfoData> pharmacologyInfoDataList;
    private List<SubPharmacologyData> subPharmacologyDataList;
    private  List<MediaData>mediaData;
    int id = 1;


//    DataService dataService;
//    ProgressBar progressBar = new ProgressBar(activity);

    public MethodTesting(Activity activity) {

        this.activity = activity;
    }





     void getSystemData(String date, final MenuFeed.LoadSystemData loadSystemData) {

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Systems> systemCall = apiService.getSystemData(date);
        systemCall.clone().enqueue(new Callback<Systems>() {
            @Override
            public void onResponse(Call<Systems> call, Response<Systems> response) {
                if(response.isSuccessful()){

                    if (response.body() != null) {
                    if(response.body().getStatus() == 1){
                            systemDataList = response.body().getData();
                            loadSystemData.execute(systemDataList);
                        }else{
                        System.out.println("message from systems.............. :"+response.body().getMessage());
                    }

                    }

                }
            }

            @Override
            public void onFailure(Call<Systems> call, Throwable t) {
                System.out.println("system error................... :"+t.getMessage());

            }
        });



    }

    public void getBlood(Map<String,String> contacts) {

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);

        HashMap<String,String>hashMap = new HashMap<>();
        hashMap.put("Content-Type","application/json");


        final Call<ContactPjo>contactPjoCall = apiService.getBlood(contacts);
//        final Call<ContactPjo> systemCall = apiService.getSystemData();
        contactPjoCall.clone().enqueue(new Callback<ContactPjo>() {
            @Override
            public void onResponse(Call<ContactPjo> call, Response<ContactPjo> response) {

                if (response.isSuccessful()){
//                    myDBHelper = new MyDBHelper(activity);
                    myDatabase = new MyDatabase(activity);
                    System.out.println("successfull message : "+response.body().getMessage());
                    contactData = response.body().getData();
                    for(int i = 0; i < contactData.size(); i++){


                        System.out.println("number : "+contactData.get(i).getPhone());
                        System.out.println("group : "+contactData.get(i).getBloodGroup());
                    }
                    myDatabase.insertContactListFromMethod(contactData);

                }
            }

            @Override
            public void onFailure(Call<ContactPjo> call, Throwable t) {

                System.out.println("errroo   : "+t.getMessage());
            }
        });


    }

    void getUserInfo(String phone) {

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


//        myDBHelper = new MyDBHelper(activity);
        myDatabase = new MyDatabase(activity);
        final Call<AppUser> userCall = apiService.getUserInfo(phone);
        userCall.clone().enqueue(new Callback<AppUser>() {
            @Override
            public void onResponse(Call<AppUser> call, Response<AppUser> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        System.out.println("response of User Info : "+response.body().getMessage());
                    }
                    if (response.body() != null) {
                        System.out.println("response success full name : "+response.body().getData().get(0).getName());
                    }
                    appUserDataList = response.body().getData();
                    myDatabase.insertUserInfo(appUserDataList);
                }
            }

            @Override
            public void onFailure(Call<AppUser> call, Throwable t) {

                System.out.println("error form user table : "+t.getMessage());
            }
        });



    }
    void getCategoryData(String date,final MenuFeed.LoadCategoryData loadCategoryData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Category> categoryCall = apiService.getCategoryData(date);
        categoryCall.clone().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){


                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            categoryDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadCategoryData.execute(categoryDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

                System.out.println("message from category :"+t.getMessage());
            }
        });




    }
     void getSystemCategoryData(String date,final MenuFeed.LoadSystemCategoryData loadSystemCategoryData) {

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<SystemCategory> systemCategoryCall = apiService.getSystemCategoryData(date);
        systemCategoryCall.clone().enqueue(new Callback<SystemCategory>() {
            @Override
            public void onResponse(Call<SystemCategory> call, Response<SystemCategory> response) {
                if(response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            systemCategoryList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadSystemCategoryData.execute(systemCategoryList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<SystemCategory> call, Throwable t) {
                System.out.println("system and category :"+t.getMessage());

            }
        });




    }
     void getDiscipline(String date,final MenuFeed.LoadDesciplineData loadDesciplineData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Discipline> disciplineCall = apiService.getDiscipline(date);
       disciplineCall.clone().enqueue(new Callback<Discipline>() {
           @Override
           public void onResponse(Call<Discipline> call, Response<Discipline> response) {
               if (response.isSuccessful())
               {
                   System.out.println("Discipline response : "+response.body().getMessage());
                   if (response.body() != null) {
                       if(response.body().getStatus() == 1){
                           disciplineDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                           loadDesciplineData.execute(disciplineDataList);
                       }else{
                           System.out.println("message from systems.............. :"+response.body().getMessage());
                       }

                   }

               }

           }

           @Override
           public void onFailure(Call<Discipline> call, Throwable t) {

               System.out.println("despline failure................... : "+t.getMessage());
           }
       });


    }
     void getBranch(String date,final MenuFeed.LoadBranchData loadBranchData) {

//        circularProgressBar.setVisibility(View.VISIBLE);
       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Branch> branchCall = apiService.getBranch(date);
      branchCall.clone().enqueue(new Callback<Branch>() {
          @Override
          public void onResponse(Call<Branch> call, Response<Branch> response) {

                int result = 0;
              if (response.isSuccessful()){

                  if (response.body() != null) {
                      if(response.body().getStatus() == 1){
                          System.out.println("Message from branch : "+response.body().getMessage());
                          branchDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                          loadBranchData.execute(branchDataList);
                      }else{
                          System.out.println("message from systems.............. :"+response.body().getMessage());
                      }

                  }

                  /*MenuFeed.LoadMedicineData loadData = null;
                  loadData.execute(branchDataList);*/
//                  myDBHelper = new MyDBHelper(activity);
                 /* System.out.println("entering in mydbhelper.........................................");
                  result =   myDBHelper.insertBranch(branchDataList);
                  System.out.println("Insert branch successfully");*/


              }


             /* hideLayout.setVisibility(View.VISIBLE);
              progressLayout.setVisibility(View.GONE);*/

          }

          @Override
          public void onFailure(Call<Branch> call, Throwable t) {

              System.out.println("brancch failure=========================== : "+t.getMessage());
          }
      });

//        circularProgressBar.setVisibility(View.GONE);

    }
     void getBrief(String date,final MenuFeed.LoadBriefData loadBriefData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Brief> briefCall = apiService.getBrief(date);
        briefCall.clone().enqueue(new Callback<Brief>() {
            @Override
            public void onResponse(Call<Brief> call, Response<Brief> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            System.out.println("Message from branch : "+response.body().getMessage());
                            briefDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadBriefData.execute(briefDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }


                    /*myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertBrief(briefDataList);
                    System.out.println("Insert brief successfully");*/
                }

                /*hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<Brief> call, Throwable t) {

                System.out.println("Brief failure=========================== : "+t.getMessage());
            }
        });



    }
     void getTreatmentMedicine(String date,final MenuFeed.LoadTreatmentMedicineData loadTreatmentMedicineData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

        /*hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<TreatmentMedicine> treatmentMedicineCall = apiService.getTreatmentMedicine(date);
        treatmentMedicineCall.clone().enqueue(new Callback<TreatmentMedicine>() {
            @Override
            public void onResponse(Call<TreatmentMedicine> call, Response<TreatmentMedicine> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            treatmentMedicineDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadTreatmentMedicineData.execute(treatmentMedicineDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }


                   /* myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertTreatmentMedicine(treatmentMedicineDataList);
                    System.out.println("MEDICINE TREATMENT CALLING... ");*/
                }
               /* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<TreatmentMedicine> call, Throwable t) {
                System.out.println("Tretment and medicine failure=========================== : "+t.getMessage());
            }
        });


    }
     void getDoctors(String date,final MenuFeed.LoadDoctorData loadDoctorData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

      /*  hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);
*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Doctor> doctorCall = apiService.getDoctor(date);

        doctorCall.clone().enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if(response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            System.out.println("Message from branch : "+response.body().getMessage());
                            doctorDataList = response.body().getData();
                            System.out.println("doctor name================ : "+doctorDataList.get(0).getName());
//                            loadSystemData.execute(systemDataList);
                            loadDoctorData.execute(doctorDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                   /* myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertDoctor(doctorDataList);
                    System.out.println("Doctor CALLING... ");
*/
                }

               /* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {

                System.out.println("doctor failure=========================== : "+t.getMessage());
            }
        });



    }
     void getChamber(String date,final MenuFeed.LoadChamberData loadChamberData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/
       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Chamber> chamberCall = apiService.getChambers(date);

        chamberCall.clone().enqueue(new Callback<Chamber>() {
            @Override
            public void onResponse(Call<Chamber> call, Response<Chamber> response) {
                if(response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            chamberDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadChamberData.execute(chamberDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                   /* myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertChamber(chamberDataList);
                    System.out.println("chamber CALLING... ");*/

                }

               /* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<Chamber> call, Throwable t) {

                System.out.println("chamber failure=========================== : "+t.getMessage());
            }
        });


    }
     void getRevisedBy(String date,final MenuFeed.LoadRevisedByData loadRevisedByData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<RevisedBy> revisedByCall = apiService.getRevisedBy(date);

        revisedByCall.clone().enqueue(new Callback<RevisedBy>() {
            @Override
            public void onResponse(Call<RevisedBy> call, Response<RevisedBy> response) {

                if(response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            System.out.println("Message from branch : "+response.body().getMessage());
                            revisedByDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadRevisedByData.execute(revisedByDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                  /*  myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertRevisedBy(revisedByDataList);
                    System.out.println("revised CALLING... ");*/

                }

               /* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<RevisedBy> call, Throwable t) {

                System.out.println("revised failure=========================== : "+t.getMessage());
            }
        });


    }
    void getDosages(String date, final MenuFeed.LoadDosageData loadDosageData) {

//        hideLayout.setVisibility(View.GONE);
       /* progressLayout.setVisibility(View.VISIBLE);
        circularProgressBar.enableIndeterminateMode(true);*/

      /* final ProgressDialog progressDialog = new ProgressDialog(activity);
       progressDialog.setCanceledOnTouchOutside(false);
       progressDialog.setMessage("Loading.please Wait!");
       progressDialog.show();*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);

        Call<Dosage> DosageCall = apiService.getDosage(date);
        DosageCall.clone().enqueue(new Callback<Dosage>() {
            @Override
            public void onResponse(Call<Dosage> call, Response<Dosage> response) {
                if(response.isSuccessful()){

//                    progressDialog.hide();
                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            Log.e("dosage response",response.body().getMessage());
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            dosageDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadDosageData.execute(dosageDataList);
                        }else{
                            System.out.println("message from dosage .............. :"+response.body().getMessage());
                        }

                    }


//                    myDBHelper.insertDosage(dosageDataList);

                }

//                progressDialog.hide();
//                hideLayout.setVisibility(View.VISIBLE);
//                progressLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Dosage> call, Throwable t) {

                System.out.println("Dosage failure=========================== : "+t.getMessage());
                Log.e("dosage failure",t.getMessage());
            }
        });

    }
    void getMedicnes(String date, final MenuFeed.LoadMedicineData loadMedicineData) {


//        hideLayout.setVisibility(View.GONE);
       /* progressLayout.setVisibility(View.VISIBLE);
        circularProgressBar.enableIndeterminateMode(true);*/

       /* final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading.please Wait!");
        progressDialog.show();*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        Call<Medicine> MedicineCall = apiService.getMedicine(date);
        MedicineCall.clone().enqueue(new Callback<Medicine>() {
            @Override
            public void onResponse(Call<Medicine> call, Response<Medicine> response) {

//                progressDialog.hide();
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
                            Log.e("medicine response",response.body().getMessage());
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            medicineDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadMedicineData.execute(medicineDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }



//                    MedicineSubGroup.out.println("enter the function");
//                    myDBHelper.insertMedicineData(medicineDataList);

                }
//                hideLayout.setVisibility(View.VISIBLE);
//                progressLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Medicine> call, Throwable t) {
                System.out.println("Medicine failure =========================== : "+t.getMessage());
                Log.e("medicine failure",t.getMessage());

            }

        });

//        progressBar.setVisibility(View.GONE);

    }
    void getMedicineSubGroup(String date, final MenuFeed.LoadMedicineSubGroupData loadMedicineSubGroupData) {
       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/
      /*  final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading.please Wait!");
        progressDialog.show();*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);

        Call<MedicineSubGroup> getData = apiService.getSystem(date);

        getData.clone().enqueue(new Callback<MedicineSubGroup>() {
            @Override
            public void onResponse(Call<MedicineSubGroup> call, Response<MedicineSubGroup> response) {

//                progressDialog.hide();
                if (response.isSuccessful()) {
//                    myDBHelper = new MyDBHelper(activity);
                    myDatabase = new MyDatabase(activity);
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            System.out.println("Message from medicine sub group : " + response.body().getMessage());

                            medicineSubGroupDataList = response.body().getData();
                            System.out.println("size from medicine sub group : " + medicineSubGroupDataList.size());
                            for(int i = 0; i < medicineSubGroupDataList.size(); i++){

                                System.out.println("medicine sub group generic name : "+medicineSubGroupDataList.get(i).getGenericName());
                            }
//                            loadSystemData.execute(systemDataList);
                            loadMedicineSubGroupData.execute(medicineSubGroupDataList);
//                            myDBHelper.insertMedicineSubGroup(medicineSubGroupDataList);

                        } else {
                            System.out.println("message from systems.............. :" + response.body().getMessage());
                        }

                    }




                   /* myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertMedicineSubGroup(medicineSubGroupDataList);*/

                }
                /*hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<MedicineSubGroup> call, Throwable t) {
                System.out.println("medicine sub group failure failure=========================== : " + t.getMessage());

            }
        });
    }
    void getInvestigation(String date, final MenuFeed.LoadInvestigationData loadInvestigationData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        Call<Investigation> investigationCall = apiService.getInvestigation(date);
        investigationCall.clone().enqueue(new Callback<Investigation>() {
            @Override
            public void onResponse(Call<Investigation> call, Response<Investigation> response) {

                if(response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            investigationDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadInvestigationData.execute(investigationDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }


                    /*myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertInvestigation(investigationDataList);*/

                }

              /*  hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
            }
            @Override
            public void onFailure(Call<Investigation> call, Throwable t) {

                System.out.println("investigation failure=========================== : "+t.getMessage());

            }
        });




    }
    void getDisease(String date, final MenuFeed.LoadDiseaseManagementData loadDiseaseManagementData) {

       /* final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/

       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/



       /* ProgressBar progressBar = new ProgressBar(activity);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<DiseaseManage>diseaseManageCall = apiService.getDisease(date);
        diseaseManageCall.clone().enqueue(new Callback<DiseaseManage>() {

            @Override
            public void onResponse(Call<DiseaseManage> call, Response<DiseaseManage> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            diseaseManageDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadDiseaseManagementData.execute(diseaseManageDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }




                   /* myDBHelper = new MyDBHelper(activity);
                    myDBHelper.insertDiseaseManagement(diseaseManageDataList);
                    System.out.println("TMSG function called");*/
                }
               /* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/

            }

            @Override
            public void onFailure(Call<DiseaseManage> call, Throwable t) {

                System.out.println("disease failure=========================== : "+t.getMessage());
            }
        });
//        progressBar.setVisibility(View.GONE);


    }
    void getSponsor(String date, final MenuFeed.LoadSponsorData loadSponsorData) {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Sponsor>sponsorCall = apiService.getSponsor(date);
        sponsorCall.clone().enqueue(new Callback<Sponsor>() {
            @Override
            public void onResponse(Call<Sponsor> call, Response<Sponsor> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            sponsorDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
//                            loadDiseaseManagementData.execute(diseaseManageDataList);
                            loadSponsorData.execute(sponsorDataList);

                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<Sponsor> call, Throwable t) {
                Log.e("EK",t.getMessage());

            }
        });
       /* diseaseManageCall.clone().enqueue(new Callback<DiseaseManage>() {

            @Override
            public void onResponse(Call<DiseaseManage> call, Response<DiseaseManage> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            diseaseManageDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
                            loadDiseaseManagementData.execute(diseaseManageDataList);
                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                }
               *//* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*//*

            }

            @Override
            public void onFailure(Call<DiseaseManage> call, Throwable t) {

                System.out.println("disease failure=========================== : "+t.getMessage());
            }
        });*/
//        progressBar.setVisibility(View.GONE);


    }
    void getHospital(String date, final MenuFeed.LoadHospitalData loadHospitalData) {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<Hospital>hospitalCall = apiService.getHospital(date);

        hospitalCall.clone().enqueue(new Callback<Hospital>() {
            @Override
            public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            hospitalDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
//                            loadDiseaseManagementData.execute(diseaseManageDataList);
                            loadHospitalData.execute(hospitalDataList);

                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<Hospital> call, Throwable t) {

                Log.d("emonKabir",t.getMessage());
            }
        });




    }
    void getInvestigationPrice(String date, final MenuFeed.LoadInvestigationPriceData loadInvestigationPriceData) {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<InvestigationPrice>investigationPriceCall = apiService.getInvestigationPrice(date);

        investigationPriceCall.clone().enqueue(new Callback<InvestigationPrice>() {
            @Override
            public void onResponse(Call<InvestigationPrice> call, Response<InvestigationPrice> response) {

                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            investigationPriceDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
//                            loadDiseaseManagementData.execute(diseaseManageDataList);
                            loadInvestigationPriceData.execute(investigationPriceDataList);

                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<InvestigationPrice> call, Throwable t) {

                Log.d("emonKabir",t.getMessage());

            }
        });

//        hospitalCall.clone().enqueue(new Callback<Hospital>() {
//            @Override
//            public void onResponse(Call<Hospital> call, Response<Hospital> response) {

//
//            }
//
//            @Override
//            public void onFailure(Call<Hospital> call, Throwable t) {
//
//                Log.d("emonKabir",t.getMessage());
//            }
//        });




    }
    void getMedicinePharmacology(String date, final MenuFeed.LoadMedicinePharmacologyData loadMedicinePharmacologyData) {

        /*final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading.please Wait!");
        progressDialog.show();*/

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<MedicinePharmacology>medicinePharmacologyCall = apiService.getMedicinePharmacology(date);

        medicinePharmacologyCall.clone().enqueue(new Callback<MedicinePharmacology>() {
            @Override
            public void onResponse(Call<MedicinePharmacology> call, Response<MedicinePharmacology> response) {

//                progressDialog.hide();
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            medicinePharmacologyDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
//                            loadDiseaseManagementData.execute(diseaseManageDataList);
                         /*   for(int i = 0; i < medicinePharmacologyDataList.size(); i++){

//                                System.out.println("description....... : "+ Html.fromHtml(medicinePharmacologyDataList.get(i).getDescription()).toString());
                            }*/
                            loadMedicinePharmacologyData.execute(medicinePharmacologyDataList);

                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<MedicinePharmacology> call, Throwable t) {

                Log.d("emonKabir",t.getMessage());

            }
        });





    }
    void getPharmacologyInfo(String date, final MenuFeed.LoadPharmacologyInfoData loadPharmacologyInfoData) {

        /*final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading.please Wait!");
        progressDialog.show();*/
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);




        final Call<PharmacologyInfo>pharmacologyInfoCall = apiService.getPharmacologyInfo(date);

        pharmacologyInfoCall.clone().enqueue(new Callback<PharmacologyInfo>() {
            @Override
            public void onResponse(Call<PharmacologyInfo> call, Response<PharmacologyInfo> response) {

//                progressDialog.hide();
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            pharmacologyInfoDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
//                            loadDiseaseManagementData.execute(diseaseManageDataList);
                            loadPharmacologyInfoData.execute(pharmacologyInfoDataList);

                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<PharmacologyInfo> call, Throwable t) {

                Log.d("emonKabir",t.getMessage());
            }
        });



    }
    void getSubPharmacology(String date, final MenuFeed.LoadSubPharmacologyData loadSubPharmacologyData) {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final Call<SubPharmacology>subPharmacologyCall = apiService.getSubPharmacology(date);

        subPharmacologyCall.clone().enqueue(new Callback<SubPharmacology>() {
            @Override
            public void onResponse(Call<SubPharmacology> call, Response<SubPharmacology> response) {
                if (response.isSuccessful()){

                    if (response.body() != null) {
                        if(response.body().getStatus() == 1){
//                            System.out.println("Message from branch : "+response.body().getMessage());
                            subPharmacologyDataList = response.body().getData();
//                            loadSystemData.execute(systemDataList);
//                            loadDiseaseManagementData.execute(diseaseManageDataList);
                            loadSubPharmacologyData.execute(subPharmacologyDataList);

                        }else{
                            System.out.println("message from systems.............. :"+response.body().getMessage());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<SubPharmacology> call, Throwable t) {

            }
        });






    }
    void getMedia(String date, final MenuFeed.LoadMediaData loadMediaData) {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        final String base = ApiInterface.BASE_URL;
        final Call<Media> mediaCall = apiService.getMedia(date);

        mediaCall.clone().enqueue(new Callback<Media>() {
            @Override
            public void onResponse(Call<Media> call, Response<Media> response) {
                if(response.isSuccessful()){

//                    imageUrl = new ArrayList<>();
//                    mediaData = new ArrayList<>();
//                    pDialog.dismiss();
                    if(response.body()!= null){

                        if(response.body().getStatus() == 1){

                            System.out.println("message from media.................. : "+response.body().getMessage());

                            mediaData = response.body().getData();
//                            loadMediaData().execute(mediaData);
                            loadMediaData.execute(mediaData);
//                            slider(videoUrl);
                            /*for(int i = 0; i < response.body().getData().size(); i++){
                                if(response.body().getData().get(i).getType() == 1){
                                    //imageUrl.add(base+response.body().getData().get(i).getUrl());
                                    System.out.println("message from imageurl.................... : "+response.body().getData().get(i).getUrl());
                                    //slider(imageUrl);
                                }else{

                                    System.out.println("message from videourl.................. : "+response.body().getData().get(i).getUrl());
                                    //videoUrl.add(new MediaData(response.body().getData().get(i).getName(),base+response.body().getData().get(i).getUrl()));
                                }
                            }*/

                           /* System.out.println("jdkjfflkjdflkjsfkjsdlkf :"+imageUrl.get(0));
                            System.out.println("jdkjfflkjdflkjsfkjsdlkf :"+imageUrl.get(1));
                            for(int i = 0; i < 2; i++){

                                slider(imageUrl);
                            }*/
                            /*List<String>stringList;
                            stringList = new ArrayList<>();
                            stringList.add(ApiInterface.BASE_URL+"/uploads/logo.png");
                            stringList.add(ApiInterface.BASE_URL+"/uploads/2f2bb2f1f4375e0d77f18a86cee22d7d--medical-apps.jpg");
                            slider(stringList);*/
                            /*VideoAdapter videoAdapter = new VideoAdapter(getActivity(),videoUrl);
                            videoListview.setAdapter(videoAdapter);*/


                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<Media> call, Throwable t) {

                Log.d("EK","media throwable",t);
            }
        });


    }

    public void getUpdates(String date, final MenuFeed.LoadBranchData loadBranchData,
                           final MenuFeed.LoadBriefData loadBriefData, final MenuFeed.LoadRevisedByData loadRevisedByData,
                           final MenuFeed.LoadChamberData loadChamberData, final MenuFeed.LoadDoctorData loadDoctorData,
                           final MenuFeed.LoadDesciplineData loadDesciplineData, final MenuFeed.LoadInvestigationData loadInvestigationData,
                           final MenuFeed.LoadMedicineSubGroupData loadMedicineSubGroupData, final MenuFeed.LoadDiseaseManagementData loadDiseaseManagementData,
                           final MenuFeed.LoadTreatmentMedicineData loadTreatmentMedicineData, final MenuFeed.LoadMedicineData loadMedicineData,
                           final MenuFeed.LoadDosageData loadDosageData, final MenuFeed.LoadSystemData loadSystemData,
                           final MenuFeed.LoadCategoryData loadCategoryData, final MenuFeed.LoadSystemCategoryData loadSystemCategoryData,
                           final MenuFeed.LoadSponsorData loadSponsorData,final MenuFeed.LoadHospitalData loadHospitalData,
                           final MenuFeed.LoadInvestigationPriceData loadInvestigationPriceData,final MenuFeed.LoadMedicinePharmacologyData loadMedicinePharmacologyData,
                           final MenuFeed.LoadPharmacologyInfoData loadPharmacologyInfoData,final MenuFeed.LoadSubPharmacologyData loadSubPharmacologyData,
                           final MenuFeed.LoadMediaData loadMediaData
                           ) {

        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);

        System.out.println("second===================================== : ");
        final Call<UpdateTable> updateTableCall = apiService.getUpdateHistory(date);
        System.out.println("third===================================== : ");
        updateTableCall.clone().enqueue(new Callback<UpdateTable>() {
            @Override
            public void onResponse(Call<UpdateTable> call, Response<UpdateTable> response) {
                if(response.isSuccessful()){



                    System.out.println("response ===================================== : "+response.body().getMessage());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if(Objects.requireNonNull(response.body()).getStatus() == 1){
//                            onUpdateTable = new OnUpdateTable();
//                            myDBHelper  = new MyDBHelper(activity);
                            myDatabase = new MyDatabase(activity);
                            System.out.println("enter in update........................");
                            updateTableDataList = Objects.requireNonNull(response.body()).getData();
//                            onUpdateTable.OnUpdateTables(updateTableDataList);
                            for(int i = 0; i < updateTableDataList.size(); i++){


                                System.out.println("enter in loop........................ : "+updateTableDataList.get(i).getTableName());
                                switch (updateTableDataList.get(i).getTableName()){


                                    case "branches":
                                        System.out.println("enter in branch........................ date : "+dateCheker());
                                        getBranch(dateCheker(),loadBranchData);
                                        break;
                                    case "treatment_briefs":
                                        System.out.println("enter in brief........................"+dateCheker());
                                        getBrief(dateCheker(),loadBriefData);
                                        break;
                                    case "chambers":
                                        System.out.println("enter in chamber........................ "+dateCheker());
                                        getChamber(dateCheker(),loadChamberData);
                                        break;
                                    case "disciplines":
                                        System.out.println("enter in disciplines........................ : "+dateCheker());
                                        getDiscipline(dateCheker(),loadDesciplineData);
                                        break;
                                    case "disease_managements":
                                        System.out.println("enter in disease........................ :"+dateCheker());
                                        getDisease(dateCheker(),loadDiseaseManagementData);
                                        break;
                                    case "doctors":
                                        System.out.println("enter in doctors........................ "+dateCheker());
                                        getDoctors(dateCheker(),loadDoctorData);
                                        break;
                                    case "investigations":
                                        System.out.println("enter in investigation........................  :"+dateCheker());
                                        getInvestigation(dateCheker(),loadInvestigationData);
                                        break;
                                    case "medicine_sub_groups":
                                        System.out.println("enter in subGroup........................"+dateCheker());
                                        getMedicineSubGroup(dateCheker(),loadMedicineSubGroupData);
                                        break;
                                    case "medicines":
                                        System.out.println("enter in medicine........................"+dateCheker());
                                        getMedicnes(dateCheker(),loadMedicineData);
                                        break;
                                    case "medicine_dosages":
                                        System.out.println("enter in dosage........................ "+dateCheker());
                                        getDosages(dateCheker(),loadDosageData);
                                        break;
                                    case "categories":
                                        System.out.println("enter in category........................ "+dateCheker());
                                        getCategoryData(dateCheker(),loadCategoryData);
                                        break;
                                    case "medicine_groups":
                                        System.out.println("enter in category........................ "+dateCheker());
                                        getSystemData(dateCheker(),loadSystemData);
                                        break;

                                    case "medicine_group_categories":
                                        getSystemCategoryData(dateCheker(),loadSystemCategoryData);
                                        break;
                                    case "hospitals":
                                        getHospital(dateCheker(),loadHospitalData);
                                        break;
                                    case "sponsors":
                                        getSponsor(dateCheker(),loadSponsorData);
                                        break;
                                    case "pharmacologies":
                                        getPharmacologyInfo(dateCheker(),loadPharmacologyInfoData);
                                        break;

                                    case "sub_pharmacologies":
                                        getSubPharmacology(dateCheker(),loadSubPharmacologyData);
                                        break;
                                    case "medicine_pharmacologies":
                                        getMedicinePharmacology(dateCheker(),loadMedicinePharmacologyData);
                                        break;

                                    case "investigation_prices":
                                        getInvestigationPrice(dateCheker(),loadInvestigationPriceData);
                                        break;
                                    case "treatment_medicines":
                                        getTreatmentMedicine(dateCheker(),loadTreatmentMedicineData);
                                        break;
                                    case "revised_by":
                                        getRevisedBy(dateCheker(),loadRevisedByData);
                                        break;
                                    case "home_page_banner_videos":
                                        getMedia(dateCheker(),loadMediaData);
                                        break;
                                }

                            }
                            System.out.println("enter in ++++++++++++++++++++++++++++++++ :"+getCurrentTime());
                            myDatabase.insertCurrentTime(id,getCurrentTime());
                            System.out.println("enter in ====================================================");

                        }
                    }

//                    System.out.println("table name iss========================= : "+tableName);

                }
            }

            @Override
            public void onFailure(Call<UpdateTable> call, Throwable t) {

                System.out.println("update table =================== :"+t.getMessage());
             }
        });

        System.out.println("table name is++++++++++++++ :"+tableName);


    }
    public String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_time = dateFormat.format(c.getTime());
        return date_time;
    }
    public String dateCheker(){

        boolean aa = false;
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String Query = "select updated_date  from data_sync";
        Cursor cursor = sqLiteDatabase.rawQuery(Query,null);
        cursor.moveToFirst();
        String date = null;
        while(!cursor.isAfterLast()){
            date = cursor.getString(cursor.getColumnIndex("updated_date"));
            System.out.println("date..................................... :" +date);
            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();
        return date;
    }




}
