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

public class Category extends AppCompatActivity {

    ListView ListviewCategory;
    List<MedicineSubGroupData> medicineSubGroupDataList;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    String rcvSystemId;
    String categoryName;
    SingleTextAdapter singleTextAdapter;
    Intent intent;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        intent = new Intent(this,GenericName.class);
        ListviewCategory = findViewById(R.id.listviewCategory);
//        myDBHelper = new MyDBHelper(this);
        myDatabase = new MyDatabase(this);
//        myDBHelper.getWritableDatabase();
        Intent secondIntent = getIntent();
        rcvSystemId = secondIntent.getStringExtra("systemId");
        if(rcvSystemId != null){

            getCategoryList(rcvSystemId);
            listView(getCategoryList(rcvSystemId));

        }else{
            Toast.makeText(Category.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
        }


        ListviewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryName = ListviewCategory.getItemAtPosition(position).toString();
                System.out.println("value on listview : "+ categoryName);
                if (categoryName != null){
                    intent.putExtra("categoryName", categoryName);
                    startActivity(intent);
                }else{
                    Toast.makeText(Category.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public List<String> getCategoryList(String rcvSystemId){

        ArrayList<String> names = new ArrayList<String>();
        System.out.println("System Name : "+rcvSystemId);
//        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select system_category.id,category.id,category.name from system_category left join category on system_category.category_id = category.id where system_category.system_id = \""+rcvSystemId+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){

//            cursor.moveToFirst();

            if(cursor != null && cursor.moveToFirst()){
                do{
                    names.add(cursor.getString(cursor.getColumnIndex("name")));
                }while (cursor.moveToNext());
            }

            cursor.close();
            sqLiteDatabase.close();
        }


        return names;

    }
    public void listView(List<String>ListsArray) {

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.pharmacology_layout,ListsArray);
//        ListviewCategory.setAdapter(adapter);
        if(ListsArray != null){
            if(!ListsArray.isEmpty()){
                 singleTextAdapter = new SingleTextAdapter(Category.this,ListsArray);
                ListviewCategory.setAdapter(singleTextAdapter);
            }else {
                    Toast.makeText(Category.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();

            }

        }else{
            Toast.makeText(Category.this,"data is updating.please wait!!!",Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(Category.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
