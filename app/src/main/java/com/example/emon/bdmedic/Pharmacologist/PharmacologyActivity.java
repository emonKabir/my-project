package com.example.emon.bdmedic.Pharmacologist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.Fragments.ShowMedicineDetails.NonScrollListview;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.List;

public class PharmacologyActivity extends AppCompatActivity implements View.OnClickListener{

//    ListView listViewDrugs;
    NonScrollListview listViewDrugs;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    int id;
    //    String rcvGenericName;
    public List<DrugsData> medicineviewList;
    private List<DrugsData> lastItem;
    DrugsData drugsData;
    String rcvPharmacologyPermission;
    String rcvMedicineSubGroupId;
    TextView genericName;
    AlertDialog alertDialog;
    AlertDialog.Builder alertDialogBuilder;
    LinearLayout generateLayout2;
    String pharmaGenericName;
    String rcvMedicineName,rcvGenericType,rcvGenericName,rcvUnit,rcvUnitPrice,rcvCompany,rcvPackagePrice;
    LinearLayout medicineViewLayout;
    List<PharmacologyDes>pharmacologyList;
    DrugsData lastItemDrug;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacology);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);

//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
        listViewDrugs = findViewById(R.id.listviewDrugsViews);
        genericName = findViewById(R.id.medicineGenericBack);
        medicineViewLayout = findViewById(R.id.medicineViewLayout);
        alertDialogBuilder = new AlertDialog.Builder(this);
        generateLayout2 = findViewById(R.id.generateLayout2);
        pharmacologyList = new ArrayList<>();
        genericName.setOnClickListener(this);
        lastItem = new ArrayList<>();
        System.out.println("first intent.......................");
        Intent pharmacologyIntent = getIntent();
        if(pharmacologyIntent != null){


            rcvPharmacologyPermission = pharmacologyIntent.getStringExtra("from");
            System.out.println("second line....................... : "+rcvPharmacologyPermission);
            pharmaGenericName = pharmacologyIntent.getStringExtra("genericName");
            rcvMedicineSubGroupId = pharmacologyIntent.getStringExtra("medicineSubGroupId");
            System.out.println("pharma generic line....................... : "+pharmaGenericName);
            if(rcvPharmacologyPermission != null && pharmaGenericName != null && rcvMedicineSubGroupId != null){
                getPharmacologySub();
            }
        }


//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "";

            System.out.println("possible to in...................");
            query = "select medicines.id,medicines.name,medicines.company_name,medicines_dosage.generic_type,medicines_dosage.unit,medicines_dosage.package_price,medicines_dosage.unit_price,medicines_dosage.package_method from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id left join sponsor on medicines.company_id = sponsor.supplier_id where medicines.sub_generic_name = \"" + pharmaGenericName + "\" order by medicines_dosage.generic_type, ifnull(sponsor.rank,9999), medicines.name limit 3";
            System.out.println("next step to in...................");
            medicineViewLayout.setVisibility(View.VISIBLE);


        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            medicineviewList = new ArrayList<DrugsData>();
            int i = 0;
            String prevMedName = "";
            String prevCompanyName = "";
            String prevGenericType = "";
            String ddd = "";
