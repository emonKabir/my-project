package com.example.emon.bdmedic.SingleTextAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.Media.VideoAdapter;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.List;

public class SingleTextAdapter extends BaseAdapter implements Filterable {

    Activity activity;
    Context context;
    List<String> stringList;
    List<String> stringListFull;

    public SingleTextAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        stringListFull = new ArrayList<>(stringList);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder {

        TextView pharmacologyName;
//            MaterialSpinner mSpinner;
//            LinearLayout linearLayout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
//        LayoutInflater inflater = context.getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pharmacology_layout, null);
            holder = new ViewHolder();
            holder.pharmacologyName  = convertView.findViewById(R.id.pharmacologyName);
//            holder.medicineImage = convertView.findViewById(R.id.videoImage);
//             holder.linearLayout = convertView.findViewById(R.id.linearLayout);
//             holder.others =convertView.findViewById(R.id.others);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String  item= stringList.get(position);
//        holder.medicineImage.setImageResource(ImageArray[position]);
        holder.pharmacologyName.setText(item);

        if((position % 2) == 0){

            convertView.setBackgroundResource(R.color.colorWhite);
        }else{
            convertView.setBackgroundResource(R.color.lightGreen);
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return stringFilter;
    }
    private  Filter stringFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(stringListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : stringListFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            stringList.clear();
            stringList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
