package com.example.emon.bdmedic.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emon.bdmedic.PriceCalculator.Medicine;
import com.example.emon.bdmedic.R;


public class Header extends Fragment implements View.OnClickListener {

    Fragment selectedFragment;
    TextView medicineTxtview,surgeryTxtview,gynacologyTxtview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_header, container, false);
        Intent intent = getActivity().getIntent();
//        String clkd = intent.getStringExtra("clickButton");
        medicineTxtview = v.findViewById(R.id.medicineTxtview);
        surgeryTxtview = v.findViewById(R.id.surgeryTxtview);
        gynacologyTxtview = v.findViewById(R.id.gynacologyTxtview);

        medicineTxtview.setOnClickListener(this);
        surgeryTxtview.setOnClickListener(this);
        gynacologyTxtview.setOnClickListener(this);

       /* Fragment fragment = new MedicineFragment();
        FragmentManager childFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.recentHeader, fragment);
        ft.commit();*/


        String value = getArguments().getString("clickButton");
        if(value.equals("1")){

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            ft.replace(R.id.recentHeader, new MedicineFragment());
            ft.commit();

        }else if(value.equals("2")){

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            ft.replace(R.id.recentHeader, new SurgeryFragment());
            ft.commit();

        }else if(value.equals("3")){

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            ft.replace(R.id.recentHeader, new GynacologyFragment());
            ft.commit();

        }

        return v;
    }

    @Override
    public void onClick(View v) {

        selectedFragment = null;
        switch (v.getId()){


            case R.id.medicineTxtview:
                selectedFragment =  new MedicineFragment();
               /* medicineTxtview.setPaintFlags(matched.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                global.setPaintFlags(0);*/
                break;
            case R.id.surgeryTxtview:
                selectedFragment =  new SurgeryFragment();
               /* global.setPaintFlags(global.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                matched.setPaintFlags(0);*/
                break;
            case R.id.gynacologyTxtview:
                selectedFragment =  new GynacologyFragment();
               /* global.setPaintFlags(global.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                matched.setPaintFlags(0);*/
                break;

        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.recentHeader, selectedFragment);
        ft.commit();

    }
}
