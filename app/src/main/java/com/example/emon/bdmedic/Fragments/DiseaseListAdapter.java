package com.example.emon.bdmedic.Fragments;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.Pharmacologist.DrugsData;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiseaseListAdapter extends BaseAdapter implements Filterable {

    public List<DiseaseData> list;
    private List<DiseaseData>diseaseData;
    Activity activity;
    Integer index;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    private static final String disease = "disease";
    private static HashMap<Long,String> map = new HashMap<Long,String>();

    private static  HashMap<Long,String>favMap = new HashMap<Long, String>();
    //        PriceCalculator priceCalculator = new PriceCalculator();
    //Context mContext;


    public DiseaseListAdapter(Activity activity, List<DiseaseData> list) {
//            super();
        this.activity = activity;
        this.list = list;
        diseaseData = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder {
        ImageView favouriteButton;
        TextView diseaseName;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.disease_list_layout, null);
            holder = new ViewHolder();
            holder.favouriteButton = convertView.findViewById(R.id.favourite);
            holder.diseaseName = convertView.findViewById(R.id.diseaseName);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        final DiseaseData item = list.get(position) ;
        holder.diseaseName.setText(item.getDiseaseName());

        if( getFavorite(item.getId()) == 0 && item.flag == 0){
            favMap.put((long)position,"show");
        }else {
            favMap.put((long)position,"showed");
        }

        if(favMap.get((long)position).equals("show") ){

            holder.favouriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
            //DrawableCompat.setTint(holder.favouriteButton.getDrawable(), ContextCompat.getColor(activity,R.color.colorGreen));

        }else {
            holder.favouriteButton.setImageResource(R.drawable.ic_favorite_red_24dp);
            //DrawableCompat.setTint(holder.favouriteButton.getDrawable(), ContextCompat.getColor(activity,R.color.Red));
        }


        holder.favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int clickId = item.getId();
//                    myDBHelper = new MyDBHelper(activity);
                System.out.println("id of medicine............. : "+clickId);
                if( item.flag == 0 && favMap.get((long)position).equals("show")){



                   /* holder.rotate.setImageResource(R.drawable.ic_change_history_black_24dp);
                    holder.linearLayout.setVisibility(View.VISIBLE);*/

                    checkTable(disease,clickId);
                    holder.favouriteButton.setImageResource(R.drawable.ic_favorite_red_24dp);
//                        DrawableCompat.setTint(holder.favouriteButton.getDrawable(), ContextCompat.getColor(activity,R.color.Red));
//                        holder.favouriteButton.setHasTransientState(true);
                    favMap.put((long) position,"showed");
                    item.flag = 1;

                }else {

                    holder.favouriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
//                        DrawableCompat.setTint(holder.favouriteButton.getDrawable(), ContextCompat.getColor(activity,R.color.colorGreen));
                    checkTable(disease,clickId);
                    item.flag = 0;
                    favMap.put((long)position,"show");
                }

            }
        });
        return convertView;


    }
    private int getFavorite(int clickId){

        int status = 0;
        Cursor cursor = null;
//        myDBHelper = new MyDBHelper(activity);
        MyDatabase myDatabase = new MyDatabase(activity);
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            //DO some db writing
            String query = "select status from favourite where click_id = \""+clickId+"\" AND type = \""+disease+"\"";
            cursor = sqLiteDatabase.rawQuery(query,null);
            if (cursor != null && cursor.moveToFirst()){
                do {
                    status = cursor.getInt(cursor.getColumnIndex("status"));
//                System.out.println("description..................................... : "+description);
                    System.out.println("fav button status..................................... : "+status);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            cursor.close();
            sqLiteDatabase.endTransaction();
        }




        sqLiteDatabase.close();

        return status;
    }

    private void checkTable(String type,int clickId){

        int status = 0;
        int id = 0;
//        myDBHelper = new MyDBHelper(activity);
        myDatabase  = new MyDatabase(activity);
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select status,id from favourite where click_id = \""+clickId+"\" AND type = \""+type+"\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                status = cursor.getInt(cursor.getColumnIndex("status"));
                id = cursor.getInt(cursor.getColumnIndex("id"));
                System.out.println("fav button status..................................... : "+status);
            } while (cursor.moveToNext());
            //TODO: updateFavouriteTable(id,status)
            if(status == 0){
                myDatabase.updateFavourite(id,1);
            }else{
                myDatabase.updateFavourite(id,0);
            }

        }else{
            //TODO: insertFavouriteTable(type,clickId)
            myDatabase.insertFavourite(type,clickId);
        }


        cursor.close();
        sqLiteDatabase.close();


    }

    @Override
    public Filter getFilter() {
        return diseaseFilter;
    }

    private  Filter diseaseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DiseaseData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(diseaseData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DiseaseData item : diseaseData) {
                    if (item.getDiseaseName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {


            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
