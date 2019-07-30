package com.example.emon.bdmedic.ContactList;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emon.bdmedic.ApiInterface;
import com.example.emon.bdmedic.ContactList.PojoClass.ContactData;
import com.example.emon.bdmedic.ContactList.PojoClass.ContactPjo;
import com.example.emon.bdmedic.ContactList.PojoClass.PhoneNumber;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.Media.VideoAdapter;
import com.example.emon.bdmedic.MethodTesting;
import com.example.emon.bdmedic.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class ContactFragment extends Fragment {
    Button button_android_contacts;
    ListView listview_Android_Contacts;
    SearchView searchView;
    MyDatabase myDatabase;
    AdapterForAndroidContacts adapter;
    Button refreshButton;
    MethodTesting methodTesting;
//    Map<String,String> contactMap;
    Map<String,String> params;
    public List<PhoneNumber>androidContact;
  public   ArrayList<Android_Contact> arrayList_Android_Contacts;
    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_contact, container, false);

        searchView = v.findViewById(R.id.searchView);
        refreshButton = v.findViewById(R.id.refreshContact);
        System.out.println("first........................");
        listview_Android_Contacts = v.findViewById(R.id.listview_Android_Contacts);
        System.out.println("secnd........................");
        arrayList_Android_Contacts = new ArrayList<Android_Contact>();
        System.out.println("third........................");
        getContact();
        System.out.println("fourth........................");
        androidContact = new ArrayList<>();
        params  = new HashMap<>();
        myDatabase = new MyDatabase(getActivity());
        methodTesting = new MethodTesting(getActivity());
