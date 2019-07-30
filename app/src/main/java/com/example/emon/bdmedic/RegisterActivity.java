package com.example.emon.bdmedic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emon.bdmedic.Login.LoginVerify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.iid.FirebaseInstanceId.getInstance;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    String MY_PREFS_NAME = "Token";
    public FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private  String mverificationId;
    LoginVerify loginVerify;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    MaterialSpinner sex,bloodGroup,districtPresent,upazilaPresent,districtParmanent,upazilaParmanent, SpinnerUserTypes;
    TextView personalInfo,presentAddress,parmanentAddress,backToLogin;
    EditText mNumber,fName;
    Button signIn;
    String[] sample = {" "," "};
    String[] bgroup = {"O-","O+","A-", "A+","B-","B+", "AB-","AB+"};
    String[] Sex = {"Male", "Female"};
    String dFileName = "districts.json";
    String uFileName = "upazilas.json";
    String[]districts;
    String[] uListName;
    JSONArray m_jArry;
    String userToken;
    String token = "testToken";
    String newToken;
    String idUserType,dParmanentId,dPresentId,uParmanentId,uPresentId,sexId,bGroup,registrationType;
  // HashMap<Integer,Integer> spinnerMap = new HashMap<Integer, Integer>();
    List<UserTypes.Datum> data;
    //String sSex, uPresent,uParmanent,dPresent,dParmanent,bldGroup;
    int idUT, bGroupSelectedItem,userTypeSelectedItem,dParmanentSelectedItem,dPresentSelectedItem,uParmanentSelectedItem,uPresentSelectedItem,sSexSelectedItem;
    SignUpResponse signUpResponsesData;
