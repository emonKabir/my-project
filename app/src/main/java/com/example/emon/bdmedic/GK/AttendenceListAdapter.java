package com.example.emon.bdmedic.GK;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/*

public class AttendenceListAdapter extends BaseAdapter {

    private Boolean isInterstitalAdEnable;
//    public InterstitialAd mInterstitialAd;
    public static FirebaseAuth auth;
    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    public static String mUserId;
    public static FirebaseUser mFirebaseUser;
//    class_item class_item;

    private   ArrayList<String> nameList;
    public Activity activity;
   // private static HashMap<Integer, Boolean> checkHashForPreviousClassAttend;
    private ArrayList<Boolean> attendanceList;
    public AttendenceListAdapter(Activity activity, ArrayList<String> nameList) {
        this.nameList = nameList;
        this.activity = activity;
//        this.class_item=class_item;
        attendanceList = new ArrayList<>();
        for(int i=0; i<nameList.size(); i++)
        {
            attendanceList.add(new Boolean(true));
        }
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.attendence_item, null);
        }

            final int pos = position;
            Log.d("GK",position + " position");
            TextView textView = (TextView) v.findViewById(R.id.attendanceText);
             String text;
             text=(nameList.get(position));
            textView.setText(text);


      Boolean isAbsentPreviousClass=text.contains("(গতক্লাসে অনুপস্থিত)");

      if(isAbsentPreviousClass){
          v.setBackgroundColor(Color.RED);
      }



            final CheckBox checkBox = (CheckBox)v.findViewById(R.id.attMarker);//For attendance
            final CheckBox checkBox1 =(CheckBox) v.findViewById(R.id.absentMarker);//For absent



        if(checkHash.containsKey(pos)){
            if(checkHash.get(pos)) {
                checkBox.setChecked(true);
                checkBox1.setChecked(false);
                attendanceList.set(pos,checkBox.isChecked());
                Log.d("GK",pos + " position true");
            }
            else {
                Log.d("GK",pos + " position false");
                attendanceList.set(pos,false);
                checkBox.setChecked(false);
                checkBox1.setChecked(true);
            }
        }else {
            checkBox.setChecked(true);
            checkBox1.setChecked(false);
        }





//        for(int i=0;i<checklist.size();i++){
//            if(pos==checklist.get(i)){
//               checkBox.setChecked(false);
//               checkBox1.setChecked(false);
//            }
//        }

            //How to add set onlickListern in Growable listView adapter

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent launchinIntent = new Intent(AttendanceActivity.activity, studentAllInfoShowActiviy.class);
                    String roll = AttendanceActivity.rolls.get(pos);
                    Log.d("GK",pos + " pos in click listener of list view");
                    launchinIntent.putExtra("Roll", roll);
                    launchinIntent.putExtra("classItem",class_item);
                    AttendanceActivity.activity=activity;
                    AttendanceActivity.activity.startActivity(launchinIntent);
                }
            });


            //Check and uncheck the check box vice versa
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("GK",pos + " pos in checkBox of list view");
                    attendanceList.set(pos,checkBox.isChecked());
                    checkBox1.setChecked(!checkBox.isChecked());
                    checkHash.put(pos,checkBox.isChecked());
                }
            });

            checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attendanceList.set(pos,!checkBox1.isChecked());
                    checkBox.setChecked(!checkBox1.isChecked());
                    checkHash.put(pos,!checkBox1.isChecked());
                }
            });

        return v;
    }



    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }
    public String getItem(int position) {
        return nameList.get(position);
    }
    public void remove(String item) {
        int position = nameList.indexOf(item);
        if (position > -1) {
            nameList.remove(position);

        }
    }

    public int getItemCount() {
        return nameList.size();
    }
    public void saveAll()
    {
        mInterstitialAd = new InterstitialAd(activity);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(activity.getString(R.string.Interstitial_info_activity));

        double totalAttendendStudentNumber=0;
        double totalAbsentStudentNumberPersentage=0;
        double totalAbsentStudentNumber=0;
        for(int i=0; i<nameList.size(); i++)
        {
            boolean sts;
            if(attendanceList.get(i)){
                sts = true;
                totalAttendendStudentNumber++;
            }
            else sts = false;
            AttendenceData attendenceData = new AttendenceData();
            attendenceData.setStatus(sts);
            attendenceData.setSubject(AttendanceActivity.subject);
            attendenceData.setDate(AttendanceActivity.time);

            //TODO:DATABASE CONNECTION
            firebaseDatabase= FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference();

            //TODO: USER (for FB logic auth throw null pointer exception)
            auth = FirebaseAuth.getInstance();
            mFirebaseUser = auth.getCurrentUser();
            databaseReference.keepSynced(true);
            mUserId=mFirebaseUser.getUid();

            MainActivity.databaseReference=databaseReference;
            MainActivity.mUserId=mUserId;

            MainActivity.databaseReference.child("Users").child(mUserId).child("Class").child(AttendanceActivity.classitemAttendence.getName()+ AttendanceActivity.classitemAttendence.getSection()).child("Student").child(AttendanceActivity.rolls.get(i)).child("Attendance").push().setValue(attendenceData);

            //activity.finish();
        }
        totalAbsentStudentNumberPersentage=(totalAttendendStudentNumber/nameList.size())*100.00;
        totalAbsentStudentNumber=nameList.size()-totalAttendendStudentNumber;
        String massegeOfDailyPersentage="আজকের মোট উপস্থিত শিক্ষার্থীর সংখ্যা "+(int)totalAttendendStudentNumber+",মোট অনুপস্থিত শিক্ষার্থীর সংখ্যা "+(int)totalAbsentStudentNumber+", এবং শতকরা উপস্থিতির হার "+(int)totalAbsentStudentNumberPersentage+"%";


        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setMessage(massegeOfDailyPersentage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"ওকে",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        AdRequest adRequest = new AdRequest.Builder()
                                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                // Check the LogCat to get your test device ID
                                .addTestDevice("26CA880D6BB164E39D8DF26A04B579B6")
                                .build();

                        activity.finish();

                        // Load ads into Interstitial Ads

                       // mInterstitialAd.loadAd(adRequest);
                        mInterstitialAd.setAdListener(new AdListener() {
                            public void onAdLoaded() {
                                showInterstitial();
                            }

                            @Override
                            public void onAdFailedToLoad(int i) {
                                activity.finish();
                                Toast.makeText(AttendanceActivity.context, " উপস্থিতি সার্ভারে সেভ হয়েছে । ", Toast.LENGTH_LONG).show();
                                super.onAdFailedToLoad(i);
                            }

                            @Override
                            public void onAdClosed() {
                                activity.finish();
                                Toast.makeText(AttendanceActivity.context, " উপস্থিতি সার্ভারে সেভ হয়েছে । ", Toast.LENGTH_LONG).show();
                                super.onAdClosed();
                            }
                        });

                    }
                });
        alertDialog.show();
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
*/
