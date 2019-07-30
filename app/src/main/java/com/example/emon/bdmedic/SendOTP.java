package com.example.emon.bdmedic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTP extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText mobileNumber,name,email;
    TextView OTP,sendSMS,message;
    private  String mverificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        mobileNumber = findViewById(R.id.mobileNumber);
        OTP = findViewById(R.id.OTP);
        message = findViewById(R.id.message);
        sendSMS = findViewById(R.id.sendSMS);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();

        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber ="+88"+mobileNumber.getText().toString();

                OTP.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        SendOTP.this,   // Activity (for callback binding)
                       mCallbacks
                );
            }




        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                message.setText("login failed");
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Save the verification id somewhere
                // ...
                mverificationId = verificationId;
                mResendToken = token;
                mobileNumber.setEnabled(false);
                name.setEnabled(false);
                email.setEnabled(false);
                sendSMS.setEnabled(false);
                sendSMS.setText("Verify");
                message.setText("A verification code send to your mobile");

                message.setVisibility(View.VISIBLE);


            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
      /*  if(currentUser != null)
        {
            Intent intent = new Intent(SendOTP.this,BottomMenu.class);
            startActivity(intent);

        }*/



    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = task.getResult().getUser();
                            Toast toast = Toast.makeText(SendOTP.this,"Registration completed",Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(SendOTP.this,BottomMenu.class);
                            startActivity(intent);
                            // ...
                        } else {
                            // Sign in failed, display a body and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast toast = Toast.makeText(SendOTP.this,"invalid OTP",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }
                });
    }
}
