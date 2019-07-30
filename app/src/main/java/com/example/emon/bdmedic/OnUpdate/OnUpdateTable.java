package com.example.emon.bdmedic.OnUpdate;

import android.app.Activity;

import com.example.emon.bdmedic.MethodTesting;
import com.example.emon.bdmedic.UpdateHistory.UpdateTableData;

import java.util.List;

public class OnUpdateTable {
     Activity activity;
    MethodTesting methodTesting = new MethodTesting(activity);

   /* public OnUpdateTable(Activity activity) {
        this.activity = activity;
    }*/
    public void OnUpdateTables(List<UpdateTableData>tableList){
        for(int i = 0; i < tableList.size(); i++){

            switch (tableList.get(i).getTableName()){

                case "branches":
                    System.out.println("data updating.......................... : "+tableList.get(i).getTableName());
//                    methodTesting.getBranch();
                    break;
            }
        }

    }
}
