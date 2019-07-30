package com.example.emon.bdmedic.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.WebView.SampleTable;


import java.util.ArrayList;
import java.util.List;

public class DiseseasList extends AppCompatActivity {

    ListView listViewDisease;
    String rcvBranchName;
    TextView textView;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    String diseaseName;
    TextView branch_name;
    String rcvHeader;
    TextView header;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseseas_list);
        final Intent intent = new Intent(DiseseasList.this, SampleTable.class);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);
//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
        Intent BranchIntent = getIntent();
      rcvBranchName = BranchIntent.getStringExtra("branchName");
      rcvHeader = BranchIntent.getStringExtra("header");
        listViewDisease =  findViewById(R.id.listViewDisease);
        branch_name = findViewById(R.id.branch_name);
        header = findViewById(R.id.header);
        branch_name.setText(rcvBranchName);
        header.setText(rcvHeader);
        getDiseaseList(rcvBranchName);
        listView( getDiseaseList(rcvBranchName));
        listViewDisease.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DiseaseData item = (DiseaseData)parent.getItemAtPosition(position);
                 diseaseName = item.getDiseaseName();
//                diseaseName = "Disease 1";
//                diseaseName = listViewDisease.getItemAtPosition(position).toString();
//                System.out.println("value on listview : "+branchName);
                if(diseaseName != null){
                    intent.putExtra("diseaseName", diseaseName);
                    startActivity(intent);
                }else{
                    Toast.makeText(DiseseasList.this,getString(R.string.userMessage),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public List<DiseaseData> getDiseaseList(String rcvBranchName){

        ArrayList<DiseaseData> names = new ArrayList<DiseaseData>();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select * from disease_management left join branch on disease_management.branch_id = branch.id where branch.branch_name = \""+rcvBranchName+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                names.add(new DiseaseData(cursor.getString(cursor.getColumnIndex("disease_name")),cursor.getInt(cursor.getColumnIndex("_id"))));
                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();

        }


        return names;

    }
/*    public String[] getDiseaseList(String rcvBranchName){

        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String query = "select * from disease_management left join branch on disease_management.branch_id = branch.id where branch.branch_name = \""+rcvBranchName+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()){
//            names.add(new DiseaseData(cursor.getString(cursor.getColumnIndex("disease_name"))));
            names.add(cursor.getString(cursor.getColumnIndex("disease_name")));
            //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.moveToNext();

        }
        cursor.close();
        sqLiteDatabase.close();

        return names.toArray(new String[names.size()]);

    }*/
    public void listView(List<DiseaseData>diseaseList) {

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.pharmacology_layout,diseaseList);
        DiseaseListAdapter diseaseListAdapter = new DiseaseListAdapter(DiseseasList.this,diseaseList) ;
        listViewDisease.setAdapter(diseaseListAdapter);


    }
}
