package com.example.emon.bdmedic.WebView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.DoctorAndChamber.DoctorAndChamberData;
import com.example.emon.bdmedic.Fragments.DoctorProfile;
import com.example.emon.bdmedic.Fragments.ShowMedicineDetails.CustomAdapterForMedicineDetails;
import com.example.emon.bdmedic.Fragments.ShowMedicineDetails.MedicineDetails;
import com.example.emon.bdmedic.Fragments.ShowMedicineDetails.NonScrollListview;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.Pharmacologist.MedicineView;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;

public class SampleTable extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    AlertDialog alertDialog;
    ArrayList<DoctorAndChamberData> doctorAndChamberDataArrayList;
    AlertDialog.Builder alertDialogBuilder;
    ListView listViewMedicineDetails;
    MedicineDetails medicineDetails;

    ArrayList<MedicineDetails> medicineDetailsArrayList;
    CustomAdapterForMedicineDetails customAdapterForMedicineDetails;
    NonScrollListview nonScrollListview;
    TextView investigation,advice,sign,symptom,onExamination,note,reference,addNote,doctorName;
    String myTable2 = "<TABLE border=1 bordercolor=Red>\n" +
            "<TR>\n" +
            "<TD>Cell 1</TD>\n" +
            "<TD>Cell 2</TD>\n" +
            "<TD>Cell 3</TD></TR>\n" +
            "<TR>\n" +
            "<TD>Cell 4</TD>\n" +
            "<TD>Cell 5</TD>\n" +
            "<TD>Cell 6</TD></TR>\n" +
            "<TR>\n" +
            "<TD>Cell 7</TD>\n" +
            "<TD>Cell 8</TD>\n" +
            "<TD>Cell 9</TD></TR>\n" +
            "</TABLE>";
    String diseaseName;
    String id;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_table);

//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
//        myDBHelper.getWritableDatabase();
        alertDialogBuilder = new AlertDialog.Builder(this);
        webView = findViewById(R.id.webView2);
        investigation = (TextView)  findViewById(R.id.investigationS);
        advice = (TextView)  findViewById(R.id.adviceS);
        sign = (TextView)  findViewById(R.id.signS);
//        listViewMedicineDetails = findViewById(R.id.listViewMedicineDetails);
        symptom = (TextView)  findViewById(R.id.symptomS);
        nonScrollListview = findViewById(R.id.listViewMedicineDetails);
        onExamination = (TextView)  findViewById(R.id.onExaminationS);
        note    = (TextView)  findViewById(R.id.noteS);
        reference    = (TextView)  findViewById(R.id.referenceS);
        addNote    = (TextView)  findViewById(R.id.addNoteS);
        doctorName = findViewById(R.id.doctorNameS);
        doctorAndChamberDataArrayList = new ArrayList<DoctorAndChamberData>();
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);



//        getDoctor(diseaseName);


