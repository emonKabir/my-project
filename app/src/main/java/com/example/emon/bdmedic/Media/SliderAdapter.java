package com.example.emon.bdmedic.Media;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emon.bdmedic.R;

public class SliderAdapter extends BaseAdapter {
//Context context;
Activity activity;
int[] ImageArray;
LayoutInflater layoutInflater;
    public SliderAdapter(Activity appActivity, int[] Images) {

        this.activity = appActivity;
        this.ImageArray = Images;


    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView medicineImage;
//            MaterialSpinner mSpinner;
//            LinearLayout linearLayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.video_list, null);
            holder = new ViewHolder();
           holder.medicineImage = convertView.findViewById(R.id.videoImage);
//             holder.linearLayout = convertView.findViewById(R.id.linearLayout);
//             holder.others =convertView.findViewById(R.id.others);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.medicineImage.setImageResource(ImageArray[position]);

        return convertView;


    }
}
