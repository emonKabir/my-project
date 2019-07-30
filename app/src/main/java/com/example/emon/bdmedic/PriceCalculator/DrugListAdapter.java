package com.example.emon.bdmedic.PriceCalculator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emon.bdmedic.R;

import java.util.Collections;
import java.util.List;

public class DrugListAdapter extends BaseAdapter {


    protected PriceCalculator context;
        public List<DrugList> list;
//        Activity activity;
        Integer index;
//        PriceCalculator priceCalculator = new PriceCalculator();
        //Context mContext;
        public DrugListAdapter(Context context){
            this.context = (PriceCalculator) context;
        }

        public DrugListAdapter(Context context, List<DrugList> list) {
//            super();
            this.context = (PriceCalculator) context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get( position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView drugName;
            TextView dose;
            TextView quantity;
            TextView total;
            TextView star;
            TextView equal;
            TextView currency;
            TextView dPrice;
            TextView cross;
            TextView allTotal;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater inflater = context.getLayoutInflater();


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.drug_list_row, null);
                holder = new ViewHolder();
             holder.drugName = convertView.findViewById(R.id.drugName);
             holder.quantity = convertView.findViewById(R.id.dQuantity);
             holder.dose =convertView.findViewById(R.id.dose);
             holder.dPrice =convertView.findViewById(R.id.dPrice);
             holder.equal =convertView.findViewById(R.id.equal);
             holder.total = convertView.findViewById(R.id.total);
//             holder.currency =convertView.findViewById(R.id.currency);
             holder.cross =convertView.findViewById(R.id.cross);
             holder.allTotal = convertView.findViewById(R.id.totalPrice);

             convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }

            final DrugList item = list.get(position) ;
            holder.drugName.setText(item.getDrugName());
            holder.quantity.setText(item.getQuantity());
            holder.dose.setText(item.getDose());
            holder.dPrice.setText(item.getdPrice());
            holder.equal.setText(item.getEqual());
            holder.total.setText(item.getTotal());
//            holder.currency.setText(item.getCurrency());
            /*Collections.reverse(list);
            DrugListAdapter.this.notifyDataSetChanged();*/
//            holder.allTotal.setText(item.getTotalPrice());
            final View finalConvertView = convertView;
            final View finalConvertView1 = convertView;
            holder.cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int singleTotal = Integer.valueOf(item.getTotal());

//                    int minusTotal = item.getTotalPrice() - singleTotal;
//                    String totalString = String.valueOf(singleTotal);
                    updateTV(singleTotal);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(finalConvertView1,View.ALPHA,0);
                    objectAnimator.setDuration(1000);
                    finalConvertView.setHasTransientState(true);
                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {

                            list.remove(item);
                            DrugListAdapter.this.notifyDataSetChanged();
                            finalConvertView.setAlpha(1);
                            finalConvertView.setHasTransientState(false);

                        }
                    });

                   objectAnimator.start();
                }
            });
            return convertView;


        }







        public void updateTV(final int total){
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(".............................. :"+str1);
                    context.allTotal = context.allTotal - total;
                    context.totalPrice.setText(String.valueOf(context.allTotal));
                    context.grandTotal.setText(String.valueOf(context.allTotal));
//                    context.allTotal = Integer.valueOf(str1);
                    System.out.println("hjkhkjhkjhk :"+context.allTotal);
                }
            });
        }


    }


