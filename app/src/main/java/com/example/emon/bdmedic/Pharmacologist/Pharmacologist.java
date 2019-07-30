package com.example.emon.bdmedic.Pharmacologist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emon.bdmedic.Api;
import com.example.emon.bdmedic.ApiInterface;
import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.SingleTextAdapter.SingleTextAdapter;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pharmacologist extends AppCompatActivity {

    ListView ListviewCommonTreatment;
    List<MedicineSubGroupData> medicineSubGroupDataList;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    String systemName,systemId;
    Intent intent;
    SingleTextAdapter singleTextAdapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacologist);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);


        ListviewCommonTreatment = findViewById(R.id.listviewCommonTreatment);
//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
//        myDBHelper.getWritableDatabase();
         intent = new Intent(Pharmacologist.this,Category.class);
//        getSystems();
        getSystemList();
        listView(getSystemList());


        ListviewCommonTreatment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              categoryName =   String.valueOf(ListviewCommonTreatment.getSelectedItem());


                systemName  = ListviewCommonTreatment.getItemAtPosition(position).toString();
//                SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
                SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
                String query = "select id from system where system.name = \""+systemName+"\"";
                Cursor cursor = sqLiteDatabase.rawQuery(query,null);

                if(cursor.getCount() > 0){

                    cursor.moveToFirst();
//                ArrayList<String> names = new ArrayList<String>();
                    while(!cursor.isAfterLast()){
                        systemId =  cursor.getString(cursor.getColumnIndex("id"));
                        //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                        cursor.moveToNext();

                    }
                    cursor.close();
                    sqLiteDatabase.close();
                    System.out.println("value on listview : "+systemName);
                    if(systemId != null){
                        intent.putExtra("systemId",systemId);
                        startActivity(intent);
                    }else{

                        Toast.makeText(Pharmacologist.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
                    }


                }

               /* getCategoryList(categoryName);
              listView(getCategoryList(categoryName));*/

            }
        });


    }



    public List<String> getSystemList(){
        ArrayList<String> names = new ArrayList<String>();
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select name from system";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                names.add(cursor.getString(cursor.getColumnIndex("name")));
                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }


//        return names.toArray(new String[names.size()]);

        return names;
    }


    public void listView(List<String>ListsArray) {

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.pharmacology_layout,ListsArray);
        if(ListsArray != null){
            if(!ListsArray.isEmpty()){

                 singleTextAdapter = new SingleTextAdapter(Pharmacologist.this,ListsArray);
                ListviewCommonTreatment.setAdapter(singleTextAdapter);
            }else{
                Toast.makeText(Pharmacologist.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(Pharmacologist.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
        }





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
                singleTextAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Pharmacologist.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
