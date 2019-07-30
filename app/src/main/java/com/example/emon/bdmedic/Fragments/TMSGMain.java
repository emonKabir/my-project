package com.example.emon.bdmedic.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.emon.bdmedic.Api;
import com.example.emon.bdmedic.ApiInterface;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.MethodTesting;
import com.example.emon.bdmedic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TMSGMain extends AppCompatActivity implements View.OnClickListener {

    Fragment selectedFragment;
    TextView medicineTxtview,surgeryTxtview,gynacologyTxtview;
    List<DiseaseManageData> diseaseManageList;
    List<BranchAndDisciplineData> branchAndDisciplineDataList;
    MedicineFragment medicineFragment;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmsgmain);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);

//        MyDBHelper myDBHelper = new MyDBHelper(this);
//        myDBHelper.getWritableDatabase();
        myDatabase = new MyDatabase(this);
        Intent intent = getIntent();
        String clkd = intent.getStringExtra("clickButton");

        medicineTxtview = findViewById(R.id.medicineTxtview);
        surgeryTxtview = findViewById(R.id.surgeryTxtview);
        gynacologyTxtview = findViewById(R.id.gynacologyTxtview);
        medicineTxtview.setOnClickListener(this);
        surgeryTxtview.setOnClickListener(this);
        gynacologyTxtview.setOnClickListener(this);
        defaultActivity(clkd);
//        getDisease();
        MethodTesting methodTesting = new MethodTesting(this);
//        methodTesting.getBranch();

//        methodTesting.getDiscipline();
//        methodTesting.getBrief();
//        methodTesting.getTreatmentMedicine();
//        methodTesting.getDoctors();
//        methodTesting.getChamber();
//          methodTesting.getRevisedBy();

    }

    @Override
    public void onClick(View v) {
        selectedFragment = null;
        switch (v.getId()){


            case R.id.medicineTxtview:
                selectedTabColorMedicine();

               /* medicineTxtview.setBackgroundResource(R.color.DarkGreen);
                surgeryTxtview.setBackgroundResource(R.color.lightGreen);
                gynacologyTxtview.setBackgroundResource(R.color.lightGreen);*/
                /*medicineTxtview.setBackgroundColor(Color.GREEN);
                surgeryTxtview.setBackgroundColor(Color.RED);
                gynacologyTxtview.setBackgroundColor(Color.RED);*/
                selectedFragment =  new MedicineFragment();
                break;
            case R.id.surgeryTxtview:

                selectedTabColorSurgery();
               /* medicineTxtview.setBackgroundResource(R.color.lightGreen);
                surgeryTxtview.setBackgroundResource(R.color.DarkGreen);
                gynacologyTxtview.setBackgroundResource(R.color.lightGreen);*/
                /*medicineTxtview.setBackgroundColor(Color.RED);
                surgeryTxtview.setBackgroundColor(Color.GREEN);
                gynacologyTxtview.setBackgroundColor(Color.RED);*/
                selectedFragment =  new SurgeryFragment();

                break;
            case R.id.gynacologyTxtview:

                selectedTabColorGyne();
               /* medicineTxtview.setBackgroundResource(R.color.lightGreen);
                surgeryTxtview.setBackgroundResource(R.color.lightGreen);
                gynacologyTxtview.setBackgroundResource(R.color.DarkGreen);*/
                /*medicineTxtview.setBackgroundColor(Color.RED);
                surgeryTxtview.setBackgroundColor(Color.RED);
                gynacologyTxtview.setBackgroundColor(Color.GREEN);*/
                selectedFragment =  new GynacologyFragment();

                break;

        }
        FragmentManager childFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.recentMain, selectedFragment);
        ft.commit();
    }

    public void defaultActivity(String string){
        selectedFragment = null;
        switch (string){


            case "1":

                selectedTabColorMedicine();
               /* medicineTxtview.setBackgroundResource(R.color.DarkGreen);
                surgeryTxtview.setBackgroundResource(R.color.lightGreen);
                gynacologyTxtview.setBackgroundResource(R.color.lightGreen);
                medicineTxtview.setTextColor(getResources().getColor(R.color.colorWhite));
                surgeryTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
                medicineTxtview.setTextColor(getResources().getColor(R.color.ericBlack));*/
//               medicineTxtview.setText("bgjhgjhgjh");
                /*medicineTxtview.setBackgroundColor(Color.GREEN);
                surgeryTxtview.setBackgroundColor(Color.RED);
                gynacologyTxtview.setBackgroundColor(Color.RED);*/
                selectedFragment =  new MedicineFragment();
                break;
            case "2":

                selectedTabColorSurgery();
               /* medicineTxtview.setBackgroundResource(R.color.lightGreen);
                surgeryTxtview.setBackgroundResource(R.color.DarkGreen);
                gynacologyTxtview.setBackgroundResource(R.color.lightGreen);*/
                /*medicineTxtview.setBackgroundColor(Color.RED);
                surgeryTxtview.setBackgroundColor(Color.GREEN);
                gynacologyTxtview.setBackgroundColor(Color.RED);*/
                selectedFragment =  new SurgeryFragment();
                break;
            case "3":
                selectedTabColorGyne();
               /* medicineTxtview.setBackgroundResource(R.color.lightGreen);
                surgeryTxtview.setBackgroundResource(R.color.lightGreen);
                gynacologyTxtview.setBackgroundResource(R.color.DarkGreen);*/
               /* medicineTxtview.setBackgroundColor(Color.RED);
                surgeryTxtview.setBackgroundColor(Color.RED);
                gynacologyTxtview.setBackgroundColor(Color.GREEN);*/
                selectedFragment =  new GynacologyFragment();
                break;

        }
        FragmentManager childFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.recentMain, selectedFragment);
        ft.commit();

    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

private void selectedTabColorMedicine(){

    medicineTxtview.setBackgroundResource(R.color.DarkGreen);
    surgeryTxtview.setBackgroundResource(R.color.lightGreen);
    gynacologyTxtview.setBackgroundResource(R.color.lightGreen);
    medicineTxtview.setTextColor(getResources().getColor(R.color.colorWhite));
    surgeryTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
    gynacologyTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
}

    private void selectedTabColorSurgery(){

        medicineTxtview.setBackgroundResource(R.color.lightGreen);
        surgeryTxtview.setBackgroundResource(R.color.DarkGreen);
        gynacologyTxtview.setBackgroundResource(R.color.lightGreen);
        medicineTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
        surgeryTxtview.setTextColor(getResources().getColor(R.color.colorWhite));
        gynacologyTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
    }

    private void selectedTabColorGyne(){

        medicineTxtview.setBackgroundResource(R.color.lightGreen);
        surgeryTxtview.setBackgroundResource(R.color.lightGreen);
        gynacologyTxtview.setBackgroundResource(R.color.DarkGreen);
        medicineTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
        surgeryTxtview.setTextColor(getResources().getColor(R.color.ericBlack));
        gynacologyTxtview.setTextColor(getResources().getColor(R.color.colorWhite));
    }
}
