package com.example.emon.bdmedic.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.SingleTextAdapter.SingleTextAdapter;

import java.util.ArrayList;
import java.util.List;


public class SurgeryFragment extends Fragment {

    ListView listViewSurgery;
    private View v;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    String branchName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_surgery, container, false);
        final Intent intent = new Intent(getActivity(),DiseseasList.class);
        listViewSurgery =(ListView) v.findViewById(R.id.listViewSurgery);
//        myDBHelper = new MyDBHelper(getActivity());
        myDatabase = new MyDatabase(getActivity());
//        myDBHelper.getWritableDatabase();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(),R.layout.pharmacology_layout,getBranchName());
        final SingleTextAdapter singleTextAdapter = new SingleTextAdapter(getActivity(),getBranchName());
        listViewSurgery.setAdapter(singleTextAdapter);

        listViewSurgery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                branchName  = listViewSurgery.getItemAtPosition(position).toString();
                System.out.println("value on listview : "+branchName);
                intent.putExtra("branchName",branchName);
                intent.putExtra("header","Surgery");
                startActivity(intent);
            }
        });

        return v;
    }

    public List<String> getBranchName(){
        ArrayList<String> names = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String Query = "SELECT * FROM branch where discpline_id = 2";
        Cursor cursor = sqLiteDatabase.rawQuery(Query,null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                names.add(cursor.getString(cursor.getColumnIndex("branch_name")));
                System.out.println("Branch name : "+cursor.getString(cursor.getColumnIndex("branch_name")));
                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();

        }


        return names;

    }

}
