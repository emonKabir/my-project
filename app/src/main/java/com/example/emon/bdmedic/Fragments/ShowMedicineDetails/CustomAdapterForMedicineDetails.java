package com.example.emon.bdmedic.Fragments.ShowMedicineDetails;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emon.bdmedic.Pharmacologist.MedicineView;
import com.example.emon.bdmedic.R;
import com.example.emon.bdmedic.WebView.SampleTable;


import java.lang.reflect.Parameter;
import java.util.List;

public class CustomAdapterForMedicineDetails extends BaseAdapter {


    protected SampleTable context;
        public List<MedicineDetails> productList;
        Activity activity;
        //Context mContext;

        public CustomAdapterForMedicineDetails(Activity activity, List<MedicineDetails> productList) {
            super();
            this.activity = activity;
            this.productList = productList;
        }

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
            TextView firstTextView;
            TextView secondTextView;
            TextView thirdTextView;
            ImageView quote;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater inflater = activity.getLayoutInflater();


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.medicine_details_layout, null);
                holder = new ViewHolder();
             holder.firstTextView = convertView.findViewById(R.id.firstTextview);
             holder.secondTextView =convertView.findViewById(R.id.secondTextview);
             holder.thirdTextView = convertView.findViewById(R.id.thirdTextview);
             holder.quote = convertView.findViewById(R.id.quote);

             convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final MedicineDetails item = productList.get(position) ;
            holder.firstTextView.setText(item.getFirstTextView());
//            System.out.println("holderName : "+item.getSecondTextView() );
            holder.secondTextView.setText(item.getSecondTextView());
//            System.out.println("holderName : "+item.getNormal_finding() );
            holder.thirdTextView.setText(item.getThirdTextView());

            if(!item.getFirstTextView().equals("") && !item.getThirdTextView().equals("")) {
                       /* Intent intent = new Intent(activity,MedicineTesting.class);

                        activity.startActivity(intent);*/

                       holder.quote.setVisibility(View.VISIBLE);


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    holder.firstTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkRed));
                    holder.secondTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkRed));
                    holder.thirdTextView.setTextColor(ContextCompat.getColor(activity, R.color.DarkRed));
                } else {
                    holder.firstTextView.setTextColor(activity.getResources().getColor(R.color.DarkRed));
                    holder.secondTextView.setTextColor(activity.getResources().getColor(R.color.DarkRed));
                    holder.thirdTextView.setTextColor(activity.getResources().getColor(R.color.DarkRed));
                }


            }else{

//                setMarginLeft(holder.secondTextView,30);

                holder.firstTextView.setText("       ");
            }

            if(item.getFirstTextView().equals("") && item.getThirdTextView().equals("")){
               /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(20,0,0,0);
                holder.secondTextView.setLayoutParams(params);*/


                /*parameter =  (RelativeLayout.LayoutParams) txtField.getLayoutParams();
                parameter.setMargins(leftMargin, parameter.topMargin, parameter.rightMargin, parameter.bottomMargin); // left, top, right, bottom
                txtField.setLayoutParams(parameter);*/



            }


            return convertView;


        }

 /*   public void updateTV(final int total){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                    System.out.println(".............................. :"+str1);
                context.allTotal = context.allTotal - total;
                context.totalPrice.setText(String.valueOf(context.allTotal));
//                    context.allTotal = Integer.valueOf(str1);
                System.out.println("hjkhkjhkjhk :"+context.allTotal);
            }
        });
    }*/



    public static void setMarginLeft(View v, int left) {
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams)v.getLayoutParams();
        params.setMargins(left, params.topMargin,
                params.rightMargin, params.bottomMargin);
    }

    public static void setMarginRight(View v, int left) {
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams)v.getLayoutParams();
        params.setMargins(left, params.topMargin,
                params.rightMargin, params.bottomMargin);
    }

    }


