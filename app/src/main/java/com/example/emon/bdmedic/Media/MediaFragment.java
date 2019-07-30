package com.example.emon.bdmedic.Media;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.example.emon.bdmedic.Api;
import com.example.emon.bdmedic.ApiInterface;
import com.example.emon.bdmedic.Laboratory.PojoClass.SubPharmacology;
import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.Media.MediaPojoClass.Media;
import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.MenuFeed;
import com.example.emon.bdmedic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MediaFragment extends Fragment {

    private int[] imageInt = new int[]{R.drawable.m4,R.drawable.m6};
    ViewFlipper v_flipper;
    ImageView leftArrow,rightArrow;
    ProgressDialog pDialog;
    List<String>imageUrl;
    List<MediaData>videoUrl;
    ListView videoListview;
    VideoView videoView;
    Button button;
    ProgressBar progressBar;
    ImageView close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_media, container, false);

//        button = v.findViewById(R.id.button);
        videoListview = v.findViewById(R.id.videoListview);
        v_flipper = v.findViewById(R.id.v_flipperCamera);
        videoView = v.findViewById(R.id.videoView);
        progressBar = v.findViewById(R.id.progressBar);
        imageUrl = new ArrayList<String>();
        close = v.findViewById(R.id.close);
//        videoUrl = new ArrayList<MediaData>();
//        videoView = v.findViewById(R.id.videoview);
       /* v_flipper = v.findViewById(R.id.v_flipperCamera);
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
        }*/

       /* pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("loading...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);*/
//        slider();
//        getMedia();
        imageSlide();
        videoSlide();


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
                videoView.stopPlayback();
                videoView.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
                v_flipper.setVisibility(View.VISIBLE);
            }
        });

       videoListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final MediaData mediaData = (MediaData) parent.getItemAtPosition(position);
               final String videoUrl = mediaData.getUrl();

               // Show progressbar
//               pDialog.show();
//        String videoUrl = "http://bdmedics.com/uploads/Karone_Okarone-Minar_Rahman_FusionBD.Com.mp4";
             /*  final Dialog dialog = new Dialog(getActivity());// add here your class name
               dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               dialog.setContentView(R.layout.video_dialog_layout);//add your own xml with defied with and height of videoview
               final VideoView videoView = dialog.findViewById(R.id.videoview3);
               MediaController mediaController = new MediaController(dialog.getContext());
               mediaController.show();
               mediaController.setAnchorView(videoView);
               videoView.setMediaController(mediaController);
               final ProgressBar progressBar = dialog.findViewById(R.id.dilogProgress);
               dialog.show();

               WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                       WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
               lp.copyFrom(dialog.getWindow().getAttributes());
               dialog.getWindow().setAttributes(lp);
//        uriPath= "android.resource://" + getPackageName() + "/" + R.raw.yourvid;

               dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        Log.v("Vidoe-URI", uriPath+ "");
               videoView.setVideoURI(Uri.parse(videoUrl));
//        videoView.start();


//        videoView.requestFocus();
               videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                   // Close the progress bar and play the video
                   public void onPrepared(MediaPlayer mp) {
//                       pDialog.dismiss();
                       progressBar.setIndeterminate(false);
                       progressBar.setVisibility(View.GONE);
                       videoView.start();
//                videoView.start();
                   }
               });

               videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                   public void onCompletion(MediaPlayer mp) {
                       if (dialog.isShowing()) {
                           dialog.dismiss();
                       }
//                finish();
                   }
               });*/



               videoView.setVisibility(View.VISIBLE);
               progressBar.setVisibility(View.VISIBLE);
               close.setVisibility(View.VISIBLE);
               v_flipper.setVisibility(View.GONE);
//                    videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName()+"/"+ R.raw.prem));
               MediaController mediaController = new MediaController(getActivity());
               mediaController.show();
               mediaController.setAnchorView(videoView);
               videoView.setMediaController(mediaController);
               videoView.setVideoURI(Uri.parse(videoUrl));
               videoView.start();
               videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                   @Override
                   public void onPrepared(MediaPlayer mp) {
                       //close the progress dialog when buffering is done
                       progressBar.setIndeterminate(false);
                       progressBar.setVisibility(View.GONE);
                   }
               });

           }
       });
/*button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        // Set progressbar message

       *//* pDialog = new ProgressDialog(getActivity());

        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController

            MediaController mediacontroller = new MediaController(getActivity());
            mediacontroller.setAnchorView(mVideoView);
            Uri videoUri = Uri.parse(videoUrl);
            mVideoView.setMediaController(mediacontroller);
            mVideoView.setVideoURI(videoUri);

        } catch (Exception e) {

            e.printStackTrace();
        }

        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                mVideoView.start();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
//                finish();
            }
        });*//*
    }
});*/


        return v;
    }

    public void slider(List<String>test){


//        System.out.println("image url size: "+url.size());

        /*for(int i = 0; i < test.size(); i++){

            if(test.get(i).getType() == 1){
                imageUrl.add(ApiInterface.BASE_URL+test.get(i).getUrl());
                System.out.println("image url ................ :"+imageUrl.get(i) );

            }
        }*/


        for(int i = 0; i<test.size(); i++){

                System.out.println("emon 222");

            ImageView imageView = new ImageView(getActivity());
            Picasso.get().load(test.get(i)).into(imageView);

//        imageView.setBackgroundResource(image);
//                v_flipper.removeView(imageView);
//                v_flipper.addView(imageView);
                 v_flipper.addView(imageView);
                v_flipper.setFlipInterval(2000);
                v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
                v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
                v_flipper.setAutoStart(true);

            }




    }






    private void imageSlide(){

        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String selectQuery = "SELECT *  FROM media where media.type = 1";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.getCount()>0){

            cursor.moveToFirst();
            ArrayList<String>image = new ArrayList<String>();
            while(!cursor.isAfterLast()){
                String imgUrl = ApiInterface.BASE_URL+cursor.getString(cursor.getColumnIndex("url"));
                image.add(imgUrl);
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
            slider(image);

        }else{
            Toast.makeText(getActivity(),"wait while data is loading",Toast.LENGTH_LONG).show();
        }

    }
    private void videoSlide(){

        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String selectQuery = "SELECT *  FROM media where media.type = 2";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.getCount()>0){

            cursor.moveToFirst();
            ArrayList<MediaData>video = new ArrayList<MediaData>();
            while(!cursor.isAfterLast()){
                String imgUrl = ApiInterface.BASE_URL+cursor.getString(cursor.getColumnIndex("url"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                video.add(new MediaData(name,imgUrl));
                cursor.moveToNext();

            }

            cursor.close();
            sqLiteDatabase.close();

            VideoAdapter videoAdapter = new VideoAdapter(getActivity(),video);
            videoListview.setAdapter(videoAdapter);

        }else{
            Toast.makeText(getActivity(),"wait while data is loading",Toast.LENGTH_LONG).show();
        }

    }



}
