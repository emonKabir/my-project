package com.example.emon.bdmedic.Search;

import com.example.emon.bdmedic.Pharmacologist.DrugsData;

import java.util.Comparator;

public class SortBasedOnName implements Comparator
{
    public int compare(Object o1, Object o2)
    {

        DrugsData dd1 = (DrugsData) o1;// where FBFriends_Obj is your object class
        DrugsData dd2 = (DrugsData) o2;
        return dd1.getName().compareToIgnoreCase(dd2.getName());//where uname is field name
    }

}
