package com.example.emon.bdmedic.SliderFragment;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.example.emon.bdmedic.MenuFeed;
import com.example.emon.bdmedic.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.emon.bdmedic.R.*;


public class VideoSliderFragment extends Fragment implements View.OnClickListener {



    private int[] imageInt = new int[]{drawable.video_cover, drawable.video_cover3};
    ViewFlipper v_flipper;
FrameLayout frameLayout2;
AdapterViewFlipper adapterViewFlipper;
VideoView videoView;
MenuFeed menuFeed;
ProgressDialog progressDialog;
String video_url ="http://file2.video9.in/english/movie/2014/x-men-_days_of_future_past/X-Men-%20Days%20of%20Future%20Past%20Trailer%20-%20[Webmusic.IN].3gp";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video_slider, container, false);

        v_flipper = v.findViewById(R.id.viewFlipperVideo);
        frameLayout2 = v.findViewById(R.id.videoFrameLayout);
        videoView = v.findViewById(id.videoView2);
       /* adapterViewFlipper= v.findViewById(R.id.adapterVideiFlipper);
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity(), imageInt);
        adapterViewFlipper.setAdapter(sliderAdapter);
        adapterViewFlipper.setFlipInterval(3000);
        adapterViewFlipper.startFlipping();*/

//        adapterViewFlipper.setAutoStart(true);
        /*adapterViewFlipper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item = (int) adapterViewFlipper.getSelectedItem();
                System.out.println("get item id :"+item);
            }
        });*/

        v_flipper.setOnClickListener(this);
        for(int image:imageInt){

            slider(image);
        }

        return v;
    }

    int i= 1;
    public void slider(int image){

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setDisplayedChild(i);
        v_flipper.setFlipInterval(4000);
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
        v_flipper.setAutoStart(true);
        i++;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case id.viewFlipperVideo:
//                onButtonShowPopupWindowClick(frameLayout2);
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Buffering video please wait...");
                progressDialog.show();
                    videoView.setVisibility(View.VISIBLE);
//                    videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName()+"/"+ R.raw.prem));
                    videoView.setVideoURI(Uri.parse(video_url));
                    videoView.start();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mp) {
                                                        //close the progress dialog when buffering is done
                                                        progressDialog.dismiss();
                                                    }
                });


               /* videoView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        videoView.stopPlayback();
                        return true;
                    }
                });*/

               break;
        }
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//        VideoView videoView = popupView.findViewById(R.id.videoView);
//        videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName()+"/"+ R.raw.heavy));
//        videoView.start();
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        // dismiss the popup window when touched
       /* popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });*/
    }


}
