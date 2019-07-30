package com.example.emon.bdmedic.Pharmacologist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Drugs extends AppCompatActivity  implements  View.OnClickListener{

    ListView listViewDrugs;
//  public  MyDBHelper myDBHelper;
  public MyDatabase myDatabase;
   public String rcvGenericName;
    ArrayList<DrugsData> names;
HashMap<Integer,Boolean>checkHash = new HashMap<Integer, Boolean>();
    DrugsData drugsData;
    TextView pharmacology;
    Intent intent;
    int id;
    String medicineSubGroupId;
     CustomAdapter customAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
    listViewDrugs = findViewById(R.id.listviewDrugs);
    pharmacology = findViewById(R.id.pharmacology);
    pharmacology.setOnClickListener(this);
//    myDBHelper = new MyDBHelper(this);
    myDatabase = new MyDatabase(this);
//    myDBHelper.getWritableDatabase();
        System.out.println("111111111111111111111111111111");
    Intent drugsIntent = getIntent();
        System.out.println("2222222222222222222222222");
    rcvGenericName = drugsIntent.getStringExtra("genericName");
        System.out.println("3333333333333333333333333333333");
    if(rcvGenericName !=  null){
        System.out.println("44444444444444444444444444444444");
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
//        System.out.println("create database");
        String query = "select medicines.id,medicines.medicine_subgroup_id,medicines.name,medicines.company_name,medicines_dosage._id,medicines_dosage.generic_type,medicines_dosage.unit,medicines_dosage.package_price,medicines_dosage.unit_price,medicines_dosage.package_method,sponsor.rank from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id left join sponsor on medicines.company_id = sponsor.supplier_id where medicines.sub_generic_name =\""+rcvGenericName+"\" order by medicines_dosage.generic_type, ifnull(sponsor.rank,9999),medicines.name";
//        System.out.println("query loded");
        System.out.println("555555555555555555555");
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
//        System.out.println("curosor loded");
        System.out.println("11111166666666666666666666666666");
        if(cursor.getCount() > 0){
            System.out.println("888888888888888888888888");
            names = new ArrayList<DrugsData>();

            int i = 0;
            String prevMedName = "";
            String prevCompanyName = "";
            String prevGenericType = "";

            String ddd = "";
            List<String>unitx =   new ArrayList<String>();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            String MedicineName = "";
            String genericType = "";
            System.out.println("9999999999999999999999");
            if(cursor != null && cursor.moveToFirst()){
                System.out.println("100000000000000000000000");
                do{
                    id = cursor.getInt(cursor.getColumnIndex("_id"));
                    MedicineName = cursor.getString(cursor.getColumnIndex("name"));
                    String companyName = cursor.getString(cursor.getColumnIndex("company_name"));
                    genericType = cursor.getString(cursor.getColumnIndex("generic_type"));
                    String units = cursor.getString(cursor.getColumnIndex("unit"));
                    String unitPrice = cursor.getString(cursor.getColumnIndex("unit_price"));
                    String packagePrice = cursor.getString(cursor.getColumnIndex("package_price"));
                    medicineSubGroupId = cursor.getString(cursor.getColumnIndex("medicine_subgroup_id"));
                    System.out.println("medicine sub group id :"+medicineSubGroupId);

            /*System.out.println("Medicine Name : "+MedicineName);
            System.out.println("Unit : "+cursor.getString(cursor.getColumnIndex("unit")));
*/
            if( MedicineName != null && companyName != null
                    && genericType != null && units != null
                    && unitPrice != null && packagePrice != null && medicineSubGroupId != null){
                if(i == 0){

                    prevMedName = MedicineName;
                    System.out.println("previousgenereic name .....................................: "+prevMedName +" and " +MedicineName);;
                    prevCompanyName = companyName;
                    System.out.println("previous company name............................... :"+prevCompanyName+" and "+companyName);
                    prevGenericType = genericType;
                    System.out.println("previous generic type name............................... :"+prevGenericType+" and "+genericType);
                    i = 1;
                }

                if(!MedicineName.equals(prevMedName) || !genericType.equals(prevGenericType)) {
                /*for(int j = 0; j < unitx.size(); j++) {
                    System.out.println("uni array : " + unitx.get(j));
                }*/
                    System.out.println("added drug  array list............................... :"+prevMedName+" and "+prevCompanyName+" and "+prevGenericType);
                    drugsData = new DrugsData(id,prevMedName,prevCompanyName ,prevGenericType ,rcvGenericName , stringBuilder.toString(),stringBuilder1.toString(),stringBuilder2.toString(),medicineSubGroupId);
                    names.add(drugsData);

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
              /*  unitx.clear();
                unitx.add(units);*/

                } else {
//                unitx.add(units);
                    stringBuilder.append(units);
                    stringBuilder.append("\n");
                    stringBuilder1.append(unitPrice);
                    stringBuilder1.append("\n");
                    String total = units+" * "+unitPrice+" = "+packagePrice+" Taka";
                    stringBuilder2.append(total);
                    stringBuilder2.append("\n");
//                ddd = stringBuilder.toString();
//                System.out.println("string builder : "+ddd);
                }
            }else{
                Toast.makeText(Drugs.this,"updating",Toast.LENGTH_SHORT).show();
            }

                }while (cursor.moveToNext());

            }else {
                Toast.makeText(Drugs.this,"updating",Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            sqLiteDatabase.close();
            if(prevMedName.equals("")){

                Toast.makeText(this,"No medicine found under this generic name",Toast.LENGTH_LONG).show();
            }
            else if(!MedicineName.equals(prevMedName) || !genericType.equals(prevGenericType)) {
                drugsData = new DrugsData(id,prevMedName, prevCompanyName, prevGenericType, rcvGenericName, stringBuilder.toString(), stringBuilder1.toString(),stringBuilder2.toString(),medicineSubGroupId);
                names.add(drugsData);
            }else if (MedicineName.equals(prevMedName) || genericType.equals(prevGenericType))
            {
                drugsData = new DrugsData(id,prevMedName, prevCompanyName, prevGenericType, rcvGenericName, stringBuilder.toString(), stringBuilder1.toString(),stringBuilder2.toString(),medicineSubGroupId);
                names.add(drugsData);
            }
           /* for(int j = 0; j <names.size(); j++ ){
                System.out.println("names total medName :"+names.get(j).getName());
            }*/
             customAdapter = new CustomAdapter(Drugs.this, names);
            listViewDrugs.setAdapter(customAdapter);

        }

    }else{
        Toast.makeText(Drugs.this,"data is updating.please wait",Toast.LENGTH_LONG).show();
    }
//    rcvGenericName = "Aspirin";
//    getDrugsList(rcvGenericName);
//        System.out.println("Generic Name : "+rcvGenericName);



        listViewDrugs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = listViewDrugs.getItemAtPosition(position).toString();
                /*DrugsData item  = (DrugsData) parent.getItemAtPosition(position);
                String tradeName = ((TextView)view.findViewById(R.id.TradeName)).getText().toString();
//                System.out.println("selected Item : "+item);
                System.out.println("Trade name  : "+tradeName);
                intent.putExtra("tradeName",tradeName);
                startActivity(intent);*/
             /*   customAdapter.selectedItem(position);
                customAdapter.notifyDataSetChanged();

    */
//            listViewDrugs.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//
              final DrugsData item  = (DrugsData) parent.getItemAtPosition(position);

              final  String currentGenericName = item.getGenerciName();
              final  int currentId = item.getId();
              final  String currentDrugName = item.getName();
              final String currentGenType = item.getGenericType();
              final String currentUnit = item.getSss();
              final String currentUnitPrice = item.getUnitPrice();
              final String currentCompany = item.getCompanyName();
              final String currentPackagePrice = item.getPackagePrice();
              final String currentMedicineSubGroupId = item.getMedicineSubGroupId();
                System.out.println("listview msgi.............................. : "+currentMedicineSubGroupId);

              if (currentMedicineSubGroupId != null && currentGenericName != null && currentDrugName != null && currentGenType != null && currentUnit != null && currentUnitPrice != null && currentCompany != null && currentPackagePrice != null){
                  Intent intent = new Intent(Drugs.this,MedicineView.class);
                  Bundle extras = new Bundle();
                  extras.putString("medicineName",currentDrugName);
                  extras.putString("genericType",currentGenType);
                  extras.putString("genericName",currentGenericName);
                  extras.putString("unit",currentUnit);
                  extras.putString("unitPrice",currentUnitPrice);
                  extras.putString("company",currentCompany);
                  extras.putString("packagePrice",currentPackagePrice);
                  extras.putString("medicineSubGroupId",currentMedicineSubGroupId);
                  extras.putInt("id",currentId);
                  intent.putExtras(extras);
                  startActivity(intent);

              }else{
                  Toast.makeText(Drugs.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
              }


            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    public static int getLineCount(String text){

        return text.split("[\n|\r]").length;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.pharmacology:

                if (rcvGenericName != null && medicineSubGroupId != null){
                    Intent intent = new Intent(Drugs.this,PharmacologyActivity.class);
                    intent.putExtra("from","pharmacology");
                    intent.putExtra("genericName",rcvGenericName);
                    intent.putExtra("medicineSubGroupId",medicineSubGroupId);
                    startActivity(intent);
                }else{

                    Toast.makeText(Drugs.this,"data updating.please wait!!!",Toast.LENGTH_SHORT).show();
                }



        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Drugs.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
