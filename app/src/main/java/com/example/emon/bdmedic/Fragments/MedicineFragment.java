package com.example.emon.bdmedic.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.Pharmacologist.Category;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.SingleTextAdapter.SingleTextAdapter;

import java.util.ArrayList;
import java.util.List;


public class MedicineFragment extends Fragment {

    ListView listViewMedicine;
    private View v;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    Context context;
    int branchId;
    String branchName;
Toolbar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_medicine, container, false);


        listViewMedicine =(ListView) v.findViewById(R.id.listViewMedicine);
        final Intent intent = new Intent(getActivity(),DiseseasList.class);
//        myDBHelper = new MyDBHelper(getActivity());
        myDatabase = new MyDatabase(getActivity());
//        myDBHelper.getWritableDatabase();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(),R.layout.pharmacology_layout,getBranchName());

        final SingleTextAdapter singleTextAdapter = new SingleTextAdapter(getActivity(),getBranchName());
        listViewMedicine.setAdapter(singleTextAdapter);
//        listViewMedicine.setAdapter(adapter);
        listViewMedicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                branchName  = listViewMedicine.getItemAtPosition(position).toString();
                System.out.println("value on listview : "+branchName);
                if(branchName != null){
                    intent.putExtra("branchName",branchName);
                    intent.putExtra("header","Medicine");
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"data is updating.please wait!!!",Toast.LENGTH_SHORT).show();
                }
               /*
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", 0.0f, 180f);
                animator.setDuration(1200);
                view.setHasTransientState(true);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        singleTextAdapter.notifyDataSetChanged();
                        view.setHasTransientState(false);
                    }
                });

                animator.start();*/
            }
        });

        return v;
    }


    public List<String> getBranchName(){
//        SQLiteDatabase  sqLiteDatabase = myDBHelper.getReadableDatabase();
        SQLiteDatabase  sqLiteDatabase = myDatabase.getReadableDatabase();
//        String selectQuery = "SELECT disease_name FROM disease_management where category = \"A\"";
        String Query = "SELECT * FROM branch where discpline_id = 1 ";
        ArrayList<String> names = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery(Query,null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                names.add(cursor.getString(cursor.getColumnIndex("branch_name")));
                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();

        }


        return names;

    }
}
