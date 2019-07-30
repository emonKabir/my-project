package com.example.emon.bdmedic.Laboratory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.List;

public class ListviewAdapter extends BaseAdapter {


        public List<InvestigationData> productList;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
        Activity activity;

        //Context mContext;

        public ListviewAdapter(Activity activity, List<InvestigationData> productList) {
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
            TextView normal_finding;
            Button action;
           /* TextView decreased;
            TextView others;*/
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            LayoutInflater inflater = activity.getLayoutInflater();


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.laboratory_table, null);
                holder = new ViewHolder();
             holder.name = convertView.findViewById(R.id.iName);
             holder.normal_finding =convertView.findViewById(R.id.iNormalFindig);
             holder.action = convertView.findViewById(R.id.iAction);
            /* holder.decreased = convertView.findViewById(R.id.decreased);
             holder.others =convertView.findViewById(R.id.others);*/
             convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

           final InvestigationData   item = productList.get(position) ;
            holder.name.setText(item.getName());
            System.out.println("holderName : "+item.getName() );
            holder.normal_finding.setText(item.getNormal_finding());
            System.out.println("holderName : "+item.getNormal_finding() );
//            holder.action.setText(item.getIncreased());
           /* holder.decreased.setText(item.getDecreased());
            holder.others.setText(item.getOthers());*/
            holder.action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("...............................first");
//                  final  MyDBHelper myDBHelper = new MyDBHelper(activity);
                  final MyDatabase myDatabase = new MyDatabase(activity);
//                    myDBHelper.getReadableDatabase();
                    System.out.println("...............................seod");
                  final  StringBuilder hName = new StringBuilder();
                  final  StringBuilder hAddress = new StringBuilder();
                  final  StringBuilder hPrice = new StringBuilder();
                    System.out.println("...............................third");
                  final  SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
                    System.out.println("...............................fourth");
//                  final int id = item.getId();
                    System.out.println("...............................fifth");
                    System.out.println("item................................... : "+item.getId());
                  final  String query = "SELECT * from investigation_price  left join hospital on investigation_price.hospital_id = hospital.id  where investigation_price.investigation_id = "+item.getId()+"";
                    System.out.println("...............................sixth");
                  final Cursor cursor = sqLiteDatabase.rawQuery(query,null);
                    System.out.println("...............................seventh");
//                    ArrayList<InvestigationData> names = new ArrayList<InvestigationData>();

                    cursor.moveToFirst();
                    while(!cursor.isAfterLast()){
//                        InvestigationData investigationData = new InvestigationData(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("normal_finding")),cursor.getString(cursor.getColumnIndex("increased")),cursor.getString(cursor.getColumnIndex("decreased")),cursor.getString(cursor.getColumnIndex("others")));
//                        names.add(investigationData);

                        //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));

                      final  String hospitalName = cursor.getString(cursor.getColumnIndex("name"));
                      final  String Address = cursor.getString(cursor.getColumnIndex("address"));
                      final  String price = cursor.getString(cursor.getColumnIndex("price"));

                        hName.append(hospitalName);
                        hName.append("\n");
                        hName.append(Address);
                        hName.append("\n");
                        hName.append("\n");
                        hPrice.append(price);
                        hPrice.append("\n");
                        hPrice.append("\n");
                        hPrice.append("\n");


//                        hName.append("\n");

                        cursor.moveToNext();

                    }
                    cursor.close();
                    sqLiteDatabase.close();

                    final Dialog dialog = new Dialog(activity);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationForPriceButton;
                    dialog.setContentView(R.layout.laboratory_price_alertdialog_layout);
                    TextView hospitalName = dialog.findViewById(R.id.hospitalName);
                    TextView Hprice = dialog.findViewById(R.id.Hprice);
//                    TextView other = dialog.findViewById(R.id.other);
                    ImageView dismiss  = dialog.findViewById(R.id.dismiss);
                    hospitalName.setText(hName.toString());
                    Hprice.setText(hPrice.toString());
//                    other.setText(hAddress.toString());

                    dismiss.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });

           /* if((position % 2) == 0){

                convertView.setBackgroundResource(R.color.lightGreen);
            }else{
                convertView.setBackgroundResource(R.color.colorWhite);
            }*/
            return convertView;


        }

  /*  private void setDialog(String columnname,String column){
        System.out.println("lllllllllllllllllllllllllllllllllllll");
        alertDialogBuilder.setTitle(columnname);
        alertDialogBuilder.setMessage(column);
        System.out.println("ppppppppppppppppppppppppppppppppppppppppppppppppppp");


        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppkkkkkkkkkkkkkkkkk");
        alertDialog = alertDialogBuilder.create();
        System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppkkkkkkkkkkkkkkkkklllllllll");
        alertDialog.show();
        System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppkkkkkkkkkkkkkkkkklllllllll2222222");

    }*/

    }


