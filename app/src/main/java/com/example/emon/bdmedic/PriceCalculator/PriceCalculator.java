package com.example.emon.bdmedic.PriceCalculator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class PriceCalculator extends AppCompatActivity {

    ListView listView;
    List<DrugList>drugLists;
    Medicine medicine;
   public TextView totalPrice,grandTotal;
    Activity activity;
    MaterialSpinner doseSpinner,priceSpinner;
    String dose,price;
    TextView addDrug;
    EditText quantity;
    LinearLayout linearLayout;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    String[] doses = {" "," "};
    String[] prices = {" "};
    AutoCompleteTextView autoComplete;
    ArrayList<String> dosageUnit;
    ArrayList<String> package_price;
    ArrayList<String> unit_price;
    String[] qqq;
    String medicineName = "";
    ArrayAdapter<String> adapter;
    int allTotal = 0;
    String genType;
    EditText discountBdt,dicountPercentage;
    DrugListAdapter drugListAdapter;
    int rowTotal = 0;

    private android.support.v7.widget.Toolbar toolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_calculator);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
//        mAuth = FirebaseAuth.getInstance();
        linearLayout = findViewById(R.id.linear);
        listView = findViewById(R.id.listviewPriceCalculator);
        doseSpinner = findViewById(R.id.doseSpinner);
        priceSpinner = findViewById(R.id.priceSpinner);
        addDrug = findViewById(R.id.addDrug);
        quantity = findViewById(R.id.quantity);
        drugLists = new ArrayList<DrugList>();
        autoComplete = findViewById(R.id.autoComplete);
        dosageUnit = new ArrayList<String>();
        package_price = new ArrayList<String>();
        dicountPercentage = findViewById(R.id.discountPercentage);
        discountBdt = findViewById(R.id.discountBdt);
        unit_price = new ArrayList<String>();
        totalPrice = findViewById(R.id.totalPrice);
        grandTotal = findViewById(R.id.grandTotal);
         qqq = new String[2];
         spinnerAdap(doseSpinner,doses);
         spinnerAdap(priceSpinner,prices);

       medicine = new Medicine();
//       myDBHelper = new MyDBHelper(this);
       myDatabase = new MyDatabase(this);
//       SQLiteDatabase  sqLiteDatabase = myDBHelper.getReadableDatabase();

 //        getMName();
//        getMedicnes();
//        getDosages();


        //inseting medicineName into autoCompleteTextview
        if(getMedicineName() != null){
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, getMedicineName());
            autoComplete.setThreshold(2);
            autoComplete.setAdapter(arrayAdapter);
        }else{
            Toast.makeText(PriceCalculator.this,"updating",Toast.LENGTH_SHORT).show();
            finish();
        }

       addDrug.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               if((doseSpinner != null && doseSpinner.getSelectedItem() != null) && (doseSpinner != null && priceSpinner.getSelectedItem() != null) && quantity.getText().toString().trim().length() != 0) {
//                   name = (String)spinnerName.getSelectedItem();

                   String quantities = quantity.getText().toString()+" "+"*";
                   int qnt = Integer.parseInt(quantity.getText().toString());
                   if(priceSpinner.getSelectedItemPosition() == 1)
                   {
                       price.trim();
                       price =  price.substring(0,price.indexOf(' '));
                   }
                   double prc = Double.parseDouble(price);
                   double totalP = qnt * prc;
                   int totalI = (int) Math.ceil(totalP);
                   allTotal = totalI + allTotal;
                   String allTtl = String.valueOf(allTotal);
                   String totalS = String.valueOf(totalI);
                   totalPrice.setText(allTtl);
                   grandTotal.setText(allTtl);
                   DrugList drugList = new DrugList(medicineName,dose,quantities,totalS,price,"BDT","=",allTotal,genType);
                   drugLists.add(drugList);
                   Collections.reverse(drugLists);
                   drugListAdapter = new DrugListAdapter(PriceCalculator.this,drugLists);
                   drugListAdapter.notifyDataSetChanged();

                   listView.setAdapter(drugListAdapter);

                   quantity.setText("");
                   autoComplete.setText("");

                   System.out.println("printing dosage");
                   dosageUnit.clear();
//              doseSpinner.setSelection(0);
                   System.out.println("printing dosage 2");

                   spinnerAdapter(doseSpinner,dosageUnit);
                   spinnerAdapter(priceSpinner,dosageUnit);


               } else  {

                   Snackbar snackbar = Snackbar.make(linearLayout,"add field",Snackbar.LENGTH_LONG);
                   snackbar.show();
               }


              /* doseSpinner.setAdapter(new ArrayAdapter<String>(PriceCalculator.this,android.R.layout.simple_dropdown_item_1line,dosageUnit));
               System.out.println("printing dosage 3");
//               Arrays.fill(qqq,null);
               System.out.println("printing dosage 4 ");
               priceSpinner.setAdapter(new ArrayAdapter<String>(PriceCalculator.this,android.R.layout.simple_dropdown_item_1line,dosageUnit));*/

           }
       });
        unit_price.toArray(new String[unit_price.size()]);

      /* if(rowTotal > 0 ){
           allTotal = allTotal - rowTotal;
           totalPrice.setText(allTotal);
           rowTotal = 0;
       }*/

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             final DrugList drugList = (DrugList) parent.getItemAtPosition(position);
             medicineName = drugList.getDrugName();
             genType = drugList.getGenType();
              int singleTotal = Integer.valueOf(drugList.getTotal());
             autoComplete.setText(medicineName+" "+genType);
             dosageUnit.clear();
              doseSpinner.setSelection(0);
              priceSpinner.setSelection(0);
             getAutoComplete(medicineName,genType);
           drugLists.remove(drugList);
           allTotal -= singleTotal;
           totalPrice.setText(String.valueOf(allTotal));
           grandTotal.setText(String.valueOf(allTotal));
           drugListAdapter.notifyDataSetChanged();


          }
      });

      quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
              if(!hasFocus){
                  hideKeyboard(v);
              }
          }
      });

