package com.example.emon.bdmedic;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;

import com.example.emon.bdmedic.ContactList.PojoClass.PhoneNumber;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.emon.bdmedic.ContactList.Android_Contact;
import com.example.emon.bdmedic.ContactList.BloodGroup;
import com.example.emon.bdmedic.Login.LoginVerify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView  accountCreate;
    Button connect;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private  String mverificationId;
    List<LoginVerify>loginVerifyList;
    LoginVerify loginVerify;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    public   List<Android_Contact> arrayList_Android_Contacts;
    List<PhoneNumber>phoneNumberArrayList;
    public List<PhoneNumber>androidContact;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
    EditText editTextOtpCode;
    Button buttonVerify;
    LinearLayout verificationLayoutForTesting;
    LinearLayout layoutMain;
    String newToken;
    RegisterActivity registerActivity;
    MethodTesting methodTesting;
    BloodGroup bloodGroup;
    Map<String,String> contactMap;
    String token;
    Map<String,String> params;
RelativeLayout loginRelative;
public FirebaseAuth mAuth;
EditText phone;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                System.out.println("new token................................................  : "+token);
                Log.d("newToken",token);

            }
        });




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_testing);
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        contactMap = new HashMap<>();
        methodTesting = new MethodTesting(LoginActivity.this);
        layoutMain = findViewById(R.id.layoutMain);
        verificationLayoutForTesting = findViewById(R.id.verificationLayoutLogIn);
        editTextOtpCode = findViewById(R.id.editTextOtpCode);
        buttonVerify = findViewById(R.id.buttonVerify);
        connect = findViewById(R.id.connect);
        phone = findViewById(R.id.phoneNumber);
        accountCreate = findViewById(R.id.accountCreate);
        mAuth = FirebaseAuth.getInstance();
        arrayList_Android_Contacts = new ArrayList<>();
        androidContact = new ArrayList<>();
        myDatabase = new MyDatabase(this);
//        myDatabase.getWritableDatabase();
        connect.setOnClickListener(this);
        buttonVerify.setOnClickListener(this);
        accountCreate.setOnClickListener(this);
        bloodGroup = new BloodGroup();
        phoneNumberArrayList = new ArrayList<>();
        params = new HashMap<String, String>();
        loginRelative = findViewById(R.id.loginRelative);
//        String json2 = "[{\"name\":\"mahfuz f\",\"phone_number\":\"01956316264\"}]";

//        JSONStringer jsonStringer = json2;


//        methodTesting.getBlood(json2);


        new LoadContacts().execute();

        System.out.println("starting.....");

//        showContacts();



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                String code = phoneAuthCredential.getSmsCode();
                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
                if (code != null) {
                    editTextOtpCode.setText(code);
                    //verifying the code
                    verifyVerificationCode(code);
                }else{
                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                System.out.println(".........................68888888888888888888888886");
                Toast.makeText(LoginActivity.this,"Verification faild",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Save the verification id somewhere
                // ...
                System.out.println(".........................6666666666666666666666666669999999999999999999999999");

                mverificationId = verificationId;
                System.out.println(".........................66666666666666666666666666699999999999999999999999991111111");
                mResendToken = token;
                System.out.println(".........................6666666666666666666666666669999999999999999999999999");
                Toast.makeText(LoginActivity.this,"A verification code is send to your mobile",Toast.LENGTH_SHORT).show();
               /* System.out.println(".........................6666666666666666666666666669999999999999999999999999222222222222");
                mNumber.setEnabled(false);
                System.out.println(".........................66666666666666666666666666699999999999999999999999993333");
                fName.setEnabled(false);
                System.out.println(".........................6666666666666666666666666669999999999999999999999999444444444");
                sex.setEnabled(false);
                System.out.println(".........................6666666666666666666666666669999999999999999999999999555555555");
                bloodGroup.setEnabled(false);
                System.out.println(".........................6666666666666666666666666669999999999999999999999999666666666");
                districtParmanent.setEnabled(false);
                districtPresent.setEnabled(false);
                upazilaParmanent.setEnabled(false);
                upazilaPresent.setEnabled(false);
//                SpinnerUserTypes.setEnabled(false);
                signIn.setEnabled(false);
                System.out.println(".........................66666666666666666666666666444444444444444444444446");*/


            }

        };


        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    System.out.println("print something");
                    hideKeyboard(v);
                }
            }
        });

    }



    public void hideKeyboard(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.accountCreate:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.connect:
//                setLoginVerify();
               /* layoutMain.setVisibility(View.GONE);
                verificationLayoutForTesting.setVisibility(View.VISIBLE);
                resendVerificationCode(phone.getText().toString(),mResendToken);*/
               setLoginVerify();
                break;
            case R.id.buttonVerify:
                String code = editTextOtpCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextOtpCode.setError("Enter valid code");
                    editTextOtpCode.requestFocus();
                }
                verifyVerificationCode(code);
                break;

        }

    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
       String  phoneNum ="+88"+phoneNumber;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNum,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();
                            }
