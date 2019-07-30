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

import com.example.emon.bdmedic.BottomMenu;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;

import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.SingleTextAdapter.SingleTextAdapter;

import java.util.ArrayList;
import java.util.List;

public class GenericName extends AppCompatActivity {

    ListView listviewGenericName;
    String rcvCategoryName;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    String genericName;
    Intent intent;
    private Toolbar toolbar;
    SingleTextAdapter singleTextAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_name);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);

        listviewGenericName = findViewById(R.id.listviewGenericName);
        intent = new Intent(this, Drugs.class);
//         myDBHelper = new MyDBHelper(this);
//        myDBHelper.getWritableDatabase();
        myDatabase = new MyDatabase(this);
        Intent genericIntent = getIntent();
        rcvCategoryName = genericIntent.getStringExtra("categoryName");
        System.out.println("category name............ : "+rcvCategoryName);
        if(rcvCategoryName != null){
            getGenericList(rcvCategoryName);
            listView(getGenericList(rcvCategoryName));
        }else{
            Toast.makeText(GenericName.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
        }

        listviewGenericName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genericName = listviewGenericName.getItemAtPosition(position).toString();
                if (genericName != null){
                    intent.putExtra("genericName",genericName);
                    startActivity(intent);
                }else{
                    Toast.makeText(GenericName.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public List<String> getGenericList(String rcvCategoryName){

        ArrayList<String> names = new ArrayList<String>();
        System.out.println("System Name : "+rcvCategoryName);
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select generic_name from medicine_subgroup where medicine_subgroup.category = \""+rcvCategoryName+"\" group by generic_name";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                names.add(cursor.getString(cursor.getColumnIndex("generic_name")));
                System.out.println("generic namexzzzzzzzzzzzzzzzzzzzzzzzzzzz :"+cursor.getString(cursor.getColumnIndex("generic_name")));
                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }


        return names;

    }
    public void listView(List<String> ListsArray) {

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.pharmacology_layout,ListsArray);

        if(ListsArray != null){
            if(!ListsArray.isEmpty()){
                 singleTextAdapter = new SingleTextAdapter(GenericName.this,ListsArray);
                listviewGenericName.setAdapter(singleTextAdapter);
            }else{
                Toast.makeText(GenericName.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(GenericName.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(GenericName.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
