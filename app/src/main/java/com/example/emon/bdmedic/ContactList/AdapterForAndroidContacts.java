package com.example.emon.bdmedic.ContactList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.emon.bdmedic.ContactList.PojoClass.ContactData;
import com.example.emon.bdmedic.PriceCalculator.DrugList;
import com.example.emon.bdmedic.PriceCalculator.DrugListAdapter;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterForAndroidContacts extends BaseAdapter implements Filterable {


    Activity activity;
    List<Android_Contact> mList_Android_Contacts;
    List<Android_Contact>filterContactList;
//</ Variables >

    //< constructor with ListArray >
    public AdapterForAndroidContacts(Activity activity, List<Android_Contact> mContact) {
        this.activity = activity;
        this.mList_Android_Contacts = mContact;
        filterContactList = new ArrayList<>(mList_Android_Contacts);
    }
//</ constructor with ListArray >

    @Override
    public int getCount() {
        return mList_Android_Contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mList_Android_Contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder {
        TextView name;
        TextView phone;
        TextView bloodGroup;

    }
    //----< show items >----
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_list_item, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.contact_name);
            holder.phone = convertView.findViewById(R.id.phone_number);
            holder.bloodGroup =convertView.findViewById(R.id.textview_blood_group);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        final Android_Contact item = mList_Android_Contacts.get(position) ;
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone_number());
        holder.bloodGroup.setText(item.getBloodGroup());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    private  Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Android_Contact> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterContactList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

//                 item.getGenerciName().toLowerCase().contains(filterPattern) || item.getCompanyName().toLowerCase().contains(filterPattern)
                for (Android_Contact item : filterContactList) {
                    if (item.getName().toLowerCase().startsWith(filterPattern) || item.getBloodGroup().toLowerCase().startsWith(filterPattern) ) {
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
            mList_Android_Contacts.clear();
            mList_Android_Contacts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