//        button_android_contacts = v.findViewById(R.id.button_android_contacts);
        /*showContacts();
        MyDBHelper myDBHelper = new MyDBHelper(getContext());
        myDBHelper.insertContactList(arrayList_Android_Contacts);*/


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                listview_Android_Contacts.setAdapter(adapter);
                return false;
            }
        });


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new LoadContacts().execute();
            }
        });

        return v;
    }


    private void getContact(){
        System.out.println("fith........................");
//        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        MyDatabase myDatabase = new MyDatabase(getActivity());
        System.out.println("sixth........................");
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        System.out.println("seven........................");
        String selectQuery = "SELECT *  FROM contact_list";
        System.out.println("eight........................");
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        System.out.println("nine........................");
        if(cursor.getCount()>0){

            System.out.println("ten........................");
            cursor.moveToFirst();
            ArrayList<Android_Contact>contacts = new ArrayList<Android_Contact>();
            while(!cursor.isAfterLast()){

                String name = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println("name............ : "+name);
                String number = cursor.getString(cursor.getColumnIndex("number"));
                System.out.println("number........................ : "+number);
                String bloodGroup = cursor.getString(cursor.getColumnIndex("blood_group"));
                System.out.println("blood goup........................" +bloodGroup);
                if(bloodGroup != null && name != null){
                    contacts.add(new Android_Contact(name,number,bloodGroup));
                }

                cursor.moveToNext();

            }

            cursor.close();
            sqLiteDatabase.close();

           /* VideoAdapter videoAdapter = new VideoAdapter(getActivity(),video);
            videoListview.setAdapter(videoAdapter);*/
            Collections.sort(contacts, new SortBasedOnContactName());
             adapter = new AdapterForAndroidContacts(getActivity(), contacts);
            listview_Android_Contacts.setAdapter(adapter);

        }else{
            Toast.makeText(getActivity(),"wait while data is loading",Toast.LENGTH_LONG).show();
        }

    }

    private class LoadContacts extends AsyncTask<Void,Void, Boolean> {

        ProgressDialog progressDialog;
        //        MyDBHelper myDBHelper = new MyDBHelper(LoginActivity.this);
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            System.out.println("enter in the excute system................................");

            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("wait while contact updating!!!");
            progressDialog.show();
           /* progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);*/

        }


        @Override
        protected Boolean doInBackground(Void... params)
        {

//            myDBHelper.insertSponsor(params[0]);
//            onData(diseaseManageDataList)
            showContacts();


            return true;
        }



        @Override
        protected void onPostExecute(final Boolean result)
        {

            super.onPostExecute(result);
            if(result){

                progressDialog.dismiss();
                /*hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
//                progressDialog.dismiss();
//                circularProgressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(),"Please wait until slider slide again!!!",Toast.LENGTH_LONG).show();
            }


        };
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
           /* List<String> contacts = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
            lstNames.setAdapter(adapter);*/
            fp_get_Android_Contacts();
        }
    }
    public void fp_get_Android_Contacts() {



//--< get all Contacts >--
        Cursor cursor_Android_Contacts = null;
        ContentResolver contentResolver = getActivity().getContentResolver();
        try {
            cursor_Android_Contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        } catch (Exception ex) {
            Log.e("Error on contact", ex.getMessage());
        }
//--</ get all Contacts >--

//----< Check: hasContacts >----
        if (cursor_Android_Contacts.getCount() > 0) {
//----< has Contacts >----
//----< @Loop: all Contacts >----
            while (cursor_Android_Contacts.moveToNext()) {
//< init >
                Android_Contact android_contact = new Android_Contact();
                String contact_id = cursor_Android_Contacts.getString(cursor_Android_Contacts.getColumnIndex(ContactsContract.Contacts._ID));
                String contact_display_name = cursor_Android_Contacts.getString(cursor_Android_Contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//</ init >

//----< set >----
                android_contact.name = contact_display_name;

//                contactMap.put("name",contact_display_name);

//----< get phone number >----
                int hasPhoneNumber = Integer.parseInt(cursor_Android_Contacts.getString(cursor_Android_Contacts.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null
                            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                            , new String[]{contact_id}
                            , null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//< set >
                        if(!phoneNumber.matches(".*[#].*"))
                        {
                            android_contact.phone_number = phoneNumber;
                           /* System.out.println("phone number................ : "+phoneNumber);
                            System.out.println("yes");*/
                            androidContact.add(new PhoneNumber(phoneNumber));
                           /* PhoneNumber mInfo=new PhoneNumber(phoneNumber);
                            String request = new Gson().toJson(mInfo);*/

                            //Here the json data is add to a hash map with key data


                        }

//                        bloodGroup.mobileNumber = phoneNumber;

//                        contactMap.put("phone_number",phoneNumber);



                    }
                    phoneCursor.close();
                }

                arrayList_Android_Contacts.add(android_contact);

            }


        }

        myDatabase.insertContactList(arrayList_Android_Contacts);
        //TODO:contactBloodGroupStore
       /*

        List<PhoneNumber> androidContactList = new ArrayList<>();
        int low = 0;
        int high = low + 98;
        if(high > arrayList_Android_Contacts.size()){
            high = arrayList_Android_Contacts.size();
        }
        while(low <= arrayList_Android_Contacts.size()){
            for(int i= low; i < high; i++){

                String number = arrayList_Android_Contacts.get(i).getPhone_number();
                androidContactList.add(new PhoneNumber(number));
            }
            Gson gsonBuilder = new GsonBuilder().create();
            String json = gsonBuilder.toJson(androidContactList);
            System.out.println("json file ................................... : "+json);
            Log.e("emon kabir",json);

            String json3 = "[{\"name\":\"mahfuz f\",\"phone_number\":\"8801956316264\"},{\"name\":\"+8801740040056\",\"phone_number\":\"8801740040056\"}]";

            low = high + 1;
            high = high + 98;
            if(high > arrayList_Android_Contacts.size()){
                high = arrayList_Android_Contacts.size();
            }
        }
*/


//        System.out.println("printing json........................... : "+json+"          ");


//        System.out.println("dslkjfslkfjlskdfjsldkf");
       /* TypeToken<String> token = new TypeToken<String>(){};
        System.out.println("dslkjfslkfjlskdfjsldkf  2");
//        String fromJson = new Gson().fromJson(arrayList_Android_Contacts, token.getType());
        String json = new Gson().toJson(arrayList_Android_Contacts, token.getRawType());*/
       /*Type contactType = new TypeToken<List<Android_Contact>>(){}.getType();

       String json  = new Gson().toJson(arrayList_Android_Contacts,contactType);
       String json2  = new Gson().toJson(androidContact,contactType2);
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(androidContact,contactType2);*/


        Type contactType2 = new TypeToken<List<PhoneNumber>>(){}.getType();
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(androidContact,contactType2);


       /* System.out.println(" printing json : "+json);
        System.out.println(" printing json : "+json2);
        System.out.println(" printing json pretty: "+prettyJson);*/
//        System.out.println("json :"+json);
//        String json3 = "[{\"name\":\"mahfuz f\",\"phone_number\":\"8801956316264\"},{\"name\":\"+8801740040056\",\"phone_number\":\"8801740040056\"}]";
        String json3 = "[{\"phone_number\":\"01956316264\"},{\"phone_number\":\"+8801740040056\"},{\"phone_number\":\"+8801629275761\"},{\"phone_number\":\"+8801738002830\"},{\"phone_number\":\"+880 1627-136253\"}]";
        System.out.println(" printing json pretty: "+prettyJson);
        params.put("contacts", prettyJson);
        methodTesting.getBlood(params);
        System.out.println("dslkjfslkfjlskdfjsldkf  4");
    }
}
