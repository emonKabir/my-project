package com.example.emon.bdmedic.Pharmacologist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
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

public class MedicineView extends AppCompatActivity implements View.OnClickListener{

//    ListView listViewDrugs;
    NonScrollListview listViewDrugs;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    AlertDialog alertDialog;
    int id;
    LinearLayout medicineLinear;
    AlertDialog.Builder alertDialogBuilder;
//    String rcvGenericName;
    public List<DrugsData> medicineviewList;
    private List<DrugsData> lastItem;
    DrugsData drugsData;
    String rcvPharmacologyPermission = "test";
    TextView genericName;
    String pharmaGenericName;
    String rcvMedicineName,rcvGenericType,rcvGenericName,rcvUnit,rcvUnitPrice,rcvCompany,rcvPackagePrice,rcvMedicineSubGroupId;
    LinearLayout medicineViewLayout;
    DrugsData lastItemDrug;
    LinearLayout generateLayout;
    List<PharmacologyDes>pharmacologyList;
    int rcvId;
    Toolbar toolbar;
    TextView genName,pharma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_view);


        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
        listViewDrugs = findViewById(R.id.listviewDrugsViews);
        medicineLinear = findViewById(R.id.medicineLinear);
//        genericName = findViewById(R.id.medicineGenericBack);
//        medicineViewLayout = findViewById(R.id.medicineViewLayout);
        alertDialogBuilder = new AlertDialog.Builder(this);
        generateLayout = findViewById(R.id.generateLayout);
        lastItem = new ArrayList<>();
        genName = findViewById(R.id.medicineGenericBack);
        pharma = findViewById(R.id.pharmacology);
        System.out.println("first intent.......................");
        genName.setOnClickListener(this);
        pharma.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            rcvMedicineSubGroupId = extras.getString("medicineSubGroupId");
            System.out.println("rcv medicicne group id...................... : "+rcvMedicineSubGroupId);
             rcvMedicineName = extras.getString("medicineName");
             rcvGenericType = extras.getString("genericType");
             rcvGenericName = extras.getString("genericName");
             rcvUnit = extras.getString("unit");
             rcvUnitPrice = extras.getString("unitPrice");
             rcvCompany = extras.getString("company");
             rcvPackagePrice = extras.getString("packagePrice");
             rcvPackagePrice = extras.getString("packagePrice");
             rcvId = extras.getInt("id");

            lastItemDrug = new DrugsData(rcvId,rcvMedicineName,rcvCompany ,rcvGenericType ,rcvGenericName , rcvUnit,rcvUnitPrice,rcvPackagePrice);
            getSponsorMedicine();
            getPharmacologySub();


        }else{
            Toast.makeText(MedicineView.this,"data is updating.please wait!!",Toast.LENGTH_LONG).show();
        }


        System.out.println("start from here.............................");


    }




    private void getSponsorMedicine(){

        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "";

        System.out.println("impossible to in...................");
        query = "select medicines.id,medicines.name,medicines.company_name,medicines_dosage.generic_type,medicines_dosage.unit,medicines_dosage.package_price,medicines_dosage.unit_price,medicines_dosage.package_method from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id left join sponsor on medicines.company_id = sponsor.supplier_id where (medicines.sub_generic_name = \"" + rcvGenericName + "\" OR medicines.name =\"" + rcvMedicineName + "\") AND medicines_dosage.generic_type = \"" + rcvGenericType + "\" order by medicines_dosage.generic_type, ifnull(sponsor.rank,9999), medicines.name limit 3";
        //Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount() > 0){

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
            if (cursor != null && cursor.moveToFirst()){

                do {
                    id  = cursor.getInt(cursor.getColumnIndex("id"));
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
                        drugsData = new DrugsData(id,prevMedName,prevCompanyName ,prevGenericType ,rcvGenericName , stringBuilder.toString(),stringBuilder1.toString(),stringBuilder2.toString());
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


                } while (cursor.moveToNext());
            }else{
                Toast.makeText(MedicineView.this,"please wait data updating",Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            sqLiteDatabase.close();
            if(prevMedName.equals("")){

                Toast.makeText(this,"No medicine found under this generic name",Toast.LENGTH_SHORT).show();
            }
            else if(!MedicineName.equals(prevMedName)) {
                drugsData = new DrugsData(id,prevMedName, prevCompanyName, prevGenericType, rcvGenericName, stringBuilder.toString(), stringBuilder1.toString(),stringBuilder2.toString());
                medicineviewList.add(drugsData);
            }else if (MedicineName.equals(prevMedName))
            {
                drugsData = new DrugsData(id,prevMedName, prevCompanyName, prevGenericType, rcvGenericName, stringBuilder.toString(), stringBuilder1.toString(),stringBuilder2.toString());
                medicineviewList.add(drugsData);
            }

            medicineviewList.add(lastItemDrug);
//            MedicineCustomAdapter customAdapter = new MedicineCustomAdapter(this, medicineviewList);
            CustomAdapter customAdapter = new CustomAdapter(this,medicineviewList);
            listViewDrugs.setAdapter(customAdapter);
        }
    }
    private void getPharmacologySub(){

        System.out.println("whats going on");
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
            TextView msg = new TextView(MedicineView.this);
//        msg.setBackgroundResource(R.drawable.rectangle);
            msg.setText(pharmacologyList.get(i).getName());
            System.out.println("messaage................................... : "+msg.getText().toString());
            msg.setPadding(10, 10, 10, 10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 15, 0, 0);
            params.gravity = Gravity.LEFT;
            msg.setLayoutParams(params);
            msg.setGravity(Gravity.LEFT);
            generateLayout.addView(msg);
            final int finalI = i;
//            final int finalI1 = i;
            msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String description = null;
                    String descriptionWithoutHtml = "";
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
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.medicineGenericBack:
//                onBackPressed();
                Intent genericIntent = new Intent(MedicineView.this,Drugs.class);
                genericIntent.putExtra("genericName",rcvGenericName);
                startActivity(genericIntent);
                break;

            case R.id.pharmacology:
                if (rcvGenericName != null && rcvMedicineSubGroupId != null){
                    Intent intent = new Intent(MedicineView.this,PharmacologyActivity.class);
                    intent.putExtra("from","pharmacology");
                    intent.putExtra("genericName",rcvGenericName);
                    intent.putExtra("medicineSubGroupId",rcvMedicineSubGroupId);
                    startActivity(intent);
                    finish();
                }else{

//                    Toast.makeText(Drugs.this,"data updating.please wait!!!",Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(medicineLinear,"No Data Found.",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(MedicineView.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
