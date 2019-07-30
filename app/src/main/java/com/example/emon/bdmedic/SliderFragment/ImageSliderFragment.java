package com.example.emon.bdmedic.SliderFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.emon.bdmedic.R;

public class ImageSliderFragment extends Fragment {


    private int[] imageInt = new int[]{R.drawable.m4,R.drawable.m6};
    ViewFlipper v_flipper;
    ImageView leftArrow,rightArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_image_slider, container, false);

        v_flipper = v.findViewById(R.id.v_flipperCamera);
        leftArrow = v.findViewById(R.id.leftArrow);
        rightArrow = v.findViewById(R.id.rightArrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v_flipper.showPrevious();
                v_flipper.setFlipInterval(2000);



            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_flipper.showNext();
                v_flipper.setFlipInterval(2000);

            }
        });


        for(int image:imageInt){

            slider(image);
        }
        return v;
    }

    public void slider(int image){

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
        v_flipper.setAutoStart(true);

    }


}
