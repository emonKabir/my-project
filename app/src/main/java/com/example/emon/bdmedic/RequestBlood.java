package com.example.emon.bdmedic;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.PriceCalculator.PriceCalculator;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestBlood extends AppCompatActivity {

   public static String CHANNEL_ID = "channelId";
    MaterialSpinner sex,bloodGroup, districtSend, upazilaSend, districtSort, upazilaSort, SpinnerUserTypes;
    String CHANNEL_NAME = "notificationChnanel";
    String CHANNEL_DESC = "Notification";
    TextView sendButton,tokenText,bloodRequest;
    FirebaseAuth mAuth;
    String dFileName = "districts.json";
    String uFileName = "upazilas.json";
    String[] sample = {" "," "};
    String[] bgroup = {"O-","O+","A-", "A+","B-","B+", "AB-","AB+"};
    String token;
    String[]districts;
    String[] uListName;
    JSONArray m_jArry;
    EditText bodyMessage;
    String dstrctSort, dstrctSend, upazlaSort, upazlaSend, bldGroup;
    int  bGroupSelectedItem, dSortSelectedItem, dSendSelectedItem, uSortSelectedItem, uSendSelectedItem;
    NotificationResponse notificationResponse;
    private Toolbar toolbar;
    String mobileNumber;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_health);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);

        linearLayout = findViewById(R.id.linearRBlood);
        mAuth = FirebaseAuth.getInstance();

        //GET MOBILE NUMBER.
        SharedPreferences sharedPreferences = getSharedPreferences("userNumber", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("number")) {
             mobileNumber = sharedPreferences.getString("number", null);
        }

        bodyMessage = findViewById(R.id.bodyMessage);
        //FirebaseMessaging.getInstance().subscribeToTopic("blood");
      //  FirebaseInstanceId.getInstance()
       //tokenText = findViewById(R.id.tokenText);
        sendButton = findViewById(R.id.sendButton);
       // MyFirebaseInstanceIDService myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
        //myFirebaseInstanceIDService.onTokenRefresh();
      // final String rtkn =  myFirebaseInstanceIDService.refreshedToken.toString();

        bloodRequest = findViewById(R.id.bloodRequest);
        bloodGroup = findViewById(R.id.bloodGroup);
        districtSend = findViewById(R.id.districtSend);
        upazilaSend = findViewById(R.id.upazilaSend);
        districtSort = findViewById(R.id.districtSort);
        upazilaSort = findViewById(R.id.upazilaSort);
        spinnerAdap(bloodGroup, bgroup);
        spinnerAdap(upazilaSort,sample);
        spinnerAdap(upazilaSend,sample);



        getDistrictsList();

        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bGroupSelectedItem = bloodGroup.getSelectedItemPosition();
                bldGroup = String.valueOf(bGroupSelectedItem);


                //MedicineSubGroup.out.println("selectedItem : "+ selectedItem);
                // bldGroup = String.valueOf(bloodGroup.getSelectedItem());
                //MedicineSubGroup.out.println("USER NAME : "+bldGroup);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dSortSelectedItem = districtSort.getSelectedItemPosition();
                if(dSortSelectedItem != 0){
                    try {
                        JSONObject jo_inside = m_jArry.getJSONObject(dSortSelectedItem -1);
                        dstrctSort = jo_inside.getString("id");
                        System.out.println("district parmanent id : " + dstrctSort);
                        getParmanentUpazilasList(dstrctSort);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // getParmanentUpazilasList(dstrctSort);
                // dParmanent = String.valueOf(districtSort.getSelectedItem());
                // MedicineSubGroup.out.println("USER NAME : "+dParmanent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtSend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dSendSelectedItem = districtSend.getSelectedItemPosition();
                if(dSendSelectedItem != 0){
                    try {
                        JSONObject jo_inside = m_jArry.getJSONObject(dSendSelectedItem -1);
                        dstrctSend = jo_inside.getString("id");
                        System.out.println("district present id : " + dstrctSend);
                        getPresentUpazilasList(dstrctSend);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // dPresent = String.valueOf(districtSend.getSelectedItem());
                // MedicineSubGroup.out.println("USER NAME : "+dPresent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upazilaSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uSortSelectedItem = upazilaSort.getSelectedItemPosition();
                if(uSortSelectedItem != 0){
                    String selectedUpazila = (String) upazilaSort.getItemAtPosition(position);
                    upazlaSort =  getUpazilaId(selectedUpazila);
                    System.out.println("upazila sort id.........................  :"+upazlaSort);
                }
                //uParmanent = String.valueOf(upazilaSort.getSelectedItem());
                //MedicineSubGroup.out.println("USER NAME : "+uParmanent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upazilaSend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uSendSelectedItem = upazilaSend.getSelectedItemPosition();
                if(uSendSelectedItem != 0){
                    String selectedUpazila = (String) upazilaSend.getItemAtPosition(position);
                upazlaSend =  getUpazilaId(selectedUpazila);
                System.out.println("upazila send id.........................  :"+upazlaSend);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendNotification();

//                Toast.makeText(RequestBlood.this,"clicked",Toast.LENGTH_SHORT).show();
                System.out.println("1st");
                sendBloodRequest();


            }
        });

       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }*/

    }

   /* public void sendNotification(){



        NotificationCompat.Builder mBulider =  new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_003_blood)
                .setContentTitle("This is title")
                .setContentText("i got notification")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBulider.build());
    }*/

    public String loadJSONFromAsset( String fileName) {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

            //MedicineSubGroup.out.println(json);

            /*for(int i = 0; i < json.length(); i++)
            {
                MedicineSubGroup.out.println(" id : "+json);

            }*/


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.e("data", json);
        return json;

    }

    public void getDistrictsList(){

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(dFileName));
            m_jArry = obj.getJSONArray("districts");
            districts = new String[m_jArry.length()];
            for(int i = 0; i < m_jArry.length(); i++){
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                //String dId = jo_inside.getString("id");
                String dName = jo_inside.getString("name");
                districts[i] = dName;
                //MedicineSubGroup.out.println("dName : "+districts[i]);

            }
            spinnerAdap(districtSort,districts);
            spinnerAdap(districtSend,districts);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getParmanentUpazilasList(String dId){

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(uFileName));
            m_jArry = obj.getJSONArray("upazilas");
            uListName = new String[m_jArry.length()];
            //uListName = new String[10];
            int j = 0;
            for(int i = 0; i < m_jArry.length(); i++){
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String uId = jo_inside.getString("id");
                String uName = jo_inside.getString("name");
                String uDId = jo_inside.getString("district_id");
                if( dId.equals(uDId))
                {

                    uListName[j] = uName;
                    //MedicineSubGroup.out.println("districtID : "+uDId);
                    //MedicineSubGroup.out.println("upazila name : "+uListName[j]);
                    j++;
                }

                // uListName[i] = uName;
                //MedicineSubGroup.out.println("upazila name : "+uName);

            }
            String[] UpazilaName = new String[j];
            //MedicineSubGroup.out.println("size of uListName : "+ uListName.length);
            //MedicineSubGroup.out.println("size of UpazilaName : "+ UpazilaName.length);
            for(int k = 0; k < j ; k++)
            {
                UpazilaName[k] = uListName[k];
                //MedicineSubGroup.out.println("UpazilaName : "+UpazilaName[k]);

            }
            spinnerAdap(upazilaSort,UpazilaName);
            // spinnerAdap(upazilaSend,UpazilaName);

            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, uListName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            upazilaSend.setAdapter(adapter);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getPresentUpazilasList(String dId){

        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset(uFileName));
            m_jArry = obj.getJSONArray("upazilas");
            uListName = new String[m_jArry.length()];
            //uListName = new String[10];
            int j = 0;
            for(int i = 0; i < m_jArry.length(); i++){
                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                jo_inside = m_jArry.
                String uId = jo_inside.getString("id");
                String uName = jo_inside.getString("name");
                String uDId = jo_inside.getString("district_id");
               /* System.out.println("upazila send id.............................. : "+uId);
                System.out.println("upazila send name.............................. : "+uName);
                System.out.println("upazila send district.............................. : "+uDId);*/
                if( dId.equals(uDId))
                {

                    uListName[j] = uName;
                    //MedicineSubGroup.out.println("districtID : "+uDId);
                    //MedicineSubGroup.out.println("upazila name : "+uListName[j]);
                    j++;
                }

                // uListName[i] = uName;
                //MedicineSubGroup.out.println("upazila name : "+uName);

            }
            String[] UpazilaName = new String[j];
            //  MedicineSubGroup.out.println("size of uListName : "+ uListName.length);
            //MedicineSubGroup.out.println("size of UpazilaName : "+ UpazilaName.length);
            for(int k = 0; k < j ; k++)
            {
                UpazilaName[k] = uListName[k];
                //  MedicineSubGroup.out.println("UpazilaName : "+UpazilaName[k]);

            }
            //spinnerAdap(upazilaSort,UpazilaName);
            spinnerAdap(upazilaSend,UpazilaName);

            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, uListName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            upazilaSend.setAdapter(adapter);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void  spinnerAdap(MaterialSpinner mSpinner, String[] sArray){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

    }

    private void sendBloodRequest() {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);


        Call<NotificationResponse> bloodRequ = apiService.postBloodRequest(bldGroup.trim(),
                bodyMessage.getText().toString().trim(),
                dstrctSend.trim(), dstrctSort.trim(),
                upazlaSend.trim(), upazlaSort.trim(),
                mobileNumber
                );

        bloodRequ.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {

                if(response.body().getStatus() == 1)
                {
//                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(linearLayout,response.body().getMessage(),Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });





    }

    private String getUpazilaId(String upazilaName){

        String uName,upazilaId = null;
        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset(uFileName));
            m_jArry = obj.getJSONArray("upazilas");
            for(int i = 0; i < m_jArry.length(); i++){
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                uName = jo_inside.getString("name");

                if( upazilaName.equals(uName))
                {

                    upazilaId =  jo_inside.getString("id");
                    break;

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return upazilaId;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(RequestBlood.this, BottomMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
