package com.example.emon.bdmedic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.DoctorAndChamber.ChamberData;
import com.example.emon.bdmedic.DoctorAndChamber.DoctorData;
import com.example.emon.bdmedic.Fragments.BranchData;
import com.example.emon.bdmedic.Fragments.BriefData;
import com.example.emon.bdmedic.Fragments.DisciplineData;
import com.example.emon.bdmedic.Fragments.DiseaseData;
import com.example.emon.bdmedic.Fragments.DiseaseManageData;
import com.example.emon.bdmedic.Fragments.RevisedByData;
import com.example.emon.bdmedic.Fragments.TMSGMain;
import com.example.emon.bdmedic.Fragments.TreatmentMedicineData;
import com.example.emon.bdmedic.Laboratory.InvestigationData;
import com.example.emon.bdmedic.Laboratory.LaboratoryValues;
import com.example.emon.bdmedic.Laboratory.PojoClass.HospitalData;
import com.example.emon.bdmedic.Laboratory.PojoClass.InvestigationPriceData;
import com.example.emon.bdmedic.Laboratory.PojoClass.MedicinePharmacologyData;
import com.example.emon.bdmedic.Laboratory.PojoClass.PharmacologyInfoData;
import com.example.emon.bdmedic.Laboratory.PojoClass.SubPharmacologyData;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.CategoryData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SponsorData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemCategoryData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemData;
import com.example.emon.bdmedic.NavDrawer.Profile;
import com.example.emon.bdmedic.Pharmacologist.DrugsData;
import com.example.emon.bdmedic.Pharmacologist.MedicineSubGroupData;
import com.example.emon.bdmedic.Pharmacologist.Pharmacologist;
import com.example.emon.bdmedic.PriceCalculator.DosageData;
import com.example.emon.bdmedic.PriceCalculator.MedicineData;
import com.example.emon.bdmedic.PriceCalculator.PriceCalculator;
import com.example.emon.bdmedic.Search.SearchActivity;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MenuFeed extends Fragment implements View.OnClickListener {



    private int[] imageInt = new int[]{R.drawable.m4,R.drawable.m6};
public FloatingActionButton bloodRequest,awarness,commonTreatment,laboratoryValues,pharmacy,priceCalculator,Medicine,Surgery,Gyne;
public LinearLayout hideLayout;
public LinearLayout progressLayout;
LinearLayout lastlayout;
DrugsData drugsData;
FrameLayout frameLayout;
ArrayList<DrugsData> medicineList;
ArrayList<DiseaseData> diseaseDataArrayList;
String initialDate = "1880-02-02 12:00:00";
String initialDate2 = "2019-05-19 12:00:00";
ProgressDialog progressDialog;
ViewGroup.MarginLayoutParams marginLayoutParams;
int id = 1;
//MyDBHelper myDBHelper;
MyDatabase myDatabase;
//MaterialSearchBar searchBar;
TextView view;
Activity activity;
String phoneNumber;

    String medicineSubGroupId;
    String genericName;
MethodTesting methodTesting;
TextView searchText;
    String token;





    // Names for the arguments we pass to the
    // activity when we create it
    private final static String ARG_STRING = "ARG_STRING";
    private final static String ARG_INT = "ARG_INT";

    public enum DataHolderMedicine {
        INSTANCE;

        private List<DrugsData> mObjectList;

        public static boolean hasData() {
            return INSTANCE.mObjectList != null;
        }

        public static void setData(final List<DrugsData> objectList) {
            INSTANCE.mObjectList = objectList;
        }

        public static List<DrugsData> getData() {
            final List<DrugsData> retList = INSTANCE.mObjectList;
            INSTANCE.mObjectList = null;
            return retList;
        }
    }
    public enum DataHolderDisease {
        INSTANCE;

        private List<DiseaseData> mObjectList;

        public static boolean hasData() {
            return INSTANCE.mObjectList != null;
        }

        public static void setData(final List<DiseaseData> objectList) {
            INSTANCE.mObjectList = objectList;
        }

        public static List<DiseaseData> getData() {
            final List<DiseaseData> retList = INSTANCE.mObjectList;
            INSTANCE.mObjectList = null;
            return retList;
        }
    }



    @Override
    public void onStart() {
        super.onStart();


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(),  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                System.out.println("new token................................................  : "+token);
                Log.d("newToken",token);

            }
        });

        try{

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userNumber",Context.MODE_PRIVATE);
            if(sharedPreferences.contains("number")){
                String mobileNumber = sharedPreferences.getString("number",null);
                methodTesting = new MethodTesting(getActivity());
//                phoneNumber = user.getPhoneNumber();
                System.out.println("phone number of current user... : "+mobileNumber);
                Log.d("user_number",mobileNumber);
                methodTesting.getUserInfo(mobileNumber);

                view.setVisibility(View.GONE);
               /* if(getVerfication() != null){

                    if((getUserType() == 1 || getUserType() == 2 || getUserType() == 3)){
                        view.setVisibility(View.GONE);
                    }

                }*/
            }



        }catch (Exception e){
            Log.e("emonKabir",e.getMessage());
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
            System.out.println("firebase errror.................. : "+e.getMessage());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu_feed, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        System.out.println("first...............");

         methodTesting = new MethodTesting(getActivity());
         myDatabase = new MyDatabase(getActivity());

        medicineList = new ArrayList<>();
        frameLayout = v.findViewById(R.id.main_menu);

        System.out.println("third.............");
        diseaseDataArrayList = new ArrayList<>();
        System.out.println("fourth.............");
        progressDialog = new ProgressDialog(getActivity());
        new LoadSearchData().execute();
//         medicineJson = new Gson().toJson(medicineList);




        System.out.println("fifth.............");
//        view.setVisibility(View.GONE);


        /* try{
             FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
             if(user != null){
                 phoneNumber = user.getPhoneNumber();
                 System.out.println("phone number of current user... : "+phoneNumber);
                 Log.d("user_number",phoneNumber);

             }
         }catch (Exception e){
             Log.e("emonKabir",e.getMessage());
             Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
             System.out.println("firebase errror.................. : "+e.getMessage());
         }*/

        System.out.println("first...............");


        System.out.println("3...............");

//        progressDialog.setMessage("data loading please wait...");
//        progressDialog.setMessage("Load please wait...");
        lastlayout = v.findViewById(R.id.lastLayout);
        commonTreatment = v.findViewById(R.id.commonTreatment);
//        searchBar = v.findViewById(R.id.searchBar);
        bloodRequest = v.findViewById(R.id.bloodRequest);
        laboratoryValues =v.findViewById(R.id.laboratoryValues);
        pharmacy = v.findViewById(R.id.pharmacy);
        priceCalculator = v.findViewById(R.id.priceCalculator);
        Medicine = v.findViewById(R.id.medicine);
        Surgery = v.findViewById(R.id.surgery);
        Gyne = v.findViewById(R.id.gyneAndObs);
        awarness = v.findViewById(R.id.awarness);
        searchText = v.findViewById(R.id.searchBtn);
//        circularProgressBar = v.findViewById(R.id.circleProgressBar);
        hideLayout = v.findViewById(R.id.hideLayout);
        progressLayout = v.findViewById(R.id.progressLayout);
        view = v.findViewById(R.id.overlapView);
//        lastlayout.setAlpha((float) 0.3);


//        view.setVisibility(View.GONE);
//        searchBar.setCardViewElevation(15);
//        searchBar.setOnClickListener(this);
        view.setOnClickListener(this);
        searchText.setOnClickListener(this);
        bloodRequest.setOnClickListener(this);
       commonTreatment.setOnClickListener(this);
       laboratoryValues.setOnClickListener(this);
        pharmacy.setOnClickListener(this);
        priceCalculator.setOnClickListener(this);
        Medicine.setOnClickListener(this);
        Surgery.setOnClickListener(this);
        Gyne.setOnClickListener(this);



//        searchBar.setIconifiedByDefault(false);

        /*DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels/displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels/displayMetrics.density;*/

        /*myDBHelper = new MyDBHelper(getActivity());
        myDBHelper.getWritableDatabase();*/
//        myDatabase = new MyDatabase(getActivity());
        System.out.println("current time.................................. :"+getCurrentTime());
//        methodTesting.getUserInfo(phoneNumber);
//        view.setVisibility(View.GONE);
        System.out.println("44444444444444444444...............");
       /* if(getUserType() == 1 || getUserType() == 2 || getUserType() == 3){
            view.setVisibility(View.GONE);
        }*/
        System.out.println("5555555555555555555...............");

//        new LoadAllTable().execute();

        if(dateCheker() == null){

            myDatabase.insertCurrentTime(id,initialDate2);
        }

        if (isOnline()){

            System.out.println("enter in online............................................");
            if(dateCheker() !=  null){


                Toast.makeText(getActivity(),"please wait while updating......",Toast.LENGTH_LONG).show();

                System.out.println("enter in update.....................................");
                methodTesting.getUpdates(dateCheker(),
                        new LoadBranchData(), new LoadBriefData(),
                        new LoadRevisedByData(),new LoadChamberData(),
                        new LoadDoctorData(),new LoadDesciplineData(),
                        new LoadInvestigationData(),new LoadMedicineSubGroupData(),
                        new LoadDiseaseManagementData(),new LoadTreatmentMedicineData(),
                        new LoadMedicineData(),new LoadDosageData(),
                        new LoadSystemData(),new LoadCategoryData(),
                        new LoadSystemCategoryData(),new LoadSponsorData(),
                        new LoadHospitalData(),new LoadInvestigationPriceData(),
                        new LoadMedicinePharmacologyData(),new LoadPharmacologyInfoData(),
                        new LoadSubPharmacologyData(),new LoadMediaData());

            }
        }else{

//            Toast.makeText(getActivity(),"please check your internet connection",Toast.LENGTH_LONG).show();
            Snackbar snackbar = Snackbar.make(frameLayout,"please check your internet connection",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        return v;
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.pharmacy:
                Intent intent = new Intent(getActivity(), Pharmacologist.class);
                startActivity(intent);
                break;
            case R.id.laboratoryValues:

                Intent intent2 = new Intent(getActivity(), LaboratoryValues.class);
                startActivity(intent2);
                break;
            case R.id.commonTreatment:

                Intent intent3 = new Intent(getActivity(), CommonTreatmentList.class);
                startActivity(intent3);
                break;
            case R.id.bloodRequest:

                Intent intent4 = new Intent(getActivity(), RequestBlood.class);
                startActivity(intent4);
                break;
            case R.id.awarness:

                Toast.makeText(getActivity(), "This system is under construction.", Toast.LENGTH_LONG).show();

                break;
            case R.id.priceCalculator:

                Intent intent5 = new Intent(getActivity(), PriceCalculator.class);
                startActivity(intent5);
                break;

            case R.id.medicine:

                Intent intent6 = new Intent(getActivity(), TMSGMain.class);
                intent6.putExtra("clickButton", "1");
                startActivity(intent6);
                break;

            case R.id.surgery:

                Intent intent7 = new Intent(getActivity(), TMSGMain.class);
                intent7.putExtra("clickButton", "2");
                startActivity(intent7);
                break;

            case R.id.gyneAndObs:

                Intent intent8 = new Intent(getActivity(), TMSGMain.class);
                intent8.putExtra("clickButton", "3");
                startActivity(intent8);

                break;
            case R.id.overlapView:

                Intent intent1 = new Intent(getActivity(), Profile.class);
                startActivity(intent1);

                break;
            case R.id.searchBtn:

                startActivity(getActivity(),"test",1,medicineList,diseaseDataArrayList);
                break;




        }
    }
 /*   public boolean inOnline(){

        try{

            int timeoutMs = 1500;
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("8.8.8.8",53);
            socket.connect(socketAddress,timeoutMs);
            socket.close();

            return true;
        }catch (IOException e){return false; }
    }*/

 public boolean isOnline(){

     /*Runtime runtime = Runtime.getRuntime();
     try{
         Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
         int exitValue = ipProcess.waitFor();
         return (exitValue == 0);
     }
     catch (IOException e){e.printStackTrace();}
     catch(InterruptedException e){e.printStackTrace();}

     return false;*/

     boolean haveConnectedWifi = false;
     boolean haveConnectedMobile = false;

     ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
     NetworkInfo[] netInfo = cm.getAllNetworkInfo();
     for (NetworkInfo ni : netInfo) {
         if (ni.getTypeName().equalsIgnoreCase("WIFI"))
             if (ni.isConnected())
                 haveConnectedWifi = true;
         if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
             if (ni.isConnected())
                 haveConnectedMobile = true;
     }
     return haveConnectedWifi || haveConnectedMobile;
 }


    public String dateCheker(){

        boolean aa = false;
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
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

    public String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_time = dateFormat.format(c.getTime());
        return date_time;
    }


    public class LoadAllTable extends AsyncTask<Void,Void, Boolean> {


        /*MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        MethodTesting methodTesting = new MethodTesting(getActivity());*/

        @Override
        protected void onPreExecute() {
//            Toast.makeText(getActivity(),"start activity",Toast.LENGTH_SHORT).show();

           /* hideLayout.setVisibility(View.GONE);
            progressLayout.setVisibility(View.VISIBLE);*//*
//           circularProgressBar.setBackgroundColor(Color.parseColor("#ffffff"));
//            circularProgressBar.setVisibility(View.VISIBLE);

           *//* progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Data loading please wait...");
            progressDialog.show();*//*

            super.onPreExecute();

//            System.out.println("enter in the excute system................................");

           *//* progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);*/

        }


        @Override
        protected Boolean doInBackground(Void... params)
        {

            methodTesting.getMedicnes(initialDate,new LoadMedicineData());
            methodTesting.getMedia(initialDate,new LoadMediaData());
            methodTesting.getBranch(initialDate,new LoadBranchData());
            methodTesting.getBrief(initialDate,new LoadBriefData());
            methodTesting.getRevisedBy(initialDate,new LoadRevisedByData());
            methodTesting.getChamber(initialDate,new LoadChamberData());
            methodTesting.getDoctors(initialDate,new LoadDoctorData());
            methodTesting.getDiscipline(initialDate,new LoadDesciplineData());
            methodTesting.getDosages(initialDate,new LoadDosageData());
            methodTesting.getInvestigation(initialDate,new LoadInvestigationData());
            methodTesting.getMedicineSubGroup(initialDate,new LoadMedicineSubGroupData());
            methodTesting.getDisease(initialDate,new LoadDiseaseManagementData());
            methodTesting.getTreatmentMedicine(initialDate,new LoadTreatmentMedicineData());
            methodTesting.getSystemData(initialDate,new LoadSystemData());
            methodTesting.getCategoryData(initialDate,new LoadCategoryData());
            methodTesting.getSystemCategoryData(initialDate,new LoadSystemCategoryData());
            methodTesting.getSponsor(initialDate,new LoadSponsorData());
            methodTesting.getHospital(initialDate,new LoadHospitalData());
            methodTesting.getInvestigationPrice(initialDate,new LoadInvestigationPriceData());
            methodTesting.getMedicinePharmacology(initialDate,new LoadMedicinePharmacologyData());
            methodTesting.getPharmacologyInfo(initialDate,new LoadPharmacologyInfoData());
            methodTesting.getSubPharmacology(initialDate,new LoadSubPharmacologyData());
            /*myDBHelper.insertCurrentTime(id,getCurrentTime());
            myDBHelper.close();*/


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            super.onPostExecute(result);
            if(result){
               /* hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/

//               progressDialog.dismiss();
//                circularProgressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(),"start data processing on your phone.it will take upto 2 minutes.please wait for successfull message!!!",Toast.LENGTH_LONG).show();
            }


        };
    }
    public class LoadMedicineData extends AsyncTask<List<MedicineData>,Void, Boolean> {





        @Override
        protected void onPreExecute() {


//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Data loading please wait.");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<MedicineData>... params)
        {

            myDatabase.insertMedicineData(params[0]);
//            myDBHelper.insertMedicineData(params[0]);

            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

//            progressDialog.dismiss();
            progressDialog.hide();

        };
    }
    public class LoadDesciplineData extends AsyncTask<List<DisciplineData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait..");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<DisciplineData>... params)
        {

            myDatabase.insertDiscipline(params[0]);
//            myDBHelper.insertDiscipline(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();


        };
    }
    public class LoadBranchData extends AsyncTask<List<BranchData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {
//         progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<BranchData>... params)
        {

            myDatabase.insertBranch(params[0]);
//            myDBHelper.insertBranch(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
            /*super.onPostExecute(result);
            if(result){

              progressDialog.dismiss();
            }*/


        };
    }
    public class LoadBriefData extends AsyncTask<List<BriefData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();


        }


        @Override
        protected Boolean doInBackground(List<BriefData>... params)
        {

            myDatabase.insertBrief(params[0]);
//            myDBHelper.insertBrief(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result) {

            progressDialog.hide();
            /*super.onPostExecute(result);
            if(result){

               progressDialog.dismiss();

            }*/


        }
    }
    public class LoadTreatmentMedicineData extends AsyncTask<List<TreatmentMedicineData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();


        }


        @Override
        protected Boolean doInBackground(List<TreatmentMedicineData>... params)
        {

            myDatabase.insertTreatmentMedicine(params[0]);
//            myDBHelper.insertTreatmentMedicine(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
            /*super.onPostExecute(result);
            if(result){

               progressDialog.dismiss();
            }*/


        };
    }
    public class LoadDoctorData extends AsyncTask<List<DoctorData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();
        }


        @Override
        protected Boolean doInBackground(List<DoctorData>... params)
        {

            myDatabase.insertDoctor(params[0]);
//            myDBHelper.insertDoctor(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
           /* super.onPostExecute(result);
            if(result){

               progressDialog.dismiss();

            }*/


        };
    }
    public class LoadChamberData extends AsyncTask<List<ChamberData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<ChamberData>... params)
        {

            myDatabase.insertChamber(params[0]);
//            myDBHelper.insertChamber(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
            /*super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();

            }*/


        };
    }
    public class LoadRevisedByData extends AsyncTask<List<RevisedByData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<RevisedByData>... params)
        {

            myDatabase.insertRevisedBy(params[0]);
//            myDBHelper.insertRevisedBy(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
           /* if(result){
               progressDialog.dismiss();
  }*/


        };
    }
    public class LoadDosageData extends AsyncTask<List<DosageData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<DosageData>... params)
        {

            myDatabase.insertDosage(params[0]);
//            myDBHelper.insertDosage(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            /*super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();

            }*/

            progressDialog.hide();

        };
    }
    public class LoadMedicineSubGroupData extends AsyncTask<List<MedicineSubGroupData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {


//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<MedicineSubGroupData>... params)
        {

            myDatabase.insertMedicineSubGroup(params[0]);
//            myDBHelper.insertMedicineSubGroup(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
            /*if(result){

               progressDialog.dismiss();

            }*/


        };
    }
    public class LoadInvestigationData extends AsyncTask<List<InvestigationData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<InvestigationData>... params)
        {

            myDatabase.insertInvestigation(params[0]);
//            myDBHelper.insertInvestigation(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {


           /* if(result){

               progressDialog.dismiss();

            }*/
           progressDialog.hide();


        };
    }
    public class LoadDiseaseManagementData extends AsyncTask<List<DiseaseManageData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<DiseaseManageData>... params)
        {

            myDatabase.insertDiseaseManagement(params[0]);
//            myDBHelper.insertDiseaseManagement(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            /*super.onPostExecute(result);
            if(result){
               progressDialog.dismiss();
            }*/
            progressDialog.hide();


        };
    }
    public class LoadSystemData extends AsyncTask<List<SystemData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();


        }


        @Override
        protected Boolean doInBackground(List<SystemData>... params)
        {

            myDatabase.insertSystem(params[0]);
//            myDBHelper.insertSystem(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

          /*  if(result){

                progressDialog.dismiss();
            }*/
            progressDialog.hide();

        };
    }
    public class LoadCategoryData extends AsyncTask<List<CategoryData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();


        }


        @Override
        protected Boolean doInBackground(List<CategoryData>... params)
        {

            myDatabase.insertCategory(params[0]);
//            myDBHelper.insertCategory(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
            /*super.onPostExecute(result);
            if(result){
                progressDialog.dismiss();

            }*/


        };
    }
    public class LoadSystemCategoryData extends AsyncTask<List<SystemCategoryData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {
//
//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<SystemCategoryData>... params)
        {

            myDatabase.insertSystemCategory(params[0]);
//            myDBHelper.insertSystemCategory(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
           /* super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();
          }*/


        };
    }
    public class LoadSponsorData extends AsyncTask<List<SponsorData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<SponsorData>... params)
        {

            myDatabase.insertSponsor(params[0]);
//            myDBHelper.insertSponsor(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {


            progressDialog.hide();
            /*if(result){

                progressDialog.dismiss();

            }*/


        };
    }
    public class LoadHospitalData extends AsyncTask<List<HospitalData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<HospitalData>... params)
        {

            myDatabase.insertHospital(params[0]);
//            myDBHelper.insertHospital(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
           /* super.onPostExecute(result);
            if(result){
                progressDialog.dismiss();
            }*/


        };
    }
    public class LoadInvestigationPriceData extends AsyncTask<List<InvestigationPriceData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();


        }


        @Override
        protected Boolean doInBackground(List<InvestigationPriceData>... params)
        {

            myDatabase.insertInvestigationPrice(params[0]);
//            myDBHelper.insertInvestigationPrice(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
/*            super.onPostExecute(result);
            if(result){
  progressDialog.dismiss();
            }*/


        };
    }
    public class LoadMedicinePharmacologyData extends AsyncTask<List<MedicinePharmacologyData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<MedicinePharmacologyData>... params)
        {

            myDatabase.insertMedicinePharmacology(params[0]);
//            myDBHelper.insertMedicinePharmacology(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
            Toast.makeText(getActivity(),"app sync successfully",Toast.LENGTH_LONG).show();
//            super.onPostExecute(result);



        };
    }
    public class LoadPharmacologyInfoData extends AsyncTask<List<PharmacologyInfoData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<PharmacologyInfoData>... params)
        {

            myDatabase.insertPharmacologyInfo(params[0]);
//            myDBHelper.insertPharmacologyInfo(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {
/*
            super.onPostExecute(result);
            if(result){
                progressDialog.dismiss();

            }*/
            progressDialog.hide();

        };
    }
    public class LoadSubPharmacologyData extends AsyncTask<List<SubPharmacologyData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();


        }


        @Override
        protected Boolean doInBackground(List<SubPharmacologyData>... params)
        {

            myDatabase.insertSubPharmacology(params[0]);
//            myDBHelper.insertSubPharmacology(params[0]);
//            onData(diseaseManageDataList)


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
           /* super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();

            }*/


        };
    }
    public  class LoadMediaData extends AsyncTask<List<MediaData>,Void, Boolean> {


       /* MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        ProgressDialog progressDialog;*/
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<MediaData>... params)
        {

            myDatabase.insertMedia(params[0]);
//            myDBHelper.insertMedia(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();

          /*  super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();

            }*/


        };
    }

    public class LoadAppUserData extends AsyncTask<List<AppUserData>,Void, Boolean> {


//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("data loading please wait...");
            progressDialog.show();

        }


        @Override
        protected Boolean doInBackground(List<AppUserData>... params)
        {

            myDatabase.insertUserInfo(params[0]);
//            myDBHelper.insertUserInfo(params[0]);


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            progressDialog.hide();
           /* super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();

            }*/


        };
    }


    private float dpTopx(float dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics());
        return px;
    }
    private void getMarginMore(ImageView imageView){

        marginLayoutParams = (ViewGroup.MarginLayoutParams)imageView.getLayoutParams();
        marginLayoutParams.setMargins((int) dpTopx(15), (int) dpTopx(0), (int) dpTopx(15), (int) dpTopx(0));
    }
    private void getMarginLess(ImageView imageView){

        marginLayoutParams = (ViewGroup.MarginLayoutParams)imageView.getLayoutParams();
        marginLayoutParams.setMargins((int) dpTopx(10), (int) dpTopx(30), (int) dpTopx(10), (int) dpTopx(10));
    }

    private int getUserType(){

     int userType = 0;
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select user_type from user_info";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
       if(cursor != null && cursor.moveToFirst()){

           do{

              userType = cursor.getInt(cursor.getColumnIndex("user_type"));
           }while (cursor.moveToNext());

       }
        cursor.close();
        sqLiteDatabase.close();

        /*Intent intent = new Intent(WebViewTable.this,DoctorProfile.class);
        intent.putExtra("array",names);*/

        return userType;
    }

    private String getVerfication(){

        String verification = null;
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String query = "select verification from user_info";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor != null && cursor.moveToFirst()){

            do{

                verification = cursor.getString(cursor.getColumnIndex("verification"));
            }while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();

        /*Intent intent = new Intent(WebViewTable.this,DoctorProfile.class);
        intent.putExtra("array",names);*/

        return verification;
    }




    private void getMedicines() {
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();

        String query = "select medicines.id,medicines.sub_generic_name,medicines.medicine_subgroup_id,medicines.name,medicines.company_name,medicines_dosage._id,medicines_dosage.generic_type,medicines_dosage.unit,medicines_dosage.package_price,medicines_dosage.unit_price,medicines_dosage.package_method from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);


        if (cursor.getCount() > 0) {
            System.out.println("888888888888888888888888");


            int i = 0;
            String prevMedName = "";
            String prevCompanyName = "";
            String prevGenericType = "";

            String ddd = "";
            List<String> unitx = new ArrayList<String>();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            String MedicineName = "";
            String genericType = "";
            System.out.println("9999999999999999999999");

            if (cursor != null && cursor.moveToFirst()) {
//                System.out.println("100000000000000000000000");
                do {
                    id = cursor.getInt(cursor.getColumnIndex("_id"));
                    MedicineName = cursor.getString(cursor.getColumnIndex("name"));
                    String companyName = cursor.getString(cursor.getColumnIndex("company_name"));
                    genericType = cursor.getString(cursor.getColumnIndex("generic_type"));
                    String units = cursor.getString(cursor.getColumnIndex("unit"));
                    String unitPrice = cursor.getString(cursor.getColumnIndex("unit_price"));
                    String packagePrice = cursor.getString(cursor.getColumnIndex("package_price"));
                    medicineSubGroupId = cursor.getString(cursor.getColumnIndex("medicine_subgroup_id"));
                    genericName = cursor.getString(cursor.getColumnIndex("sub_generic_name"));
//                    System.out.println("medicine sub group id :" + medicineSubGroupId);

            /*System.out.println("Medicine Name : "+MedicineName);
            System.out.println("Unit : "+cursor.getString(cursor.getColumnIndex("unit")));
*/
                    if (MedicineName != null && companyName != null
                            && genericType != null && units != null
                            && unitPrice != null && packagePrice != null && medicineSubGroupId != null) {
                        if (i == 0) {

                            prevMedName = MedicineName;
//                            System.out.println("previousgenereic name .....................................: " + prevMedName + " and " + MedicineName);
                            ;
                            prevCompanyName = companyName;
                            System.out.println("previous company name............................... :" + prevCompanyName + " and " + companyName);
                            prevGenericType = genericType;
//                            System.out.println("previous generic type name............................... :" + prevGenericType + " and " + genericType);
                            i = 1;
                        }

                        if (!MedicineName.equals(prevMedName) || !genericType.equals(prevGenericType)) {
                /*for(int j = 0; j < unitx.size(); j++) {
                    System.out.println("uni array : " + unitx.get(j));
                }*/
//                            System.out.println("added drug  array list............................... :" + prevMedName + " and " + prevCompanyName + " and " + prevGenericType);

                            medicineList.add(new DrugsData(id, prevMedName, prevCompanyName, prevGenericType, genericName, stringBuilder.toString(), stringBuilder1.toString(), stringBuilder2.toString(), medicineSubGroupId));

                            prevMedName = MedicineName;

                            prevCompanyName = companyName;

                            prevGenericType = genericType;

                            stringBuilder.setLength(0);
                            stringBuilder1.setLength(0);
                            stringBuilder2.setLength(0);
                            stringBuilder.append(units);
                            stringBuilder.append("\n");
                            stringBuilder1.append(unitPrice);
                            stringBuilder1.append("\n");
                            String total = units + " * " + unitPrice + "=" + packagePrice + " Taka";
                            stringBuilder2.append(total);
                            stringBuilder2.append("\n");
              /*  unitx.clear();
                unitx.add(units);*/

                        } else {
//                unitx.add(units);
                            stringBuilder.append(units);
                            stringBuilder.append("\n");
                            stringBuilder1.append(unitPrice);
                            stringBuilder1.append("\n");
                            String total = units + " * " + unitPrice + " = " + packagePrice + " Taka";
                            stringBuilder2.append(total);
                            stringBuilder2.append("\n");
//                ddd = stringBuilder.toString();
//                System.out.println("string builder : "+ddd);
                        }
                    }

                } while (cursor.moveToNext());

            }

            cursor.close();
            sqLiteDatabase.close();
            if (prevMedName.equals("")) {


            } else if (!MedicineName.equals(prevMedName) || !genericType.equals(prevGenericType)) {
                drugsData = new DrugsData(id, prevMedName, prevCompanyName, prevGenericType, genericName, stringBuilder.toString(), stringBuilder1.toString(), stringBuilder2.toString(), medicineSubGroupId);
                medicineList.add(drugsData);
            } else if (MedicineName.equals(prevMedName) || genericType.equals(prevGenericType)) {
                drugsData = new DrugsData(id, prevMedName, prevCompanyName, prevGenericType, genericName, stringBuilder.toString(), stringBuilder1.toString(), stringBuilder2.toString(), medicineSubGroupId);
                medicineList.add(drugsData);
            }
           /* for(int j = 0; j <names.size(); j++ ){
                System.out.println("names total medName :"+names.get(j).getName());
            }*/



        }

       /* medicineJson = new Gson().toJson(medicineList);
        System.out.println("medicine json : "+medicineJson);*/
    }

    private void getDiseaseList() {


//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select * from disease_management ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            if(cursor != null && cursor.moveToFirst()){

                do{
                    diseaseDataArrayList.add(new DiseaseData(cursor.getString(cursor.getColumnIndex("disease_name")), cursor.getInt(cursor.getColumnIndex("_id"))));
                }while(cursor.moveToNext());
            }

            cursor.close();
            sqLiteDatabase.close();

        }




    }



    public  class LoadSearchData extends AsyncTask<Void, Void, Void> {

//        ProgressDialog progressDialog;
         /*progressDialog.setMessage("data loading please wait...");
            progressDialog.show();*/


        @Override
        protected void onPreExecute() {

            progressDialog.setMessage("please wait...");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            getMedicines();
            getDiseaseList();
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();

        }
    }


    public static void startActivity(final Context context, final String stringArg,
                                     final int intArg, final List<DrugsData> objectListMedicine,final List<DiseaseData> objectListDisease) {

        // Initialize a new intent
        final Intent intent = new Intent(context, SearchActivity.class);

        // To speed things up :)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        // And add arguments to the Intent
        intent.putExtra(ARG_STRING, stringArg);
        intent.putExtra(ARG_INT, intArg);

        // Now we put the large data into our enum instead of using Intent extras
        DataHolderMedicine.setData(objectListMedicine);
        DataHolderDisease.setData(objectListDisease);

        context.startActivity(intent);
    }
}


