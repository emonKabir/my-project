package com.example.emon.bdmedic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CommonTreatmentList extends AppCompatActivity {


    private Toolbar toolbar;
    ListView ListviewCommonTreatment;
    String diseases[] = {"Diseases 1","Diseases 2","Diseases 3","Diseases 4","Diseases 5","Diseases 6","Diseases 7","Diseases 8"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_treatment_list);

        ListviewCommonTreatment = findViewById(R.id.listviewCommonTreatment);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.common_treatment_list,R.id.commonTreatmentListTextView,diseases);
        ListviewCommonTreatment.setAdapter(adapter);


        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_health);
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
