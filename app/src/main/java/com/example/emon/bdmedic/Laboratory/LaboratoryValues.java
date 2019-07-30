package com.example.emon.bdmedic.Laboratory;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryValues extends AppCompatActivity {

    public List<InvestigationData> productList = null;
    List<InvestigationData>arrayList = null;
    int size;
    String st;
    ListView lview;
    ListviewAdapter adapter;
    private Toolbar toolbar;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory_values);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);
        //list = new ArrayList<InvestigationData>();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
        productList = new ArrayList<InvestigationData>();
        lview = (ListView) findViewById(R.id.listview);
//        System.out.println("dfffffffffffffffffffffffffffffffffffffffffff33333333333333333333333333");
//InvestigationData data = new InvestigationData("djfdk","djd","lllll","djksjfj","djslfd");
//productList.add(data);


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.laboratory_listview_header,lview,false);
        lview.addHeaderView(header,null,false);
        adapter = new ListviewAdapter(LaboratoryValues.this,getInvestigation());
        lview.setAdapter(adapter);
//        System.out.println("dffffffffffffffffffffffffffffffffffffffffff55555555555f66666666666");
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final InvestigationData item  = (InvestigationData) parent.getItemAtPosition(position);
                /*final  String lIncrease = item.getIncreased();

                final  String lDecrease = item.getDecreased();
                final String lOther = item.getOthers();*/


                final Dialog dialog = new Dialog(LaboratoryValues.this);
                Toast.makeText(LaboratoryValues.this,"click",Toast.LENGTH_SHORT).show();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.laboratory_alertdialog_layout);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                TextView increase = dialog.findViewById(R.id.increase);
                TextView decrease = dialog.findViewById(R.id.decrease);
                TextView other = dialog.findViewById(R.id.other);
                ImageView dismiss  = dialog.findViewById(R.id.dismiss);
                increase.setText(Html.fromHtml(item.getIncreased()).toString());
                decrease.setText(Html.fromHtml(item.getDecreased()).toString());
                other.setText(Html.fromHtml(item.getOthers()).toString());

                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }




    @Override
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
    }

    public List<InvestigationData> getInvestigation(){
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
//        String selectQuery = "SELECT disease_name FROM disease_management where category = \"A\"";
        String Query = "SELECT * FROM investigation";
        ArrayList<InvestigationData> names = new ArrayList<InvestigationData>();
        Cursor cursor = sqLiteDatabase.rawQuery(Query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                InvestigationData investigationData = new InvestigationData(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("normal_finding")),cursor.getString(cursor.getColumnIndex("increased")),cursor.getString(cursor.getColumnIndex("decreased")),cursor.getString(cursor.getColumnIndex("others")));
                names.add(investigationData);
                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));

                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }


        return names;

    }

}
