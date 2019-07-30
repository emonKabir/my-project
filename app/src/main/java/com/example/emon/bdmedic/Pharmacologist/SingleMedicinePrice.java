package com.example.emon.bdmedic.Pharmacologist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emon.bdmedic.LocalDatabase.MyDBHelper;
import com.example.emon.bdmedic.R;

import java.util.ArrayList;
import java.util.List;

public class SingleMedicinePrice extends AppCompatActivity {

    MyDBHelper myDBHelper;
    String rcvDrugsName;
    List<String>unitList;
    List<String>unitPriceList;
    List<String> packagePriceMethod;
    TextView tradeName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlemedicine_price);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);
        unitList = new ArrayList<String>();
        unitPriceList = new ArrayList<String>();
        packagePriceMethod = new ArrayList<String>();
        LinearLayout linearLayout = findViewById(R.id.lnr);
        tradeName = findViewById(R.id.TradeName);
        myDBHelper = new MyDBHelper(this);
//        myDBHelper.getWritableDatabase();
        Intent drugsIntent = getIntent();
        rcvDrugsName = drugsIntent.getStringExtra("tradeName");
        tradeName.setText(rcvDrugsName);
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();
        String query = "select * from medicines left join medicines_dosage on medicines.id = medicines_dosage.medicine_id where medicines.name = \""+rcvDrugsName+"\"";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                unitList.add(cursor.getString(cursor.getColumnIndex("unit")));
                System.out.println("UNIT : "+cursor.getString(cursor.getColumnIndex("unit")));
//                MedicineSubGroup.out.println("INDEX OF UNIT : "+dosageUnit.indexOf(cursor.getString(cursor.getColumnIndex("unit"))));
                unitPriceList.add(cursor.getString(cursor.getColumnIndex("unit_price")));
                System.out.println("UNIT_PRICE : "+cursor.getString(cursor.getColumnIndex("unit_price")));
//                MedicineSubGroup.out.println("INDEX OF UNIT_PRICE : "+unit_price.indexOf(cursor.getString(cursor.getColumnIndex("unit_price"))));

                packagePriceMethod.add(cursor.getString(cursor.getColumnIndex("package_method")));
                System.out.println("PACKAGE_METHOD : "+cursor.getString(cursor.getColumnIndex("package_method")));
//                MedicineSubGroup.out.println("INDEX OF package_price : "+unit_price.indexOf(cursor.getString(cursor.getColumnIndex("package_price"))));

                //medicine_id.add(cursor.getInt(cursor.getColumnIndex("id")));
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();

            for(int i = 0; i < unitList.size(); i++)
            {
                String unitPrice =  unitPriceList.get(i).trim();
                String packageMethod = packagePriceMethod.get(i).trim();
                packageMethod = packageMethod.replaceAll("[^0-9]","");
//            packageMethod = packageMethod.substring(0,packageMethod.indexOf('\''));
                unitPrice = unitPrice.substring(0,unitPrice.indexOf(' '));
                System.out.println("Package : "+packageMethod);
                System.out.println("Price : "+unitPrice);
                double prc = Double.parseDouble(unitPrice);
                int packAge = Integer.parseInt(packageMethod);
                double totalP = packAge * prc;
                int totalI = (int) Math.ceil(totalP);
                String totalO = String.valueOf(totalI);
                String total = unitList.get(i)+" * "+packagePriceMethod.get(i)+"="+totalO+" Taka";
                TextView tv = new TextView(this);
                tv.setText(total);
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                linearLayout.addView(tv);

            }

        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
