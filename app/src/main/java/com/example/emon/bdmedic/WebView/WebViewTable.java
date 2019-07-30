package com.example.emon.bdmedic.WebView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.emon.bdmedic.DoctorAndChamber.DoctorAndChamberData;
import com.example.emon.bdmedic.Fragments.DoctorProfile;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;

public class WebViewTable extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    MyDBHelper myDBHelper;
    String myTable ;
    String id;
    TextView investigation,advice,sign,symptom,onExamination,note,reference,addNote,doctorName;
    AlertDialog.Builder alertDialogBuilder;
    String diseaseName;
    AlertDialog alertDialog;
    ArrayList<DoctorAndChamberData> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_table);
         alertDialogBuilder = new AlertDialog.Builder(this);

        myDBHelper = new MyDBHelper(this);
        myDBHelper.getWritableDatabase();
//        MethodTesting methodTesting = new MethodTesting(this);
        webView = findViewById(R.id.webView);
        investigation = (TextView)  findViewById(R.id.investigation2);
        advice = (TextView)  findViewById(R.id.advice2);
        sign = (TextView)  findViewById(R.id.sign2);
        symptom = (TextView)  findViewById(R.id.symptom);
        onExamination = (TextView)  findViewById(R.id.onExamination);
        note    = (TextView)  findViewById(R.id.note);
        reference    = (TextView)  findViewById(R.id.reference);
        addNote    = (TextView)  findViewById(R.id.addNote);
        doctorName = findViewById(R.id.doctorName2);
        Intent rcvDisease = getIntent();
         diseaseName = rcvDisease.getStringExtra("diseaseName");
        names = new ArrayList<DoctorAndChamberData>();
        getBrief(diseaseName);
        getDoctor(diseaseName);

        doctorName.setText(names.get(0).getName());
        doctorName.setOnClickListener(this);
        investigation.setOnClickListener(this);
        advice.setOnClickListener(this);
        sign.setOnClickListener(this);
        symptom.setOnClickListener(this);
        onExamination.setOnClickListener(this);
        note.setOnClickListener(this);
        reference.setOnClickListener(this);
        addNote.setOnClickListener(this);


    }

    public void getDoctor(String diseaseName){

        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String query = "select doctor.*,chamber.* from doctor left join revised_by on doctor.id = revised_by.doctor_id left join disease_management on revised_by.disease_management_id = disease_management._id left join chamber on chamber.doctor_id = doctor.id where disease_management.disease_name =  \""+diseaseName+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        int k = 0;
        cursor.moveToFirst();

        myTable = "";

        while(!cursor.isAfterLast()){
            names.add(new DoctorAndChamberData(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("sex")),cursor.getInt(cursor.getColumnIndex("blood_group")),cursor.getInt(cursor.getColumnIndex("present_dist")),cursor.getInt(cursor.getColumnIndex("present_upa")),cursor.getInt(cursor.getColumnIndex("permanent_dist")),cursor.getInt(cursor.getColumnIndex("permanent_upa")),cursor.getInt(cursor.getColumnIndex("user_type")),cursor.getString(cursor.getColumnIndex("email")),cursor.getInt(cursor.getColumnIndex("medical_id")),cursor.getString(cursor.getColumnIndex("batch_no")),cursor.getString(cursor.getColumnIndex("roll_no")),cursor.getInt(cursor.getColumnIndex("reg_type")),cursor.getInt(cursor.getColumnIndex("doctor_id")),cursor.getString(cursor.getColumnIndex("bmdc_reg_no")),cursor.getString(cursor.getColumnIndex("designation")),cursor.getString(cursor.getColumnIndex("speciality")),cursor.getString(cursor.getColumnIndex("academic_career")),cursor.getInt(cursor.getColumnIndex("post_grad_branch_1")),cursor.getInt(cursor.getColumnIndex("post_grad_branch_2")),cursor.getString(cursor.getColumnIndex("chamber_address")),cursor.getInt(cursor.getColumnIndex("cham_dist")),cursor.getInt(cursor.getColumnIndex("cham_upa")),cursor.getString(cursor.getColumnIndex("contact_no")),cursor.getString(cursor.getColumnIndex("chamber_opens"))));
            cursor.moveToNext();
            k++;

        }
        cursor.close();
        sqLiteDatabase.close();
        for(int i = 0; i < names.size(); i++){
            System.out.println("Arralist Name "+i+": "+names.get(i).getName());

        }
        /*Intent intent = new Intent(WebViewTable.this,DoctorProfile.class);
        intent.putExtra("array",names);*/

    }
    public void getBrief(String diseaseName){

        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String query = "select * from brief left join disease_management on disease_management._id = brief.disease_management_id where disease_management.disease_name = \""+diseaseName+"\"";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        myTable = "";
        while(!cursor.isAfterLast()){
//            names.add(cursor.getString(cursor.getColumnIndex("branch_name")));
//            myTable += cursor.getString(cursor.getColumnIndex("brief"));
            id = cursor.getString(cursor.getColumnIndex("id"));

            String trtmntQuery = "select treatment_and_medicine.*, medicines_dosage.*, medicines.* from treatment_and_medicine  left join medicines_dosage on treatment_and_medicine.dosage_id = medicines_dosage._id left join medicines on treatment_and_medicine.medicine_id = medicines.id where treat_brief_id = \""+id+"\"";
            Cursor trtmntCursor = sqLiteDatabase.rawQuery(trtmntQuery,null);
            trtmntCursor.moveToFirst();
            while (!trtmntCursor.isAfterLast()){
                String trtmnt = "<p>"+trtmntCursor.getString(trtmntCursor.getColumnIndex("generic_type"))+" "+trtmntCursor.getString(trtmntCursor.getColumnIndex("name"))+" "+trtmntCursor.getString(trtmntCursor.getColumnIndex("unit"))+" </p><br/>";
                myTable += trtmnt;
                String freqencyDose = "<p>"+trtmntCursor.getString(trtmntCursor.getColumnIndex("dose_frequency"))+"</p><br/>";
                myTable += freqencyDose;
                String relation;
                if(trtmntCursor.getString(trtmntCursor.getColumnIndex("relation")) != null){
                    relation  = "<p>"+trtmntCursor.getString(trtmntCursor.getColumnIndex("relation"))+"</p><br/>";
                   myTable += relation;

                }
                trtmntCursor.moveToNext();

            }
            System.out.println("table data : "+myTable);
            System.out.println("brief id : "+id);
            //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();
        webView.loadDataWithBaseURL(null,myTable,"text/html","utf-8",null);
    }



    @Override
    public void onClick(View v) {
       switch (v.getId()){

           case R.id.investigation2:
               getInfo("investigation",diseaseName);
               break;

           case R.id.advice2:
               getInfo("advice",diseaseName);
               break;

           case R.id.sign2:
               getInfo("sign2",diseaseName);
               break;

           case R.id.symptom:
               getInfo("symtom",diseaseName);
               break;

           case R.id.onExamination:
               getInfo("on_examination",diseaseName);
               break;

           case R.id.note:
               getInfo("note",diseaseName);
               break;

           case R.id.reference:
               getInfo("reference",diseaseName);
               break;

           case R.id.addNote:
               getInfo("add_note",diseaseName);
               break;
           case R.id.doctorName2:
               Intent intent = new Intent(WebViewTable.this, DoctorProfile.class);
               intent.putExtra("arrayList",names);
               startActivity(intent);

       }

    }

    private void getInfo(String columnName,String diseaseName){
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String column = null;
        String query;
        if(columnName == "investigation"){
            query = "select * from disease_management left join investigation on disease_management.investigation_id = investigation.id where disease_name = \""+diseaseName+"\"";
        }else{
            query = "select * from disease_management  where disease_name = \""+diseaseName+"\"";
        }

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
//            names.add(cursor.getString(cursor.getColumnIndex("branch_name")));
            if(columnName == "investigation") {
                column = cursor.getString(cursor.getColumnIndex("name"))+"\n"+ cursor.getString(cursor.getColumnIndex("normal_finding"));
            }else{
                column = cursor.getString(cursor.getColumnIndex(columnName));
            }

            //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();
        setDialog(columnName,column);

    }

    private void setDialog(String columnname,String column){
        alertDialogBuilder.setTitle(columnname);
        alertDialogBuilder.setMessage(column);


        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               dialog.cancel();
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
