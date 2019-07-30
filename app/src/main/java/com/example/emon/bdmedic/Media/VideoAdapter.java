package com.example.emon.bdmedic.Media;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.R;

import java.util.List;

public class VideoAdapter extends BaseAdapter {

    Activity activity;
    Context context;
    List<MediaData> mediaDataList;

    public VideoAdapter(Context context, List<MediaData> mediaDataList) {
        this.context = context;
        this.mediaDataList = mediaDataList;
    }

    @Override
    public int getCount() {
        return mediaDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mediaDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {

             TextView videoTitle;
//            MaterialSpinner mSpinner;
//            LinearLayout linearLayout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       final ViewHolder holder;
//        LayoutInflater inflater = context.getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.video_list, null);
            holder = new ViewHolder();
            holder.videoTitle  = convertView.findViewById(R.id.videoTitle);
//            holder.medicineImage = convertView.findViewById(R.id.videoImage);
//             holder.linearLayout = convertView.findViewById(R.id.linearLayout);
//             holder.others =convertView.findViewById(R.id.others);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MediaData  item= mediaDataList.get(position);
//        holder.medicineImage.setImageResource(ImageArray[position]);
        holder.videoTitle.setText(item.getName());

      /*  if((position % 2) == 0){

            convertView.setBackgroundResource(R.color.colorGreen);
        }else{
            convertView.setBackgroundResource(R.color.colorBlack);
        }
*/
        return convertView;
    }
}
