package com.example.emon.bdmedic.Fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.emon.bdmedic.DoctorAndChamber.DoctorAndChamberData;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;

public class DoctorProfile extends AppCompatActivity {

    TextView dName,dDesignation,bmdcRegNo,academicInformation,chamberInfo,collgege,academicStatus,speciality,chamberOpen,chamberAdress,contact;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);


        dName = findViewById(R.id.dName);
        dDesignation = findViewById(R.id.dDesignation);
        bmdcRegNo = findViewById(R.id.bmdcRegNo);
        collgege = findViewById(R.id.college);
        academicStatus =findViewById(R.id.academicStatus);
        speciality  =findViewById(R.id.speciality);
        chamberOpen = findViewById(R.id.chamberOpen);
        chamberAdress = findViewById(R.id.chamberAddressDoctor);
        contact = findViewById(R.id.contactDoctor);
        /*academicInformation = findViewById(R.id.academicInformation);
        chamberInfo = findViewById(R.id.chamberInfo);*/

       /* ArrayList<DoctorAndChamberData> doctorAndChamberDataList = rcvArray.getExtras("array");
        for(int i = 0; i < doctorAndChamberDataList.size(); i++){

            System.out.println("successfully done : "+doctorAndChamberDataList.get(i).getName());
        }*/

//       dName.setText("hi this is doctor profile");

        Intent intent = getIntent();
        ArrayList<DoctorAndChamberData> doctorAndChamberData = intent.getParcelableArrayListExtra("arrayList");
        dName.setText(doctorAndChamberData.get(0).getName());
        dDesignation.setText(doctorAndChamberData.get(0).getDesignation());
        bmdcRegNo.setText(doctorAndChamberData.get(0).getBmdcRegNo());
//        academicStatus.setText(doctorAndChamberData.get(0).getAcademicCareer());
//        chamberInfo.setText(doctorAndChamberData.get(0).getChamberAddress());
        academicStatus.setText(doctorAndChamberData.get(0).getAcademicCareer());
        speciality.setText(doctorAndChamberData.get(0).getSpeciality());
        System.out.println("chamber open : "+doctorAndChamberData.get(0).getChamberOpens());
        chamberOpen.setText(doctorAndChamberData.get(0).getChamberOpens());
        System.out.println("chamber address : "+doctorAndChamberData.get(0).getChamberAddress());
        chamberAdress.setText(doctorAndChamberData.get(0).getChamberAddress());
        System.out.println("chamber address : "+doctorAndChamberData.get(0).getContactNo());
        contact.setText(doctorAndChamberData.get(0).getContactNo());
//        collgege.setText(doctorAndChamberData.get(0).get);

    }
}