EditText editTextCode;
Button buttonSignIn;
LinearLayout verificationLayout;
LinearLayout restLayout;

    private Toolbar toolbar;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(RegisterActivity.this,  new OnSuccessListener<InstanceIdResult>() {
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
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        presentAddress = findViewById(R.id.presentAddress);
        parmanentAddress = findViewById(R.id.parmanentAddress);
        bloodGroup = findViewById(R.id.bloodGroup);
        districtPresent = findViewById(R.id.districtSend);
        upazilaPresent = findViewById(R.id.upazilaSend);
        districtParmanent = findViewById(R.id.districtSort);
        upazilaParmanent = findViewById(R.id.upazilaSort);
        signIn = findViewById(R.id.signIn);
        sex = findViewById(R.id.spinnerSex);
        editTextCode = findViewById(R.id.editTextCode);
        backToLogin = findViewById(R.id.backToLogin);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        verificationLayout = findViewById(R.id.verificationLayout);
        restLayout = findViewById(R.id.restlayout);
//        SpinnerUserTypes = findViewById(R.id.userType);
        mNumber = findViewById(R.id.mobileNumber);
        fName = findViewById(R.id.fullName);
      /*  medical = findViewById(R.id.medical);
        dental = findViewById(R.id.dental);
*/

      backToLogin.setOnClickListener(this);
      signIn.setOnClickListener(this);
      buttonSignIn.setOnClickListener(this);
//         token = getInstance().getToken($SENDER_ID, "FCM");


       /* SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", "");
        System.out.println("restore text   : "+restoredText);*/

        paint(presentAddress);
        paint(parmanentAddress);



        spinnerAdap(bloodGroup, bgroup);
        spinnerAdap(upazilaParmanent,sample);
        spinnerAdap(upazilaPresent,sample);
        spinnerAdap(sex,Sex);

        //getDataFromJson
//        getUserListData();


        getDistrictsList();

       // MedicineSubGroup.out.println("checked button : "+registrationType);


        //toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);

        

        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 bGroupSelectedItem = bloodGroup.getSelectedItemPosition();
                 bGroup = String.valueOf(bGroupSelectedItem);
                System.out.println("blood Group id : "+ bGroup);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtParmanent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dParmanentSelectedItem = districtParmanent.getSelectedItemPosition();
                if(dParmanentSelectedItem != 0){
                    try {
                        JSONObject jo_inside = m_jArry.getJSONObject(dParmanentSelectedItem-1);
                        dParmanentId = jo_inside.getString("id");
                        System.out.println("district parmanent id : " + dParmanentId);
                        getParmanentUpazilasList(dParmanentId);
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

        districtPresent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dPresentSelectedItem = districtPresent.getSelectedItemPosition();
                if(dPresentSelectedItem != 0){
                    try {
                        JSONObject jo_inside = m_jArry.getJSONObject(dPresentSelectedItem-1);
                        dPresentId  = jo_inside.getString("id");
                        System.out.println("district present id : " + dPresentId);
                        getPresentUpazilasList(dPresentId);
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

        upazilaParmanent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uParmanentSelectedItem = upazilaParmanent.getSelectedItemPosition();
                if(uParmanentSelectedItem != 0){

                    String selectedUpazila = (String) upazilaParmanent.getItemAtPosition(position);
                    uParmanentId = getUpazilaId(selectedUpazila);
                   /* try {
                        JSONObject jo_inside = m_jArry.getJSONObject(uParmanentSelectedItem-1);
                         uParmanentId = jo_inside.getString("id");

                        System.out.println("upazila parmanent id : " + uParmanentId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                }
                //uParmanent = String.valueOf(upazilaSort.getSelectedItem());
                //MedicineSubGroup.out.println("USER NAME : "+uParmanent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upazilaPresent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                uPresentSelectedItem = upazilaPresent.getSelectedItemPosition();
                if(uPresentSelectedItem != 0){
                    /*try {
                        JSONObject jo_inside = m_jArry.getJSONObject(uPresentSelectedItem-1);
                         uPresentId = jo_inside.getString("id");
                        System.out.println("upazila present id : " + uPresentId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                    String selectedUpazila = (String) upazilaPresent.getItemAtPosition(position);
                    uPresentId = getUpazilaId(selectedUpazila);
                    System.out.println("upazila present : "+uPresentId);

                }
               // uPresent = String.valueOf(upazilaSend.getSelectedItem());
                //MedicineSubGroup.out.println("USER NAME : "+uPresent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sSexSelectedItem = sex.getSelectedItemPosition();

                if (sSexSelectedItem != 0){

                    String Sex = sex.getItemAtPosition(position).toString();
                    if(Sex.equals("Male"))  sexId = "M";
                    else sexId = "F";
                }



//                = String.valueOf(sSexSelectedItem);
                //sSex = String.valueOf(sex.getSelectedItem());
                //MedicineSubGroup.out.println("USER NAME : "+sSex);
                System.out.println("sex id : " + sexId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                System.out.println(".........................666666666666666666666666666");
                String code = phoneAuthCredential.getSmsCode();
                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
                if (code != null) {
                    editTextCode.setText(code);
                    //verifying the code
                    verifyVerificationCode(code);
                }else{

                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }

                System.out.println(".........................7777777777777777777777777777");
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                System.out.println(".........................68888888888888888888888886");
               Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RegisterActivity.this,"A verification code is send to your mobile",Toast.LENGTH_SHORT).show();
                System.out.println(".........................6666666666666666666666666669999999999999999999999999222222222222");
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
                System.out.println(".........................66666666666666666666666666444444444444444444444446");


            }

        };




        mNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        fName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    System.out.println("print from registration");
                    hideKeyboard(v);
                }
            }
        });
    }



    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationId, code);
        //signing the user
        signInWithPhoneAuthCredential(credential);
    }


    public void paint(TextView text)
    {

        text.setPaintFlags(text.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
    }


    public void  spinnerAdap(MaterialSpinner mSpinner, String[] sArray){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

    }



    private void signUp() {


        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);

        System.out.println("..............................................111111111111111111111 : "+fName.getText().toString().trim());

//        System.out.println("..............................................111111111111111111111 : "+token.trim());
//        token = "token";
        Call<SignUpResponse> callReg = apiService.registration(mNumber.getText().toString().trim(),fName.getText().toString().trim(),sexId.trim(),bGroup.trim(),dPresentId.trim(),dParmanentId.trim(),uPresentId.trim(),uParmanentId.trim(),token.trim());
        System.out.println("..............................................222222222222222222222222222222");


        callReg.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                signUpResponsesData = response.body();
                if(response.isSuccessful()){

                    if(response.body().getMessage().equals("success")){

                        Toast.makeText(RegisterActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        String phoneNumber ="+88"+mNumber.getText().toString();
                        restLayout.setVisibility(View.GONE);
                        verificationLayout.setVisibility(View.VISIBLE);
                        getOtp(phoneNumber);
                    }
                }


            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("response", t.getStackTrace().toString());
//                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });


    }

    private boolean validate() {
        // check the lenght of the enter data in EditText and give error if its empty
        if (bGroupSelectedItem > 0  && dParmanentSelectedItem > 0 && dPresentSelectedItem > 0 && uParmanentSelectedItem > 0 && uPresentSelectedItem > 0 && sSexSelectedItem > 0) {
            return true; // returns true if field is not empty
        }

        return false;
    }
    private boolean validateMobileNumber(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() == 11) {
            return true; // returns true if field is not empty
        }
        editText.setError("Number is incorrect");
        editText.requestFocus();
        return false;
    }

    private boolean validateFullName(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }



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
          spinnerAdap(districtParmanent,districts);
          spinnerAdap(districtPresent,districts);
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
        spinnerAdap(upazilaParmanent,UpazilaName);
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
          //  MedicineSubGroup.out.println("size of uListName : "+ uListName.length);
            //MedicineSubGroup.out.println("size of UpazilaName : "+ UpazilaName.length);
            for(int k = 0; k < j ; k++)
            {
                UpazilaName[k] = uListName[k];
              //  MedicineSubGroup.out.println("UpazilaName : "+UpazilaName[k]);

            }
            //spinnerAdap(upazilaSort,UpazilaName);
            spinnerAdap(upazilaPresent,UpazilaName);

            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, uListName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            upazilaSend.setAdapter(adapter);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

                            Toast toast = Toast.makeText(RegisterActivity.this,"Registration completed",Toast.LENGTH_SHORT);
                            toast.show();
                            setSharedPreference();
                            setMobileNumber(mNumber.getText().toString());
                            Intent intent = new Intent(RegisterActivity.this,BottomMenu.class);
                            startActivity(intent);

                            // ...
                        } else {
                            // Sign in failed, display a body and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                               /* Toast toast = Toast.makeText(RegisterActivity.this,"invalid OTP",Toast.LENGTH_SHORT);
                                toast.show();*/
                            }
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signIn:
//                Toast.makeText(RegisterActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                if(validate() && validateMobileNumber(mNumber) && validateFullName(fName))
                {
                    //MedicineSubGroup.out.println("enter signup");

                    System.out.println("...............................enter in");

                    setLoginVerify();
                    /*restLayout.setVisibility(View.GONE);
                    verificationLayout.setVisibility(View.VISIBLE);
                    String phoneNumber ="+88"+mNumber.getText().toString();
                    getOtp(phoneNumber);*/
//                    resendVerificationCode(mNumber.getText().toString(),mResendToken);


                }else{

                    Toast.makeText(RegisterActivity.this,"please provide all information!",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.backToLogin:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonSignIn:
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                }
                verifyVerificationCode(code);
                break;
        }
    }



    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    public void setSharedPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences("logInTracker", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",true);
        editor.apply();
    }

    public void setMobileNumber(String number){

        SharedPreferences sharedPreferences = getSharedPreferences("userNumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number","+88"+number);
        editor.apply();
    }

    private void getOtp(String phoneNumber){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,   // Activity (for callback binding)
                mCallbacks
        );
    }


    private void setLoginVerify() {


        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("please wait");
        progressDialog.show();
        System.out.println("enter in log in verify.................");
        ApiInterface apiService =
                Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);
        System.out.println("enter in log in verify.................");
        final Call<LoginVerify> loginVerifyCall = apiService.loginVerify(mNumber.getText().toString(),token);
        System.out.println("enter in log in verify.................");
        loginVerifyCall.enqueue(new Callback<LoginVerify>() {
            @Override
            public void onResponse(Call<LoginVerify> call, Response<LoginVerify> response) {

                if (response.isSuccessful()){
                    System.out.println("enter in log in verify   1.................");
                    loginVerify = response.body();
                    progressDialog.dismiss();
                    if(loginVerify.getStatus() == 1){
                        Toast.makeText(RegisterActivity.this,"you have already an account!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else if(loginVerify.getStatus() == 0){
                        System.out.println("enter in log in verify  000.................");

                        signUp();

                    }
                }

                if (!response.isSuccessful()){
                    System.out.println("not successfufll");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginVerify> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("failureOnLoginVerify",t.getMessage());
                progressDialog.dismiss();

            }
        });




    }

    public void hideKeyboard(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
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
}
