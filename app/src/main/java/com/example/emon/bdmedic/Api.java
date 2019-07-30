package com.example.emon.bdmedic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AbhiAndroid
 */
public class Api {
    private static Retrofit retrofit = null;


    public static Retrofit getClient(String base_url) {


        Gson gson = new GsonBuilder().setLenient().create();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();



        // change your base URL
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        //Creating object for our interface

        return retrofit;
    }

}