//                            FirebaseUser currentUser = mAuth.getCurrentUser();
//                            currentUser = mAuth.getCurrentUser();

                            setSharedPreference(true);
                            setMobileNumber(phone.getText().toString());
                            Intent intent = new Intent(LoginActivity.this,BottomMenu.class);
                            startActivity(intent);

                            // ...
                        } else {
                            // Sign in failed, display a body and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                               /* Toast toast = Toast.makeText(LoginActivity.this,"invalid OTP",Toast.LENGTH_SHORT);
                                toast.show();*/
                            }
                        }
                    }
                });
    }

    public void setSharedPreference(boolean state){
        SharedPreferences sharedPreferences = getSharedPreferences("logInTracker", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",state);
        editor.apply();
    }

    public void setMobileNumber(String number){
        SharedPreferences sharedPreferences = getSharedPreferences("userNumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number","+88"+number);
        editor.apply();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                new LoadContacts().execute();
            } else {
                Toast.makeText(LoginActivity.this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
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
        ContentResolver contentResolver = getContentResolver();
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

    private class LoadContacts extends AsyncTask<Void,Void, Boolean> {

        ProgressDialog progressDialog;
//        MyDBHelper myDBHelper = new MyDBHelper(LoginActivity.this);
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            System.out.println("enter in the excute system................................");

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
                /*hideLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);*/
//                progressDialog.dismiss();
//                circularProgressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(),"Please wait until slider slide again!!!",Toast.LENGTH_LONG).show();
            }


        };
    }

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationId, code);
        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private JsonObject ApiJsonMap() {

        JsonObject gsonObject = new JsonObject();
        JSONObject jsonObj_ = new JSONObject();
        JsonParser jsonParser = new JsonParser();
        for(int i = 0; i < arrayList_Android_Contacts.size(); i++){


            try {

                jsonObj_.put("name", arrayList_Android_Contacts.get(i).getName());
                jsonObj_.put("phone_number", arrayList_Android_Contacts.get(i).getPhone_number());


                gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

                //print parameter
                Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return gsonObject;
    }

    private void setLoginVerify() {


        if(isOnline()){


            System.out.println("enter in log in verify.................");
            ApiInterface apiService =
                    Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);
            System.out.println("enter in log in verify.................");
            final Call<LoginVerify> loginVerifyCall = apiService.loginVerify(phone.getText().toString(),token);
            System.out.println("enter in log in verify.................");
            loginVerifyCall.enqueue(new Callback<LoginVerify>() {
                @Override
                public void onResponse(Call<LoginVerify> call, Response<LoginVerify> response) {

                    if (response.isSuccessful()){
                        System.out.println("enter in log in verify   1.................");
                        loginVerify = response.body();
                        if(loginVerify.getStatus() == 1){

                            layoutMain.setVisibility(View.GONE);
                            verificationLayoutForTesting.setVisibility(View.VISIBLE);
                            resendVerificationCode(phone.getText().toString(),mResendToken);
//                        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                        startActivity(intent);
                        }else if(loginVerify.getStatus() == 0){
                            System.out.println("enter in log in verify  000.................");

                            Toast.makeText(LoginActivity.this,"you haven't any account.create a new one!",Toast.LENGTH_SHORT).show();

                        }
                    }

                    if (!response.isSuccessful()){
                        System.out.println("not successfufll");
                    }
                }

                @Override
                public void onFailure(Call<LoginVerify> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    Log.e("failureOnLoginVerify",t.getMessage());

                }
            });



        }else{

            Snackbar snackbar = Snackbar.make(loginRelative,"please check your internet connection",Snackbar.LENGTH_LONG);
            snackbar.show();
        }







    }

    public boolean isOnline(){



        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}



