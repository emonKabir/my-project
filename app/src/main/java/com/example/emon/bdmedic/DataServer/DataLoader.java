package com.example.emon.bdmedic.DataServer;

import com.example.emon.bdmedic.Api;
import com.example.emon.bdmedic.ApiInterface;
import com.example.emon.bdmedic.Fragments.Branch;

import retrofit2.Callback;

public class DataLoader {

    ApiInterface apiService =
            Api.getClient(ApiInterface.BASE_URL).create(ApiInterface.class);

    /*public void getBranchData(Callback<Branch>branchCallback){
        apiService.getBranch().enqueue(branchCallback);


    }*/


}