autoComplete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            hideKeyboard(v);
        }
    }
});


discountBdt.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if(s.length()!=0){
            final int tPrice = allTotal;
            String discountB =  discountBdt.getText().toString().trim();
            int discouuntBInt= Integer.valueOf(discountB);
            discouuntBInt = tPrice - discouuntBInt;
            totalPrice.setText(String.valueOf(discouuntBInt));

        }else{

            totalPrice.setText(String.valueOf(allTotal));
        }


    }

    @Override
    public void afterTextChanged(Editable s) {



    }
});

        dicountPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0){
                    final int tPrice = allTotal;
                    String discountB =  dicountPercentage.getText().toString().trim();
                    int discouuntBInt= Integer.valueOf(discountB);
                    discouuntBInt = tPrice - ((tPrice * discouuntBInt) / 100);

                    totalPrice.setText(String.valueOf(discouuntBInt));

                }else{

                    totalPrice.setText(String.valueOf(allTotal));
                }


            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });
    autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            dosageUnit.clear();
            doseSpinner.setEnabled(true);
            medicineName = String.valueOf(parent.getItemAtPosition(position));
            String[] nnn = medicineName.split(" ");
             genType = nnn[nnn.length-1];
            medicineName = medicineName.replace(" "+genType,"");
            getAutoComplete(medicineName,genType);

        }
    });

        spinnerAdapter(doseSpinner,dosageUnit);

        doseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(doseSpinner.getSelectedItemPosition() != 0)
                {

                    String doseName = (String) doseSpinner.getSelectedItem();
                    SQLiteDatabase  sqLiteDatabase = myDatabase.getReadableDatabase();
                    String query = "select * from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id where medicines.name = \""+medicineName+"\" and medicines_dosage.generic_type = \""+genType+"\" and medicines_dosage.unit = \""+doseName+"\"";
                    Cursor cursor = sqLiteDatabase.rawQuery(query,null);
                    cursor.moveToFirst();

                    priceSpinner.setEnabled(true);
                    while(!cursor.isAfterLast()){
                    qqq[0] = cursor.getString(cursor.getColumnIndex("unit_price"));
                    qqq[1] = cursor.getString(cursor.getColumnIndex("package_price"));

                        cursor.moveToNext();
                    }
                    cursor.close();
                    sqLiteDatabase.close();
                    spinnerAdap(priceSpinner,qqq);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(priceSpinner.getSelectedItemPosition() != 0){
                    price = String.valueOf(priceSpinner.getSelectedItem());

                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
     public void  spinnerAdap(MaterialSpinner mSpinner, String[] sArray){


    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

    }
     public void  spinnerAdapter(MaterialSpinner mSpinner, List<String> list){

        //mSpinner.setAdapter(null);
         list.toArray(new String[list.size()]);
         adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
//        doseSpinner.setSelection(1);

    }
     public String[] getMedicineName(){
     SQLiteDatabase  sqLiteDatabase = myDatabase.getReadableDatabase();
     String selectQuery = "SELECT medicines.name,medicines_dosage.generic_type  FROM medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id group by medicines.name,medicines_dosage.generic_type";
     Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
     cursor.moveToFirst();
     ArrayList<String>names = new ArrayList<String>();
     if(cursor != null && cursor.moveToFirst()){
         do{
             if(cursor.getString(cursor.getColumnIndex("name")) != null && cursor.getString(cursor.getColumnIndex("generic_type")) != null){
                 String sss = cursor.getString(cursor.getColumnIndex("name")) + " " + cursor.getString(cursor.getColumnIndex("generic_type"));
                 names.add(sss);
             }

         }while(cursor.moveToNext());
     }

     cursor.close();
     sqLiteDatabase.close();

     return names.toArray(new String[names.size()]);

 }
    public void hideKeyboard(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    private void getAutoComplete(String medicineName, String genType){

        SQLiteDatabase  sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select * from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id where medicines.name = \""+medicineName+"\" and medicines_dosage.generic_type = \""+genType+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            dosageUnit.add(cursor.getString(cursor.getColumnIndex("unit")));
            unit_price.add(cursor.getString(cursor.getColumnIndex("unit_price")));
            package_price.add(cursor.getString(cursor.getColumnIndex("package_price")));
            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(PriceCalculator.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
