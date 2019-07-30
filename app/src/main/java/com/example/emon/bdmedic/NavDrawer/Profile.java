package com.example.emon.bdmedic.NavDrawer;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.LocalDatabase.MyDatabase;
import com.example.emon.bdmedic.R;

import java.io.IOException;

public class Profile extends AppCompatActivity {
    View internLayout,doctorLayout,medicalStudentLayout;
//    MyDBHelper myDBHelper;
    MyDatabase myDatabase;
   static WebView webview;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    RelativeLayout relativeLayout;




    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(this, "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        relativeLayout = findViewById(R.id.relativeLayout1);
       /* internLayout=findViewById(R.id.intern);
        doctorLayout=findViewById(R.id.doctor);*/
      /*  medicalStudentLayout=findViewById(R.id.medical_student);



        Spinner dropdown = findViewById(R.id.spinner1);*/
//      myDBHelper = new MyDBHelper(this);
      myDatabase = new MyDatabase(this);
      webview = findViewById(R.id.webviewD);
//      getProfile();


        if(isOnline()){

//            progressBar.setVisibility(View.VISIBLE);
            WebSettings mWebSettings = webview.getSettings();
            mWebSettings.setJavaScriptEnabled(true);

            webview.setWebViewClient(new WebViewClient());
            System.out.println("id of user.............. : "+getUserId());
            mWebSettings.setSupportZoom(false);
            mWebSettings.setAllowFileAccess(true);
            mWebSettings.setAllowContentAccess(true);
            webview.loadUrl("http://bdmedics.com/appUsers/app_edit/"+getUserId()+"");
            webview.setWebChromeClient(new WebChromeClient()
            {
                // For 3.0+ Devices (Start)
                // onActivityResult attached before constructor
                protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
                {
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
                }


                // For Lollipop 5.0+ Devices
                public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
                {
                    if (uploadMessage != null) {
                        uploadMessage.onReceiveValue(null);
                        uploadMessage = null;
                    }

                    uploadMessage = filePathCallback;

                    Intent intent = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        intent = fileChooserParams.createIntent();
                    }
                    try
                    {
                        startActivityForResult(intent, REQUEST_SELECT_FILE);
                    } catch (ActivityNotFoundException e)
                    {
                        uploadMessage = null;
                        Toast.makeText(Profile.this, "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                        return false;
                    }
                    return true;
                }

                //For Android 4.1 only
                protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
                {
                    mUploadMessage = uploadMsg;
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
                }

                protected void openFileChooser(ValueCallback<Uri> uploadMsg)
                {
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
                }
            });





        }else{
            Snackbar snackbar = Snackbar
                    .make(relativeLayout, "Something went wrong!!!", Snackbar.LENGTH_LONG);
            snackbar.show();


        }




    }

    private int getUserId(){

        int userType = 0;
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        String query = "select id from user_info";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor != null && cursor.moveToFirst()){

            do{

                userType = cursor.getInt(cursor.getColumnIndex("id"));
                System.out.println("user type : "+userType);
            }while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();

        /*Intent intent = new Intent(WebViewTable.this,DoctorProfile.class);
        intent.putExtra("array",names);*/


        return userType;
    }

    public boolean isOnline(){

       /* Runtime runtime = Runtime.getRuntime();
        try{
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e){e.printStackTrace();}
        catch(InterruptedException e){e.printStackTrace();}

        return false;*/

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

    private void getProfile(){


    }
}
