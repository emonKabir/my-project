package com.example.emon.bdmedic.RetrofitHelper;

import com.example.emon.bdmedic.Fragments.DisciplineData;
import com.example.emon.bdmedic.Fragments.DiseaseManageData;

import java.util.List;

public interface CallbackInterface {

     void onData(List<DiseaseManageData>list);

}
