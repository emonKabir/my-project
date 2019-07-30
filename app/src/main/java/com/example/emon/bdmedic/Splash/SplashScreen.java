package com.example.emon.bdmedic.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.LoginActivity;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

public class SplashScreen extends AppCompatActivity {

    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myDatabase = new MyDatabase(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                SharedPreferences sharedPreferences = getSharedPreferences("logInTracker", Context.MODE_PRIVATE);
                if (sharedPreferences.contains("login")) {

                    boolean loginState = sharedPreferences.getBoolean("login", false);
                    if (loginState) {
                        Intent intent = new Intent(SplashScreen.this, BottomMenu.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }else{

                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }


            }
        }, 4000);
       /* if(menuFeed.isOnline()){
            System.out.println("33333333333333333");
          if(dateCheker() == null){
              System.out.println("44444444444444");
              final MenuFeed.LoadAllTable loadAllTable = null;
              loadAllTable.execute();
              System.out.println("55555555555555");
              new Handler().postDelayed(new Runnable() {

                  @Override
                  public void run() {
                      // This method will be executed once the timer is over
                      // Start your app main activity
                      Intent i = new Intent(SplashScreen.this, MenuFeed.class);
                      startActivity(i);

                      // close this activity
                      finish();
                  }
              }, 30000);
          }else {
              new Handler().postDelayed(new Runnable() {

                  @Override
                  public void run() {
                      // This method will be executed once the timer is over
                      // Start your app main activity
                      Intent i = new Intent(SplashScreen.this, MenuFeed.class);
                      startActivity(i);

                      // close this activity
                      finish();
                  }
              }, 3000);
          }
        }
*/
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
    public class LoadAllTable extends AsyncTask<Void,Void, Boolean> {




        @Override
        protected void onPreExecute() {
//            Toast.makeText(getActivity(),"start activity",Toast.LENGTH_SHORT).show();

           /* hideLayout.setVisibility(View.GONE);
            progressLayout.setVisibility(View.VISIBLE);*/
//           circularProgressBar.setBackgroundColor(Color.parseColor("#ffffff"));
//            circularProgressBar.setVisibility(View.VISIBLE);

           /* progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Data loading please wait...");
            progressDialog.show();*/

            super.onPreExecute();

//            System.out.println("enter in the excute system................................");

           /* progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);*/

        }


        @Override
        protected Boolean doInBackground(Void... params)
        {




           /* methodTesting.getBranch(initialDate,new MenuFeed.LoadBranchData());
            methodTesting.getBrief(initialDate,new MenuFeed.LoadBriefData());
            methodTesting.getRevisedBy(initialDate,new MenuFeed.LoadRevisedByData());
            methodTesting.getChamber(initialDate,new MenuFeed.LoadChamberData());
            methodTesting.getDoctors(initialDate,new MenuFeed.LoadDoctorData());
            methodTesting.getDiscipline(initialDate,new MenuFeed.LoadDesciplineData());
            methodTesting.getInvestigation(initialDate,new MenuFeed.LoadInvestigationData());
            methodTesting.getMedicineSubGroup(initialDate,new MenuFeed.LoadMedicineSubGroupData());
            methodTesting.getDisease(initialDate,new MenuFeed.LoadDiseaseManagementData());
            methodTesting.getTreatmentMedicine(initialDate,new MenuFeed.LoadTreatmentMedicineData());
            methodTesting.getMedicnes(initialDate,new MenuFeed.LoadMedicineData());
            methodTesting.getDosages(initialDate,new MenuFeed.LoadDosageData());
            methodTesting.getSystemData(initialDate,new MenuFeed.LoadSystemData());
            methodTesting.getCategoryData(initialDate,new MenuFeed.LoadCategoryData());
            methodTesting.getSystemCategoryData(initialDate,new MenuFeed.LoadSystemCategoryData());
            methodTesting.getSponsor(initialDate,new MenuFeed.LoadSponsorData());
            methodTesting.getHospital(initialDate,new MenuFeed.LoadHospitalData());
            methodTesting.getInvestigationPrice(initialDate,new MenuFeed.LoadInvestigationPriceData());
            methodTesting.getMedicinePharmacology(initialDate,new MenuFeed.LoadMedicinePharmacologyData());
            methodTesting.getPharmacologyInfo(initialDate,new MenuFeed.LoadPharmacologyInfoData());
            methodTesting.getSubPharmacology(initialDate,new MenuFeed.LoadSubPharmacologyData());
//            myDBHelper.insertCurrentTime(id,getCurrentTime());
            myDBHelper.close();
*/

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
}
