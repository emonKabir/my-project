package com.example.emon.bdmedic.ContactList;

import com.example.emon.bdmedic.Pharmacologist.DrugsData;

import java.util.Comparator;

public class SortBasedOnContactName implements Comparator
{
    public int compare(Object o1, Object o2)
    {

        Android_Contact dd1 = (Android_Contact) o1;// where FBFriends_Obj is your object class
        Android_Contact dd2 = (Android_Contact) o2;
        return dd1.getName().compareToIgnoreCase(dd2.getName());//where uname is field name
    }

}
