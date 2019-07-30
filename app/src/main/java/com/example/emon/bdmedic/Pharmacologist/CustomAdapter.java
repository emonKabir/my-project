package com.example.emon.bdmedic.Pharmacologist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.davidea.flipview.FlipView;

//import static com.example.emon.bdmedic.Pharmacologist.Drugs.checkHash;
public class CustomAdapter extends BaseAdapter implements Filterable {




    protected Drugs context;
    public List<DrugsData> productList;
    private List<DrugsData>drugsDataList;
    List<String>unitList;
    List<String>unitPriceList;
    List<String> packagePriceMethod;
    List<String> totalPackagePrice;
    Activity activity;
    StringBuilder stringBuilder;
    public static final String medicine = "medicine";

   private static HashMap<Long,String>map = new HashMap<Long,String>();
   private static  HashMap<Long,String>favMap = new HashMap<Long, String>();
//    private static HashMap<Integer,Boolean>map2 = new HashMap<Integer, Boolean>();

    String startColor = "#79e2e2";
    String endColor = "#32aef4";
//    LayoutInflater inflater;
    boolean result = false;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
        //Context mContext;
    DrugsData drugsData;

        public CustomAdapter(Activity activity, List<DrugsData> productList) {
            super();
            this.activity = activity;
            this.productList = productList;
            drugsDataList = new ArrayList<>(productList);
        }


/*

    @Override
    public int getViewTypeCount() {
        return 2;
    }
*/


    /*@Override
    public int getItemViewType(int position) {
        return (productList.get(position).getContactType() == ContactType.CONTACT_WITH_IMAGE) ? 0 : 1;
    }*/
    @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int position) {
            return productList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



    private class ViewHolder {
            TextView name;
            TextView companyName;
            TextView genericType;
            TextView unit;
            TextView packagePrice;
            TextView unitPrice;
            TextView packageMethod;
            TextView genericName;
            ImageView pills;
            ImageView rotate;
            RelativeLayout relativeLayout;
            TextView priceTotal;
            ImageView favouriteButton;
            FlipView flipView;
            LinearLayout linearLayout;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
         final  LayoutInflater  inflater = activity.getLayoutInflater();


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.sample_drugs_list, null);
                holder = new ViewHolder();
             holder.name = convertView.findViewById(R.id.TradeName);
             holder.companyName =convertView.findViewById(R.id.companyName);
             holder.genericType = convertView.findViewById(R.id.genericType);
             holder.genericName = convertView.findViewById(R.id.genericName);
             holder.unit = convertView.findViewById(R.id.Dose);
             holder.unitPrice = convertView.findViewById(R.id.Price);
             holder.rotate = convertView.findViewById(R.id.rotate);
             holder.priceTotal = convertView.findViewById(R.id.priceTotal);
             holder.flipView = convertView.findViewById(R.id.flip_layout2);
             holder.favouriteButton = convertView.findViewById(R.id.fav);
            /* holder.linearLayout = convertView.findViewById(R.id.hiddenLayout);
             holder.relativeLayout = convertView.findViewById(R.id.flipLayout);*/



                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
           final DrugsData item = productList.get(position);
                holder.name.setText(item.getName());
                holder.companyName.setText(item.getCompanyName());
                holder.genericType.setText(item.getGenericType());
                holder.genericName.setText(item.getGenerciName());
                holder.priceTotal.setText(item.getPackagePrice());
               /* holder.unit.setText(item.getSss());
                holder.unitPrice.setText(item.getUnitPrice());*/


           /* if ((position % 2)== 0) {
                convertView.setBackgroundResource(R.color.colorWhite);
            } else if ((position % 2)!= 0) {
                convertView.setBackgroundResource(R.color.lightGreen);
            }*/


            if( getFavorite(item.getId()) == 0 && item.favShowFlag == 0){
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


            if(item.priceShowFlag == 0){
                    map.put((long) position,"show");
                }else{
                    map.put((long) position,"showed");
                }




                if(map.get((long)position).equals("show") ){
                    holder.rotate.setImageResource(R.drawable.ic_details_black_24dp);

                }else{
                    holder.rotate.setImageResource(R.drawable.ic_change_history_black_24dp);

                }

            final Shader myShader = new LinearGradient(
                    0, 0, 0, 30,
                    Color.parseColor(startColor), Color.parseColor(endColor),
                    Shader.TileMode.MIRROR );
             holder.unit.getPaint().setShader( myShader );
             holder.unitPrice.getPaint().setShader( myShader );
                holder.unit.setText(item.getSss());
                holder.unitPrice.setText(item.getUnitPrice());


            holder.rotate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(final View v) {

                   if(item.priceShowFlag == 0 && map.get((long)position).equals("show")){

                           holder.rotate.setImageResource(R.drawable.ic_change_history_black_24dp);
                           holder.flipView.flip(true);
                           holder.flipView.setHasTransientState(true);
                          map.put((long) position,"showed");
                          item.priceShowFlag = 1;

                   }else{

                       holder.rotate.setImageResource(R.drawable.ic_details_black_24dp);
                       holder.flipView.flip(false);
                       item.priceShowFlag = 0;
                       map.put((long)position,"show");
                   }

               }

           });


            holder.favouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final int clickId = item.getId();
//                    myDBHelper = new MyDBHelper(activity);
                    System.out.println("id of medicine............. : "+clickId);
                    if( item.favShowFlag == 0 && favMap.get((long)position).equals("show")){



                   /* holder.rotate.setImageResource(R.drawable.ic_change_history_black_24dp);
                    holder.linearLayout.setVisibility(View.VISIBLE);*/

                        checkTable(medicine,clickId);
                        holder.favouriteButton.setImageResource(R.drawable.ic_favorite_red_24dp);
//                        DrawableCompat.setTint(holder.favouriteButton.getDrawable(), ContextCompat.getColor(activity,R.color.Red));
//                        holder.favouriteButton.setHasTransientState(true);
                        favMap.put((long) position,"showed");
                        item.favShowFlag = 1;

                    }else {

                        holder.favouriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
//                        DrawableCompat.setTint(holder.favouriteButton.getDrawable(), ContextCompat.getColor(activity,R.color.colorGreen));
                        checkTable(medicine,clickId);
                        item.favShowFlag = 0;
                        favMap.put((long)position,"show");
                    }

                }
            });


            return convertView;
        }



    public static int getLineCount(String text){

        return text.split("[\n|\r]").length;
    }

    private int getFavorite(int clickId){

        int status = 0;
        Cursor cursor = null;
//        myDBHelper = new MyDBHelper(activity);
        myDatabase = new MyDatabase(activity);
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            //DO some db writing
            String query = "select status from favourite where click_id = \""+clickId+"\" AND type = \""+medicine+"\"";
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
        myDatabase = new MyDatabase(activity);
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
        return drugsFilter;
    }

    private  Filter drugsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DrugsData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(drugsDataList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

//                 item.getGenerciName().toLowerCase().contains(filterPattern) || item.getCompanyName().toLowerCase().contains(filterPattern)
                for (DrugsData item : drugsDataList) {
                    if ( item.getGenerciName().toLowerCase().startsWith(filterPattern)|| item.getName().toLowerCase().startsWith(filterPattern) || item.getCompanyName().toLowerCase().startsWith(filterPattern) ) {
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
            productList.clear();
            productList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}