//                List<String>unitx =   new ArrayList<String>();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            String MedicineName = "";
            String generic_type = "";
            while(!cursor.isAfterLast()){

                id = cursor.getInt(cursor.getColumnIndex("id"));
                MedicineName = cursor.getString(cursor.getColumnIndex("name"));
                String companyName = cursor.getString(cursor.getColumnIndex("company_name"));
                String genericType = cursor.getString(cursor.getColumnIndex("generic_type"));
                String units = cursor.getString(cursor.getColumnIndex("unit"));
                String unitPrice = cursor.getString(cursor.getColumnIndex("unit_price"));
                System.out.println("Medicine Name : "+MedicineName);
                System.out.println("Unit : "+cursor.getString(cursor.getColumnIndex("unit")));
                String packagePrice = cursor.getString(cursor.getColumnIndex("package_price"));

                if(i == 0){
                    prevMedName = MedicineName;
                    System.out.println("previousgenereic name custom adapter.....................................: "+prevMedName +" and " +MedicineName);;
                    prevCompanyName = companyName;
                    System.out.println("previous company name custom adapter............................... :"+prevCompanyName+" and "+companyName);
                    prevGenericType = genericType;
                    System.out.println("previous generic type name custom adapter............................... :"+prevGenericType+" and "+genericType);
                    i = 1;
                }

                if(!MedicineName.equals(prevMedName)) {

                    System.out.println("added drug  array list custom adapter............................... :"+prevMedName+" and "+prevCompanyName+" and "+prevGenericType);
                    drugsData = new DrugsData(id,prevMedName,prevCompanyName ,prevGenericType ,pharmaGenericName , stringBuilder.toString(),stringBuilder1.toString(),stringBuilder2.toString());
                    medicineviewList.add(drugsData);

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
                    String total = units+" * "+unitPrice+"="+packagePrice+" Taka";
                    stringBuilder2.append(total);
                    stringBuilder2.append("\n");


                } else {
//
                    stringBuilder.append(units);
                    stringBuilder.append("\n");
                    stringBuilder1.append(unitPrice);
                    stringBuilder1.append("\n");
                    String total = units+" * "+unitPrice+"="+packagePrice+" Taka";
                    stringBuilder2.append(total);
                    stringBuilder2.append("\n");

                }

                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
            if(prevMedName.equals("")){

                Toast.makeText(this,"No medicine found under this generic name",Toast.LENGTH_SHORT).show();
            }
            else if(!MedicineName.equals(prevMedName)) {
                drugsData = new DrugsData(id,prevMedName, prevCompanyName, prevGenericType, pharmaGenericName, stringBuilder.toString(), stringBuilder1.toString(),stringBuilder2.toString());
                medicineviewList.add(drugsData);
            }else if (MedicineName.equals(prevMedName))
            {
                drugsData = new DrugsData(id,prevMedName, prevCompanyName, prevGenericType, pharmaGenericName, stringBuilder.toString(), stringBuilder1.toString(),stringBuilder2.toString());
                medicineviewList.add(drugsData);
            }

//            MedicineCustomAdapter customAdapter = new MedicineCustomAdapter(this, medicineviewList);
            CustomAdapter customAdapter = new CustomAdapter(this,medicineviewList);
            listViewDrugs.setAdapter(customAdapter);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.medicineGenericBack:
                Intent genericIntent = new Intent(PharmacologyActivity.this,Drugs.class);
                genericIntent.putExtra("genericName",pharmaGenericName);
                startActivity(genericIntent);
//                onBackPressed();
//                Toast.makeText(PharmacologyActivity.this,"clicked",Toast.LENGTH_SHORT);
        }
    }

    private void getPharmacologySub(){

        System.out.println("whats going on");
//        SQLiteDatabase sqLiteDatabase  = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase  = myDatabase.getReadableDatabase();
        System.out.println("whats going on 2");
        pharmacologyList = new ArrayList<>();
        System.out.println("whats going on 3");
        String query = "SELECT * from pharmacology_info order by rank";
        System.out.println("whats going on 4");
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        System.out.println("whats going on    5");
        if(cursor.getCount() > 0){
            System.out.println("whats going on 6");
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                System.out.println("whats going on 7");
                pharmacologyList.add(new PharmacologyDes(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name"))));
                System.out.println("phrmclogy id ;;;;;;;;;;;;;;;;;;;;;;;;;;;; : "+cursor.getInt(cursor.getColumnIndex("id")));
                System.out.println("phrmclogy id;;;;; : "+cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }



        for(int i = 0 ; i < pharmacologyList.size(); i++){
            TextView msg = new TextView(PharmacologyActivity.this);
//        msg.setBackgroundResource(R.drawable.rectangle);
            msg.setText(pharmacologyList.get(i).getName());
            System.out.println("messaage................................... : "+msg.getText().toString());
            msg.setPadding(10, 10, 10, 10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 15, 0, 0);
            params.gravity = Gravity.LEFT;
            msg.setLayoutParams(params);
            msg.setGravity(Gravity.LEFT);
            generateLayout2.addView(msg);
            final int finalI = i;
//            final int finalI1 = i;
            msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String description = null;
                    String descriptionWithoutHtml = "";
//                    SQLiteDatabase sqLiteDatabase1 = myDBHelper.getReadableDatabase();
                    SQLiteDatabase sqLiteDatabase1 = myDatabase.getReadableDatabase();
                    String query = "select description from medicine_pharmacology where medicine_subgroup_id = \""+rcvMedicineSubGroupId+"\" AND pharmacology_id = \""+pharmacologyList.get(finalI).getId()+"\"";
                    Cursor cursor1 = sqLiteDatabase1.rawQuery(query,null);


                    if (cursor1 != null && cursor1.moveToFirst()){
                        do {
                            description = cursor1.getString(cursor1.getColumnIndex("description"));
                            System.out.println("description..................................... : "+description);
                            if(description != null){
                                descriptionWithoutHtml = Html.fromHtml(description).toString();
                            }


                            System.out.println("descriptionwihtouthtml..................................... : "+descriptionWithoutHtml);
                        } while (cursor1.moveToNext());
                    }


                    cursor1.close();
                    sqLiteDatabase1.close();
                    setDialog(pharmacologyList.get(finalI).getName(),descriptionWithoutHtml);

                }
            });
        }

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(PharmacologyActivity.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }

