package com.example.emon.bdmedic.Search;


import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.Fragments.DiseaseData;
import com.example.emon.bdmedic.Fragments.DiseaseListAdapter;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.MenuFeed;
import com.example.emon.bdmedic.Pharmacologist.CustomAdapter;

import com.example.emon.bdmedic.Pharmacologist.DrugsData;
import com.example.emon.bdmedic.Pharmacologist.MedicineView;
import com.example.emon.bdmedic.PriceCalculator.PriceCalculator;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.WebView.SampleTable;

import java.util.Collections;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SearchActivity extends AppCompatActivity {


    MyDatabase myDatabase;
    SearchView searchView;
    int selectedPos;
    List<DiseaseData> diseaseDataArrayList;
    String[] cat = {"Medicine", "Disease"};
    List<DrugsData> medicineList;
    MaterialSpinner materialSpinner;
    ListView listViewSearchDisease;
    ListView listViewSearchMedic;
    CustomAdapter customAdapter;
    DiseaseListAdapter diseaseListAdapter;
    private final static String ARG_STRING = "ARG_STRING";
    private final static String ARG_INT = "ARG_INT";
    private String stringField;
    private int intField;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        Intent intent = getIntent();

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);


        // Get the activity intent if there is a one
        final Intent intent = getIntent();

        // And retrieve arguments if there are any
        if (intent.hasExtra(ARG_STRING)) {
            stringField = intent.getExtras().getString(ARG_STRING);
        }
        if (intent.hasExtra(ARG_INT)) {
            intField = intent.getExtras().getInt(ARG_INT);
        }
        // And we retrieve large data from enum
        if (MenuFeed.DataHolderMedicine.hasData()) {
            medicineList = MenuFeed.DataHolderMedicine.getData();
        }
        if (MenuFeed.DataHolderDisease.hasData()) {
            diseaseDataArrayList = MenuFeed.DataHolderDisease.getData();
        }

        listViewSearchMedic = findViewById(R.id.listviewSearchMedic);
        listViewSearchDisease = findViewById(R.id.listviewSearchDisease);
        materialSpinner = findViewById(R.id.selectSearchItem);
//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
        searchView = findViewById(R.id.searchView);
//        searchBar.setCardViewElevation(15);
        spinnerAdap(materialSpinner, cat);

        materialSpinner.setSelection(1);

        searchView.setIconified(false);
        Collections.sort(medicineList, new SortBasedOnName());
        customAdapter = new CustomAdapter(SearchActivity.this,medicineList);
        listViewSearchMedic.setAdapter(customAdapter);
        listViewSearchMedic.setVisibility(View.GONE);



        materialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPos = materialSpinner.getSelectedItemPosition();
                if (selectedPos != 0) {
                    System.out.println("selected pos : " + selectedPos);
                    if (selectedPos == 1) {
                        searchView.setIconified(false);

                        Collections.sort(medicineList, new SortBasedOnName());
                         customAdapter = new CustomAdapter(SearchActivity.this,medicineList);
                        listViewSearchMedic.setAdapter(customAdapter);
                        listViewSearchMedic.setVisibility(View.GONE);

                        listViewSearchDisease.setVisibility(View.GONE);
                    }  if (selectedPos == 2) {
                        searchView.setIconified(false);
                        diseaseListAdapter = new DiseaseListAdapter(SearchActivity.this,diseaseDataArrayList);
                        listViewSearchDisease.setAdapter(diseaseListAdapter);
                        listViewSearchDisease.setVisibility(View.GONE);
                        listViewSearchMedic.setVisibility(View.GONE);



                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                materialSpinner.setError("select category");
            }
        });

        listViewSearchMedic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



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
                    Intent intent = new Intent(SearchActivity.this, MedicineView.class);
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
                    Toast.makeText(SearchActivity.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
                }


            }
        });



        listViewSearchDisease.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DiseaseData diseaseData = (DiseaseData)parent.getItemAtPosition(position);
                final String diseaseName = diseaseData.getDiseaseName();
                if(diseaseName != null){
                    Intent intent = new Intent(SearchActivity.this, SampleTable.class);
                    intent.putExtra("diseaseName", diseaseName);
                    startActivity(intent);
                }else{
                    Toast.makeText(SearchActivity.this,getString(R.string.userMessage),Toast.LENGTH_SHORT).show();
                }

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                switch (selectedPos){

                    case 1:
                        customAdapter.getFilter().filter(s);
                        listViewSearchMedic.setAdapter(customAdapter);
                        listViewSearchMedic.setVisibility(View.VISIBLE);
                        listViewSearchDisease.setVisibility(View.GONE);
                        if(s.isEmpty()){
                            listViewSearchMedic.setVisibility(View.GONE);
                            listViewSearchDisease.setVisibility(View.GONE);
                        }
                        break;

                    case 2:
                        diseaseListAdapter.getFilter().filter(s);
                        listViewSearchDisease.setAdapter(diseaseListAdapter);
                        listViewSearchDisease.setVisibility(View.VISIBLE);
                        listViewSearchMedic.setVisibility(View.GONE);
                        if(s.isEmpty()){
                            listViewSearchDisease.setVisibility(View.GONE);
                            listViewSearchMedic.setVisibility(View.GONE);
                        }
                        break;

                        default:
                            customAdapter.getFilter().filter(s);
                            listViewSearchMedic.setAdapter(customAdapter);
                            listViewSearchMedic.setVisibility(View.VISIBLE);
                            listViewSearchDisease.setVisibility(View.GONE);
                            if(s.isEmpty()){
                                listViewSearchMedic.setVisibility(View.GONE);
                                listViewSearchDisease.setVisibility(View.GONE);
                            }
                            break;

                }

                return false;
            }
        });


    }

    public void spinnerAdap(MaterialSpinner mSpinner, String[] sArray) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(SearchActivity.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



