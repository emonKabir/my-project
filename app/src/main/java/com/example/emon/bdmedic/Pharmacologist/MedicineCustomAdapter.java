package com.example.emon.bdmedic.Pharmacologist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emon.bdmedic.R;

import java.util.HashMap;
import java.util.List;

import eu.davidea.flipview.FlipView;

public class MedicineCustomAdapter extends BaseAdapter {




    protected Drugs context;
        public List<DrugsData> productList;
        Activity activity;

        String startColor = "#79e2e2";
        String endColor = "#32aef4";
        //Context mContext;
    DrugsData drugsData;
    private static HashMap<Long,String> map = new HashMap<Long,String>();

        public MedicineCustomAdapter(Activity activity, List<DrugsData> productList) {
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
            FlipView flipView;
            TextView priceTotal;

            LinearLayout linearLayout;
//            MaterialSpinner mSpinner;
//            LinearLayout linearLayout;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater inflater = activity.getLayoutInflater();


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.sample_drugs_list, null);
                holder = new ViewHolder();
//                holder.pills = convertView.findViewById(R.id.pills);
             holder.name = convertView.findViewById(R.id.TradeName);
             holder.companyName =convertView.findViewById(R.id.companyName);
             holder.genericType = convertView.findViewById(R.id.genericType);
             holder.genericName = convertView.findViewById(R.id.genericName);
             holder.unit = convertView.findViewById(R.id.Dose);
             holder.unitPrice = convertView.findViewById(R.id.Price);
                holder.rotate = convertView.findViewById(R.id.rotate);
                holder.flipView = convertView.findViewById(R.id.flip_layout2);
                holder.priceTotal = convertView.findViewById(R.id.priceTotal);
//                holder.linearLayout = convertView.findViewById(R.id.hiddenLayout);
//             holder.linearLayout = convertView.findViewById(R.id.linearLayout);
//             holder.others =convertView.findViewById(R.id.others);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            System.out.println("pos : " + position);
            //for(int i = 0; i < productList.size(); i++) {
           final DrugsData item = productList.get(position);
                holder.name.setText(item.getName());
                holder.companyName.setText(item.getCompanyName());
                holder.genericType.setText(item.getGenericType());
                holder.genericName.setText(item.getGenerciName());
                 holder.priceTotal.setText(item.getPackagePrice());

            if(item.priceShowFlag == 0){
                map.put((long) position,"show");
            }else{
                map.put((long) position,"showed");
            }

            if(map.get((long)position).equals("show")){
                holder.rotate.setImageResource(R.drawable.ic_details_black_24dp);
//                holder.linearLayout.setVisibility(View.GONE);

            }else{
                holder.rotate.setImageResource(R.drawable.ic_change_history_black_24dp);
//                holder.linearLayout.setVisibility(View.VISIBLE);
            }

            Shader myShader = new LinearGradient(
                    0, 0, 0, 30,
                    Color.parseColor(startColor), Color.parseColor(endColor),
                    Shader.TileMode.MIRROR );
             holder.unit.getPaint().setShader( myShader );
             holder.unitPrice.getPaint().setShader( myShader );
                holder.unit.setText(item.getSss());
                holder.unitPrice.setText(item.getUnitPrice());


            holder.rotate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


//                   CustomAdapter.this.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                    if(item.priceShowFlag == 0 && map.get((long)position).equals("show")){

                        holder.rotate.setImageResource(R.drawable.ic_change_history_black_24dp);
//                        holder.linearLayout.setVisibility(View.VISIBLE);
                        holder.flipView.flip(true);

                        holder.flipView.setHasTransientState(true);
                        map.put((long) position,"showed");
                        item.priceShowFlag = 1;
                    }else{

                        holder.rotate.setImageResource(R.drawable.ic_details_black_24dp);
//                        holder.linearLayout.setVisibility(View.GONE);
                        holder.flipView.flip(false);
                        item.priceShowFlag = 0;
                        map.put((long)position,"show");
                    }
                  /* holder.priceTotal.setVisibility(View.VISIBLE);
                   CustomAdapter.this.notifyDataSetChanged();*/

                }

            });


            return convertView;


        }




}