//
        doctorName.setOnClickListener(this);
        investigation.setOnClickListener(this);
        advice.setOnClickListener(this);
        sign.setOnClickListener(this);

        symptom.setOnClickListener(this);
        onExamination.setOnClickListener(this);
        note.setOnClickListener(this);
        reference.setOnClickListener(this);
        addNote.setOnClickListener(this);
        Intent rcvDisease = getIntent();
        diseaseName = rcvDisease.getStringExtra("diseaseName");

        System.out.println("sample table.................... : "+diseaseName);
        getBrief(diseaseName);
        getDoctor(diseaseName);
        if(!doctorAndChamberDataArrayList.get(0).getName().isEmpty()){
            doctorName.setText(doctorAndChamberDataArrayList.get(0).getName());
        }


     nonScrollListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             MedicineDetails medicineDetails = (MedicineDetails) parent.getItemAtPosition(position);



             if(!medicineDetails.getFirstTextView().equals("") && !medicineDetails.getThirdTextView().equals("")){
                       /* Intent intent = new Intent(activity,MedicineTesting.class);

                        activity.startActivity(intent);*/

                       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
                          holder.firstTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkRed));
                          holder.secondTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkRed));
                          holder.thirdTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkRed));
                        }else{
                           holder.firstTextView.setTextColor(activity.getResources().getColor(R.color.DarkRed));
                           holder.secondTextView.setTextColor(activity.getResources().getColor(R.color.DarkRed));
                           holder.thirdTextView.setTextColor(activity.getResources().getColor(R.color.DarkRed));
                        }*/

                 final  String medicineName = medicineDetails.getSecondTextView();
                 System.out.println("custom adapter selected medicine name.......... : "+medicineName);
                 final String currentGenericType = medicineDetails.getFirstTextView();
                 final String currentGenericName = medicineDetails.getGenericName();
                 final String currentMedicicneSubGroupId = medicineDetails.getMedicineSubGroupId();
                 System.out.println("custom adapter selected genric name.......... : "+currentGenericName);
                 System.out.println("custom adapter selected genric type.......... : "+currentGenericType);
                 if(currentGenericName != null && currentGenericType != null && currentMedicicneSubGroupId != null && medicineName != null){
                     Intent intent = new Intent(SampleTable.this, MedicineView.class);
                     Bundle extras = new Bundle();
                     extras.putString("medicineName",medicineName);
                     extras.putString("genericType",currentGenericType);
                     extras.putString("genericName",currentGenericName);
                     extras.putString("medicineSubGroupId",currentMedicicneSubGroupId);
                     intent.putExtras(extras);
                     startActivity(intent);
                 }

             }




         }
     });
    }

    private void getBrief(String diseaseName) {

        System.out.println("start from here............ 1: ");
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select * from brief left join disease_management on disease_management._id = brief.disease_management_id where disease_management.disease_name = \""+diseaseName+"\"";
        System.out.println("start from here............ 2: ");
        System.out.println("start from here............ 3: ");
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        myTable2 = "";
        while(!cursor.isAfterLast()){
            System.out.println("start from here............ 4: ");
            myTable2 += cursor.getString(cursor.getColumnIndex("brief"));

            id = cursor.getString(cursor.getColumnIndex("id"));

            String trtmntQuery = "select treatment_and_medicine.*, medicines_dosage.*, medicines.* from treatment_and_medicine  left join medicines_dosage on treatment_and_medicine.dosage_id = medicines_dosage._id left join medicines on treatment_and_medicine.medicine_id = medicines.id where treat_brief_id = \""+id+"\"";
            Cursor trtmntCursor = sqLiteDatabase.rawQuery(trtmntQuery,null);
            medicineDetailsArrayList = new ArrayList<MedicineDetails>();

            trtmntCursor.moveToFirst();
            while (!trtmntCursor.isAfterLast()){
               /* String trtmnt = "<p>"+trtmntCursor.getString(trtmntCursor.getColumnIndex("generic_type"))+" "+trtmntCursor.getString(trtmntCursor.getColumnIndex("name"))+" "+trtmntCursor.getString(trtmntCursor.getColumnIndex("unit"))+" </p><br/>";
                myTable2 += trtmnt;
                String freqencyDose = "<p>"+trtmntCursor.getString(trtmntCursor.getColumnIndex("dose_frequency"))+"</p><br/>";
                myTable2 += freqencyDose;
                String relation;
                if(trtmntCursor.getString(trtmntCursor.getColumnIndex("relation")) != null){
                    relation  = "<p>"+trtmntCursor.getString(trtmntCursor.getColumnIndex("relation"))+"</p><br/>";
                    myTable2 += relation;

                }*/

                System.out.println("start from here............ 5: ");
                 medicineDetails = new MedicineDetails(trtmntCursor.getString(trtmntCursor.getColumnIndex("generic_type")),trtmntCursor.getString(trtmntCursor.getColumnIndex("name")),trtmntCursor.getString(trtmntCursor.getColumnIndex("unit")),trtmntCursor.getString(trtmntCursor.getColumnIndex("sub_generic_name")),trtmntCursor.getString(trtmntCursor.getColumnIndex("medicine_subgroup_id")));
               medicineDetailsArrayList.add(medicineDetails);
                medicineDetails = new MedicineDetails("",trtmntCursor.getString(trtmntCursor.getColumnIndex("dose_frequency")),"","","");
               medicineDetailsArrayList.add(medicineDetails);
               if(trtmntCursor.getString(trtmntCursor.getColumnIndex("relation")) != null){

           medicineDetails = new MedicineDetails("",trtmntCursor.getString(trtmntCursor.getColumnIndex("relation")),"","","");
                    medicineDetailsArrayList.add(medicineDetails);
               }
                trtmntCursor.moveToNext();

            }

            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();
        webView.loadDataWithBaseURL(null,myTable2,"text/html","utf-8",null);
        customAdapterForMedicineDetails = new CustomAdapterForMedicineDetails(SampleTable.this,medicineDetailsArrayList);
        nonScrollListview.setAdapter(customAdapterForMedicineDetails);

    }
    public void getDoctor(String diseaseName){

//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select doctor.*,chamber.* from doctor left join revised_by on doctor.id = revised_by.doctor_id left join disease_management on revised_by.disease_management_id = disease_management._id left join chamber on chamber.doctor_id = doctor.id where disease_management.disease_name =  \""+diseaseName+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();

        if (cursor != null && cursor.moveToFirst()){

            do{
                System.out.println("chammber from sama  :"+cursor.getString(cursor.getColumnIndex("chamber_opens"))+" "+cursor.getString(cursor.getColumnIndex("chamber_address"))+" "+cursor.getString(cursor.getColumnIndex("contact_no")));
                doctorAndChamberDataArrayList.add(new DoctorAndChamberData(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("sex")),cursor.getInt(cursor.getColumnIndex("blood_group")),cursor.getInt(cursor.getColumnIndex("present_dist")),cursor.getInt(cursor.getColumnIndex("present_upa")),cursor.getInt(cursor.getColumnIndex("permanent_dist")),cursor.getInt(cursor.getColumnIndex("permanent_upa")),cursor.getInt(cursor.getColumnIndex("user_type")),cursor.getString(cursor.getColumnIndex("email")),cursor.getInt(cursor.getColumnIndex("medical_id")),cursor.getString(cursor.getColumnIndex("batch_no")),cursor.getString(cursor.getColumnIndex("roll_no")),cursor.getInt(cursor.getColumnIndex("reg_type")),cursor.getInt(cursor.getColumnIndex("doctor_id")),cursor.getString(cursor.getColumnIndex("bmdc_reg_no")),cursor.getString(cursor.getColumnIndex("designation")),cursor.getString(cursor.getColumnIndex("speciality")),cursor.getString(cursor.getColumnIndex("academic_career")),cursor.getInt(cursor.getColumnIndex("post_grad_branch_1")),cursor.getInt(cursor.getColumnIndex("post_grad_branch_2")),cursor.getString(cursor.getColumnIndex("chamber_address")),cursor.getInt(cursor.getColumnIndex("cham_dist")),cursor.getInt(cursor.getColumnIndex("cham_upa")),cursor.getString(cursor.getColumnIndex("contact_no")),cursor.getString(cursor.getColumnIndex("chamber_opens"))));
                cursor.moveToNext();
            }while (cursor.moveToNext());
        }
      /*  while(!cursor.isAfterLast()){

            System.out.println("chammber from sama  :"+cursor.getString(cursor.getColumnIndex("chamber_opens"))+" "+cursor.getString(cursor.getColumnIndex("chamber_address"))+" "+cursor.getString(cursor.getColumnIndex("contact_no")));
            doctorAndChamberDataArrayList.add(new DoctorAndChamberData(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("sex")),cursor.getInt(cursor.getColumnIndex("blood_group")),cursor.getInt(cursor.getColumnIndex("present_dist")),cursor.getInt(cursor.getColumnIndex("present_upa")),cursor.getInt(cursor.getColumnIndex("permanent_dist")),cursor.getInt(cursor.getColumnIndex("permanent_upa")),cursor.getInt(cursor.getColumnIndex("user_type")),cursor.getString(cursor.getColumnIndex("email")),cursor.getInt(cursor.getColumnIndex("medical_id")),cursor.getString(cursor.getColumnIndex("batch_no")),cursor.getString(cursor.getColumnIndex("roll_no")),cursor.getInt(cursor.getColumnIndex("reg_type")),cursor.getInt(cursor.getColumnIndex("doctor_id")),cursor.getString(cursor.getColumnIndex("bmdc_reg_no")),cursor.getString(cursor.getColumnIndex("designation")),cursor.getString(cursor.getColumnIndex("speciality")),cursor.getString(cursor.getColumnIndex("academic_career")),cursor.getInt(cursor.getColumnIndex("post_grad_branch_1")),cursor.getInt(cursor.getColumnIndex("post_grad_branch_2")),cursor.getString(cursor.getColumnIndex("chamber_address")),cursor.getInt(cursor.getColumnIndex("cham_dist")),cursor.getInt(cursor.getColumnIndex("cham_upa")),cursor.getString(cursor.getColumnIndex("contact_no")),cursor.getString(cursor.getColumnIndex("chamber_opens"))));
            cursor.moveToNext();


        }*/
        cursor.close();
        sqLiteDatabase.close();

        /*Intent intent = new Intent(WebViewTable.this,DoctorProfile.class);
        intent.putExtra("array",names);*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.investigationS:
                getInfo("investigation",diseaseName);
//                Toast.makeText(SampleTable.this,"investigation clicked",Toast.LENGTH_LONG).show();
                break;

            case R.id.adviceS:
                getInfo("advice",diseaseName);
                break;

            case R.id.signS:
                getInfo("sign",diseaseName);
                break;

            case R.id.symptomS:
                getInfo("symtom",diseaseName);
                break;

            case R.id.onExaminationS:
                getInfo("on_examination",diseaseName);
                break;

            case R.id.noteS:
                getInfo("note",diseaseName);
                break;

            case R.id.referenceS:
//                getInfo("reference",diseaseName);
                Toast.makeText(SampleTable.this,"This module is under construction",Toast.LENGTH_LONG).show();
                break;

            case R.id.addNoteS:
//                getInfo("add_note",diseaseName);
                Toast.makeText(SampleTable.this,"This module is under construction",Toast.LENGTH_LONG).show();
                break;
            case R.id.doctorNameS:
                Intent intent = new Intent(SampleTable.this, DoctorProfile.class);
                intent.putExtra("arrayList",doctorAndChamberDataArrayList);
                startActivity(intent);
//                Toast.makeText(SampleTable.this,"This module is under construction",Toast.LENGTH_LONG).show();


        }
    }
    private void getInfo(String columnName,String diseaseName){
        System.out.println("step 1..................................... :");
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        System.out.println("step 2..................................... :");
        String column = null;
        String query;
        if(columnName.equals("investigation") ){
            System.out.println("step 3..................................... :");
            query = "select * from disease_management left join investigation on disease_management.investigation_id = investigation.id where disease_name = \""+diseaseName+"\"";
            System.out.println("step 4..................................... :");
        }else{
            System.out.println("step 5..................................... :");
            query = "select * from disease_management  where disease_name = \""+diseaseName+"\"";
            System.out.println("step 6..................................... :");
        }

        System.out.println("step 7..................................... :");
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        System.out.println("step 8..................................... :");
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
//            names.add(cursor.getString(cursor.getColumnIndex("branch_name")));
            if(columnName.equals("investigation")) {
                System.out.println("step 9..................................... :");

                column = cursor.getString(cursor.getColumnIndex("name"))+"\n"+ cursor.getString(cursor.getColumnIndex("normal_finding"));
                System.out.println("step 10..................................... :"+column);
            }else{
                System.out.println("step 11..................................... :");
                column = cursor.getString(cursor.getColumnIndex(columnName));
                System.out.println("column................................... :"+column);
            }

            //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();
        setDialog(columnName,column);

    }
    private void setDialog(String columnname,String column){
        System.out.println("lllllllllllllllllllllllllllllllllllll");
        alertDialogBuilder.setTitle(columnname);
        alertDialogBuilder.setMessage(column);
        System.out.println("ppppppppppppppppppppppppppppppppppppppppppppppppppp");


        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppkkkkkkkkkkkkkkkkk");
        alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppkkkkkkkkkkkkkkkkklllllllll");
        alertDialog.show();
        System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppkkkkkkkkkkkkkkkkklllllllll2222222");

    }
}
